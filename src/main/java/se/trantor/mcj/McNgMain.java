package se.trantor.mcj;


import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.protobuf.Empty;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import se.trantor.grpcproto.BrewStatusReply;
import se.trantor.grpcproto.BrewStatusReply.Builder;
import se.trantor.grpcproto.BrewStep;
import se.trantor.grpcproto.HopAdditionStep;
import se.trantor.grpcproto.MashProfileStep;
import se.trantor.grpcproto.McServerGrpc;
import se.trantor.grpcproto.StartStopRequest;
import se.trantor.grpcproto.StartStopRequest.StartStop;
import se.trantor.grpcproto.SuccessReply;
import se.trantor.grpcproto.SuccessReply.Success;
import se.trantor.mcj.MashStepControl.MashControlStateE;



/**
 * Server that manages startup/shutdown of a {@code Greeter} server.
 */
public class McNgMain {

	private static final Logger logger = Logger.getLogger(McNgMain.class.getName());
	private ArrayList<MashStep> mashStepList;
	private MashStepControl mashStepControl;

	/* The port on which the server should run */
	private int port = 50051;
	private Server server;


	public void start() throws IOException {
		server = ServerBuilder.forPort(port).addService(McServerGrpc.bindService(new McServerImpl())).build().start();
		//		server = ServerBuilder.forPort(port).addService(new McServerImpl()).build().start();
		logger.info("Server started, listening on " + port);

		/*		ManagedChannel channel = ManagedChannelBuilder.forAddress("127.0.0.1", port)
		        .usePlaintext(true)
		        .build();
		McServerGrpc.McServerBlockingStub  blockingStub = McServerGrpc.newBlockingStub(channel);


	    LoadBrewProfileRequest request = LoadBrewProfileRequest.newBuilder().setBoilTime(90).build();
	    SuccessReply response;
	    try {
	      response = blockingStub.loadBrewProfile(request);
	    } catch (StatusRuntimeException e) {
	      logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
	      return;
	    }
	    logger.info("Greeting: " + response.getMsg());*/


		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				// Use stderr here since the logger may have been reset by its
				// JVM shutdown hook.
				System.err.println("*** shutting down gRPC server since JVM is shutting down");
				McNgMain.this.stop();
				System.err.println("*** server shut down");
			}
		});
	}

	public void stop() {
		if (server != null) {
			server.shutdown();
		}
	}

	/**
	 * Await termination on the main thread since the grpc library uses daemon
	 * threads.
	 */
	public void blockUntilShutdown() throws InterruptedException {
		if (server != null) {
			server.awaitTermination();
		}
	}

	/**
	 * Main launches the server from the command line.
	 */

	private class McServerImpl extends McServerGrpc.AbstractMcServer {


		private int boilTime;
		private ArrayList<HopAddition> hopAdditions;
		private BrewController bc;
		private Thread tBc;

		@Override
		public void loadBrewProfile(se.trantor.grpcproto.LoadBrewProfileRequest request,
				io.grpc.stub.StreamObserver<se.trantor.grpcproto.SuccessReply> responseObserver) {

			logger.log(Level.INFO, "Brew profiles load request.");
			logger.log(Level.INFO, "Mash profile:");
			mashStepList = new ArrayList<MashStep>();
			for( MashProfileStep step : request.getMashProfileStepsList() )
			{	
				MashStep ms = new MashStep();
				ms.Temperature = step.getTemperature();
				ms.Time = step.getTime();
				mashStepList.add(ms);
				logger.log(Level.INFO,  MessageFormat.format("Time {0} for {1} minutes", ms.Temperature, ms.Time));
			}

			logger.log(Level.INFO, "Hop additions:");
			hopAdditions = new ArrayList<HopAddition>();
			for( HopAdditionStep step : request.getHopAdditionStepList() )
			{	
				HopAddition ha = new HopAddition();
				ha.Time = step.getTime();
				ha.Name = step.getName();
				hopAdditions.add(ha);
				logger.log(Level.INFO,  MessageFormat.format("Time {0} for {1} minutes", ha.Time, ha.Name));
			}


			boilTime = request.getBoilTime();
			logger.log(Level.INFO, "Boil time is {0}", boilTime);

			SuccessReply reply = SuccessReply.newBuilder().setSuccess(Success.OK).build();
			responseObserver.onNext(reply);
			responseObserver.onCompleted();
		}


		@SuppressWarnings("deprecation")
		@java.lang.Override
		public void startStopAbort(StartStopRequest request,
				io.grpc.stub.StreamObserver<SuccessReply> responseObserver) {

			se.trantor.grpcproto.SuccessReply.Builder rplb = SuccessReply.newBuilder();

			if(request.getStartStop() == StartStop.START)
			{
				if (bc != null && bc.GetState() != MashControlStateE.DONE)
				{
					logger.log(Level.INFO, "Brew process already started. No actions will be taken.");
					rplb.setSuccess(se.trantor.grpcproto.SuccessReply.Success.FALIED);
					rplb.setMsg("Brew process is already running.");
				}
				else
				{
					logger.log(Level.INFO, "Start request");
					bc = new BrewController(mashStepList, new ArrayList<HopAddition>(), boilTime);
					tBc = new Thread(bc);
					tBc.start();
					rplb.setSuccess(se.trantor.grpcproto.SuccessReply.Success.OK);
					rplb.setMsg("Brew process starting ...");
				}
			}
			
			if (request.getStartStop() == StartStop.STOP)
			{
				if ((bc != null) && (bc.GetState() != MashControlStateE.DONE))
				{
					tBc.interrupt();
					rplb.setSuccess(se.trantor.grpcproto.SuccessReply.Success.OK);
					rplb.setMsg("Stopping brew process ...");

				}
				else
				{
					rplb.setSuccess(se.trantor.grpcproto.SuccessReply.Success.FALIED);
					rplb.setMsg("No brew process running. not possible to stop ...");

				}
			}

			SuccessReply reply = rplb.build();
			responseObserver.onNext(reply);
			responseObserver.onCompleted();		
		}


		@java.lang.Override
		public void grainsAdded(se.trantor.grpcproto.GrainsAddedNotify request,
				io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {

			bc.SetGrainsAdded(true);

			Empty reply = Empty.newBuilder().build();
			responseObserver.onNext(reply);
			responseObserver.onCompleted();		
		}

		@java.lang.Override
		public void spargeDone(se.trantor.grpcproto.SpargeDoneNotify request,
				io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {

			bc.SpargeDoneNotify();

			Empty reply = Empty.newBuilder().build();
			responseObserver.onNext(reply);
			responseObserver.onCompleted();		
		}

		@java.lang.Override
		public void wortChillerSanitizedDone(se.trantor.grpcproto.WortChillerSanitizedDoneNotify request,
				io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {

			bc.WortChillerSanitiedDoneNotify();

			Empty reply = Empty.newBuilder().build();
			responseObserver.onNext(reply);
			responseObserver.onCompleted();		
		}
		
		@java.lang.Override
		public void getStatus(com.google.protobuf.Empty request,
				io.grpc.stub.StreamObserver<se.trantor.grpcproto.BrewStatusReply> responseObserver) {
			if(bc != null)
			{
				BrewStatus bs = bc.getStatus();
				Iterator<MashStep> i = bs.currentMashProfile.iterator();
				Builder bsr = BrewStatusReply.newBuilder();				
				while(i.hasNext())
				{
					MashStep ms = i.next();
					bsr.addRemainingMashSteps(MashProfileStep.newBuilder()
							.setTemperature(ms.Temperature)
							.setTime(ms.Time)
							.build());
				}
				
				bsr.setRemainingBoilTime(bs.boilTime);
				
				bsr.setProgress(bs.progress);

				bsr.setMashTemperature(bs.currentMashTemp).setMashTemperatureSetpoint(bs.currentMashSetPoint);
				switch(bs.state)
				{
				case INIT:
					bsr.setCurrentBrewStep(BrewStep.IDLE);
					break;
				case HEATING:
				case HEATING_TO_STRIKE_WATER:
					bsr.setCurrentBrewStep(BrewStep.HEATING);
					break;
				case MASHING:
					bsr.setCurrentBrewStep(BrewStep.MASHING);
					break;
				case MASH_DONE:
					bsr.setCurrentBrewStep(BrewStep.MASH_DONE_START_SPARGE);
					break;
				case WAIT_FOR_GRAINS:
					bsr.setCurrentBrewStep(BrewStep.STRIKE_WATER_TEMP_REACHED);
					break;
				case BOILING:
					bsr.setCurrentBrewStep(BrewStep.BOILING);
					break;
				case BOIL_DONE:
					bsr.setCurrentBrewStep(BrewStep.BOIL_DONE);
					break;
				case DONE:
					bsr.setCurrentBrewStep(BrewStep.IDLE);
					break;
				default:
					break;

				}
				bsr.build();
				BrewStatusReply reply = bsr.build();
				responseObserver.onNext(reply);
				responseObserver.onCompleted();		

			}
			else
			{
				BrewStatusReply reply = BrewStatusReply.newBuilder().build();
				responseObserver.onNext(reply);
				responseObserver.onCompleted();		
			}
		}

	}

}
