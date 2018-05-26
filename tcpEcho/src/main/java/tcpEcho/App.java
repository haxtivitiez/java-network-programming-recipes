package tcpEcho;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;

/**
 * Hello world!
 *
 */
public class App 
{
    private static final int TELNET_PORT = 8080;
    private static final int BUFLEN = 1024;

    public static void main( String[] args ) throws IOException
    {
        try {
            ServerSocket serverSocket = new ServerSocket(TELNET_PORT);
            System.out.println("Server Started...");
            Socket clientSocket = serverSocket.accept();
            InetSocketAddress remote = (InetSocketAddress) clientSocket.getRemoteSocketAddress();
            System.out.println("connection from port=" + remote.getPort() + " host=" + remote.getHostName());
            InputStream in = clientSocket.getInputStream();
            OutputStream out = clientSocket.getOutputStream();
            byte[] buffer = new byte[BUFLEN];
            while (clientSocket.isConnected()) {
                int len = in.read(buffer);
                if (len > 0) {
                    out.write(buffer, 0, len);
                }
            }
        } catch (SocketException e){
            System.out.println("Client exit.. [INFO]: " + e.getMessage());
        }
    }
}
