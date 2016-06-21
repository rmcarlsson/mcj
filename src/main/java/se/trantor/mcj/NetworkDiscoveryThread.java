package se.trantor.mcj;

import java.io.ByteArrayOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

import se.trantor.grpcproto.*;


public class NetworkDiscoveryThread implements Runnable {

	private DatagramSocket socket;
	private static final Logger logger = Logger.getLogger(NetworkDiscoveryThread.class.getName());

	public void run() {

		try {
			// Keep a socket open to listen to all the UDP trafic that is
			// destined
			// for this port
			socket = new DatagramSocket(8888, InetAddress.getByName("0.0.0.0"));
			socket.setBroadcast(true);

			while (true) {
				logger.log(Level.INFO, "Ready to receive broadcast packets");

				// Receive a packet
				byte[] buffer = new byte[15000];
				DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
				socket.receive(packet);

				byte[] bytes = new byte[packet.getLength()];
				for (int i = 0; i < packet.getLength(); i++) {
					bytes[i] = buffer[i];
				}
				
				// Packet received
				logger.log(Level.INFO, "Discovery packet received from: {0} ", packet.getAddress().getHostAddress());
				logger.log(Level.INFO, "Packet received; data: {0}", new String(packet.getData()));

				//InputStream aInput = new ByteArrayInputStream(bytes);
				NetworkDiscoveryRequest request = NetworkDiscoveryRequest.parseFrom(bytes);
						//parseDelimitedFrom(aInput);

				if (request.getName().equals("NETWORK-DISCOVERY-MSG")) {
					
					ByteArrayOutputStream aOutput = new ByteArrayOutputStream(1024);
					NetworkDiscoveryReply reply = NetworkDiscoveryReply.newBuilder().setName("NETWORK-DISCOVERY-RELPY").build();
					reply.writeDelimitedTo(aOutput);
					byte aSendData[] = aOutput.toByteArray();

					// Send a response
					DatagramPacket sendPacket = new DatagramPacket(aSendData, aSendData.length, packet.getAddress(),
							packet.getPort());
					socket.send(sendPacket);

					logger.log(Level.INFO, "Sent packet to: {0}", sendPacket.getAddress().getHostAddress());
				}
			}
		} catch (

		Exception ex) {
			Logger.getLogger(NetworkDiscoveryThread.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static NetworkDiscoveryThread getInstance() {
		return NetworkDiscoveryHolder.INSTANCE;
	}

	private static class NetworkDiscoveryHolder {
		private static final NetworkDiscoveryThread INSTANCE = new NetworkDiscoveryThread();
	}
}
