package se.trantor.grpcproto;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.0.3)",
    comments = "Source: mc-service.proto")
public class McServerGrpc {

  private McServerGrpc() {}

  public static final String SERVICE_NAME = "grpcproto.McServer";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<se.trantor.grpcproto.LoadBrewProfileRequest,
      se.trantor.grpcproto.SuccessReply> METHOD_LOAD_BREW_PROFILE =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "grpcproto.McServer", "LoadBrewProfile"),
          io.grpc.protobuf.ProtoUtils.marshaller(se.trantor.grpcproto.LoadBrewProfileRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(se.trantor.grpcproto.SuccessReply.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<se.trantor.grpcproto.StartStopRequest,
      se.trantor.grpcproto.SuccessReply> METHOD_START_STOP_ABORT =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "grpcproto.McServer", "StartStopAbort"),
          io.grpc.protobuf.ProtoUtils.marshaller(se.trantor.grpcproto.StartStopRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(se.trantor.grpcproto.SuccessReply.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<se.trantor.grpcproto.GrainsAddedNotify,
      com.google.protobuf.Empty> METHOD_GRAINS_ADDED =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "grpcproto.McServer", "GrainsAdded"),
          io.grpc.protobuf.ProtoUtils.marshaller(se.trantor.grpcproto.GrainsAddedNotify.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.google.protobuf.Empty.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<se.trantor.grpcproto.SpargeDoneNotify,
      com.google.protobuf.Empty> METHOD_SPARGE_DONE =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "grpcproto.McServer", "SpargeDone"),
          io.grpc.protobuf.ProtoUtils.marshaller(se.trantor.grpcproto.SpargeDoneNotify.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.google.protobuf.Empty.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<se.trantor.grpcproto.WortChillerSanitizedDoneNotify,
      com.google.protobuf.Empty> METHOD_WORT_CHILLER_SANITIZED_DONE =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "grpcproto.McServer", "WortChillerSanitizedDone"),
          io.grpc.protobuf.ProtoUtils.marshaller(se.trantor.grpcproto.WortChillerSanitizedDoneNotify.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.google.protobuf.Empty.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      se.trantor.grpcproto.BrewStatusReply> METHOD_GET_STATUS =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "grpcproto.McServer", "GetStatus"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.google.protobuf.Empty.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(se.trantor.grpcproto.BrewStatusReply.getDefaultInstance()));

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static McServerStub newStub(io.grpc.Channel channel) {
    return new McServerStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static McServerBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new McServerBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary and streaming output calls on the service
   */
  public static McServerFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new McServerFutureStub(channel);
  }

  /**
   */
  public static abstract class McServerImplBase implements io.grpc.BindableService {

    /**
     */
    public void loadBrewProfile(se.trantor.grpcproto.LoadBrewProfileRequest request,
        io.grpc.stub.StreamObserver<se.trantor.grpcproto.SuccessReply> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_LOAD_BREW_PROFILE, responseObserver);
    }

    /**
     */
    public void startStopAbort(se.trantor.grpcproto.StartStopRequest request,
        io.grpc.stub.StreamObserver<se.trantor.grpcproto.SuccessReply> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_START_STOP_ABORT, responseObserver);
    }

    /**
     */
    public void grainsAdded(se.trantor.grpcproto.GrainsAddedNotify request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GRAINS_ADDED, responseObserver);
    }

    /**
     */
    public void spargeDone(se.trantor.grpcproto.SpargeDoneNotify request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_SPARGE_DONE, responseObserver);
    }

    /**
     */
    public void wortChillerSanitizedDone(se.trantor.grpcproto.WortChillerSanitizedDoneNotify request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_WORT_CHILLER_SANITIZED_DONE, responseObserver);
    }

    /**
     */
    public void getStatus(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<se.trantor.grpcproto.BrewStatusReply> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_STATUS, responseObserver);
    }

    @java.lang.Override public io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_LOAD_BREW_PROFILE,
            asyncUnaryCall(
              new MethodHandlers<
                se.trantor.grpcproto.LoadBrewProfileRequest,
                se.trantor.grpcproto.SuccessReply>(
                  this, METHODID_LOAD_BREW_PROFILE)))
          .addMethod(
            METHOD_START_STOP_ABORT,
            asyncUnaryCall(
              new MethodHandlers<
                se.trantor.grpcproto.StartStopRequest,
                se.trantor.grpcproto.SuccessReply>(
                  this, METHODID_START_STOP_ABORT)))
          .addMethod(
            METHOD_GRAINS_ADDED,
            asyncUnaryCall(
              new MethodHandlers<
                se.trantor.grpcproto.GrainsAddedNotify,
                com.google.protobuf.Empty>(
                  this, METHODID_GRAINS_ADDED)))
          .addMethod(
            METHOD_SPARGE_DONE,
            asyncUnaryCall(
              new MethodHandlers<
                se.trantor.grpcproto.SpargeDoneNotify,
                com.google.protobuf.Empty>(
                  this, METHODID_SPARGE_DONE)))
          .addMethod(
            METHOD_WORT_CHILLER_SANITIZED_DONE,
            asyncUnaryCall(
              new MethodHandlers<
                se.trantor.grpcproto.WortChillerSanitizedDoneNotify,
                com.google.protobuf.Empty>(
                  this, METHODID_WORT_CHILLER_SANITIZED_DONE)))
          .addMethod(
            METHOD_GET_STATUS,
            asyncUnaryCall(
              new MethodHandlers<
                com.google.protobuf.Empty,
                se.trantor.grpcproto.BrewStatusReply>(
                  this, METHODID_GET_STATUS)))
          .build();
    }
  }

  /**
   */
  public static final class McServerStub extends io.grpc.stub.AbstractStub<McServerStub> {
    private McServerStub(io.grpc.Channel channel) {
      super(channel);
    }

    private McServerStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected McServerStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new McServerStub(channel, callOptions);
    }

    /**
     */
    public void loadBrewProfile(se.trantor.grpcproto.LoadBrewProfileRequest request,
        io.grpc.stub.StreamObserver<se.trantor.grpcproto.SuccessReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_LOAD_BREW_PROFILE, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void startStopAbort(se.trantor.grpcproto.StartStopRequest request,
        io.grpc.stub.StreamObserver<se.trantor.grpcproto.SuccessReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_START_STOP_ABORT, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void grainsAdded(se.trantor.grpcproto.GrainsAddedNotify request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GRAINS_ADDED, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void spargeDone(se.trantor.grpcproto.SpargeDoneNotify request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_SPARGE_DONE, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void wortChillerSanitizedDone(se.trantor.grpcproto.WortChillerSanitizedDoneNotify request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_WORT_CHILLER_SANITIZED_DONE, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getStatus(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<se.trantor.grpcproto.BrewStatusReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_STATUS, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class McServerBlockingStub extends io.grpc.stub.AbstractStub<McServerBlockingStub> {
    private McServerBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private McServerBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected McServerBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new McServerBlockingStub(channel, callOptions);
    }

    /**
     */
    public se.trantor.grpcproto.SuccessReply loadBrewProfile(se.trantor.grpcproto.LoadBrewProfileRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_LOAD_BREW_PROFILE, getCallOptions(), request);
    }

    /**
     */
    public se.trantor.grpcproto.SuccessReply startStopAbort(se.trantor.grpcproto.StartStopRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_START_STOP_ABORT, getCallOptions(), request);
    }

    /**
     */
    public com.google.protobuf.Empty grainsAdded(se.trantor.grpcproto.GrainsAddedNotify request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GRAINS_ADDED, getCallOptions(), request);
    }

    /**
     */
    public com.google.protobuf.Empty spargeDone(se.trantor.grpcproto.SpargeDoneNotify request) {
      return blockingUnaryCall(
          getChannel(), METHOD_SPARGE_DONE, getCallOptions(), request);
    }

    /**
     */
    public com.google.protobuf.Empty wortChillerSanitizedDone(se.trantor.grpcproto.WortChillerSanitizedDoneNotify request) {
      return blockingUnaryCall(
          getChannel(), METHOD_WORT_CHILLER_SANITIZED_DONE, getCallOptions(), request);
    }

    /**
     */
    public se.trantor.grpcproto.BrewStatusReply getStatus(com.google.protobuf.Empty request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_STATUS, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class McServerFutureStub extends io.grpc.stub.AbstractStub<McServerFutureStub> {
    private McServerFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private McServerFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected McServerFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new McServerFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<se.trantor.grpcproto.SuccessReply> loadBrewProfile(
        se.trantor.grpcproto.LoadBrewProfileRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_LOAD_BREW_PROFILE, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<se.trantor.grpcproto.SuccessReply> startStopAbort(
        se.trantor.grpcproto.StartStopRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_START_STOP_ABORT, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> grainsAdded(
        se.trantor.grpcproto.GrainsAddedNotify request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GRAINS_ADDED, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> spargeDone(
        se.trantor.grpcproto.SpargeDoneNotify request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_SPARGE_DONE, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> wortChillerSanitizedDone(
        se.trantor.grpcproto.WortChillerSanitizedDoneNotify request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_WORT_CHILLER_SANITIZED_DONE, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<se.trantor.grpcproto.BrewStatusReply> getStatus(
        com.google.protobuf.Empty request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_STATUS, getCallOptions()), request);
    }
  }

  private static final int METHODID_LOAD_BREW_PROFILE = 0;
  private static final int METHODID_START_STOP_ABORT = 1;
  private static final int METHODID_GRAINS_ADDED = 2;
  private static final int METHODID_SPARGE_DONE = 3;
  private static final int METHODID_WORT_CHILLER_SANITIZED_DONE = 4;
  private static final int METHODID_GET_STATUS = 5;

  private static class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final McServerImplBase serviceImpl;
    private final int methodId;

    public MethodHandlers(McServerImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_LOAD_BREW_PROFILE:
          serviceImpl.loadBrewProfile((se.trantor.grpcproto.LoadBrewProfileRequest) request,
              (io.grpc.stub.StreamObserver<se.trantor.grpcproto.SuccessReply>) responseObserver);
          break;
        case METHODID_START_STOP_ABORT:
          serviceImpl.startStopAbort((se.trantor.grpcproto.StartStopRequest) request,
              (io.grpc.stub.StreamObserver<se.trantor.grpcproto.SuccessReply>) responseObserver);
          break;
        case METHODID_GRAINS_ADDED:
          serviceImpl.grainsAdded((se.trantor.grpcproto.GrainsAddedNotify) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
          break;
        case METHODID_SPARGE_DONE:
          serviceImpl.spargeDone((se.trantor.grpcproto.SpargeDoneNotify) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
          break;
        case METHODID_WORT_CHILLER_SANITIZED_DONE:
          serviceImpl.wortChillerSanitizedDone((se.trantor.grpcproto.WortChillerSanitizedDoneNotify) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
          break;
        case METHODID_GET_STATUS:
          serviceImpl.getStatus((com.google.protobuf.Empty) request,
              (io.grpc.stub.StreamObserver<se.trantor.grpcproto.BrewStatusReply>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    return new io.grpc.ServiceDescriptor(SERVICE_NAME,
        METHOD_LOAD_BREW_PROFILE,
        METHOD_START_STOP_ABORT,
        METHOD_GRAINS_ADDED,
        METHOD_SPARGE_DONE,
        METHOD_WORT_CHILLER_SANITIZED_DONE,
        METHOD_GET_STATUS);
  }

}
