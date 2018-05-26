package udpEcho.udp.echo.project;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPEchoServer {
    public static void main(String[] args) throws IOException {
        UDPReceiver receiver = new UDPReceiver(PortUsed.SERVER_PORT);
        String sentence = receiver.receive();
        DatagramPacket packet  = new DatagramPacket(sentence.getBytes(), sentence.length(),InetAddress.getLocalHost(),PortUsed.CLIENT_PORT);
        DatagramSocket datagramSocket = new DatagramSocket();
        datagramSocket.send(packet);
    }
}
