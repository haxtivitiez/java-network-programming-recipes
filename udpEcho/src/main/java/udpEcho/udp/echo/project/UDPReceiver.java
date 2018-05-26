package udpEcho.udp.echo.project;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPReceiver {

    private static final int BUFLEN = 2000;
    final private int port;
    DatagramPacket receivePacket = null;
    private DatagramSocket serverSocket = null;
    UDPReceiver(int port){
        this.port = port;
    }
    boolean isBound(){
        return serverSocket != null && serverSocket.isBound();
    }

    String receive(){
        try {
           Thread.sleep(1000);
            serverSocket = new DatagramSocket(port);
            byte[] receiveData = new byte[BUFLEN];
            receivePacket = new DatagramPacket(receiveData,receiveData.length);
            serverSocket.receive(receivePacket);
            return new String(receivePacket.getData());
        }catch (IOException e){
            return e.toString();
        }catch (InterruptedException e){
           return e.toString();
        }
    }
}
