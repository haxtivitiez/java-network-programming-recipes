package tcpMultiThread;


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
    private static int  sessions = 0;

    public static void main( String[] args ) throws IOException
    {
        try {

            ServerSocket serverSocket = new ServerSocket(TELNET_PORT);
            while (true) {

                final Socket clientSocket = serverSocket.accept();
                InetSocketAddress remote = (InetSocketAddress) clientSocket.getRemoteSocketAddress();
                System.out.println("connection from port=" + remote.getPort() + " host=" + remote.getHostName());

                new Thread(
                        () -> {
                            sessions += 1;
                            InputStream in = null;
                            try {
                                System.out.println("session " + sessions + " Started...");
                                in = clientSocket.getInputStream();
                                OutputStream out = clientSocket.getOutputStream();
                                byte[] bufer = new byte[BUFLEN];
                                while (clientSocket.isConnected()) {
                                    int len = in.read(bufer);
                                    if (len > 0) {
                                        out.write(bufer, 0, len);
                                    }
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }
                ).start();


            }

        }
        catch (SocketException e){
            System.out.println("Client exit..");
        }
    }
}
