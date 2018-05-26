package udpEcho.udp.echo.project;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class UDPEchoClient {

    private static final String MESSAGE = "Hello, World!";

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        byte[] buffer = MESSAGE.getBytes();
        DatagramPacket packet = new DatagramPacket(buffer,buffer.length,InetAddress.getLocalHost(), PortUsed.SERVER_PORT);
        DatagramSocket datagramSocket = new DatagramSocket();
        final UDPReceiver receiver = new UDPReceiver(PortUsed.CLIENT_PORT);
        CompletableFuture<String> response = CompletableFuture.supplyAsync(
                () -> receiver.receive()
        );
        int i = 1;
        while (!receiver.isBound()){
            System.out.println("" + i + ". not bound yet");
            i++;
            Thread.sleep(100);
        }
        System.out.println("SENDING: '" + MESSAGE);
        datagramSocket.send(packet);
        String received = response.get();
        System.out.println("SEND '" + MESSAGE + "' RECEIVED '" + received + "'");

    }
}
