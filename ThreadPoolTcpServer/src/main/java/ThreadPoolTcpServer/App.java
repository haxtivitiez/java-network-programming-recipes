package ThreadPoolTcpServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
        final ServerSocket serverSocket = new ServerSocket(TELNET_PORT);
        final ExecutorService service = Executors.newFixedThreadPool(2);

        while (true){

                final Socket clientSocket = serverSocket.accept();
                final InetSocketAddress remote = (InetSocketAddress) clientSocket.getRemoteSocketAddress();
                System.out.println("connection from port=" + remote.getPort() + " host=" + remote.getHostName());
                service.submit(
                        () -> {
                            try {
                                final InputStream in = clientSocket.getInputStream();
                                final OutputStream out = clientSocket.getOutputStream();
                                byte[] buffer = new byte[BUFLEN];
                                while (clientSocket.isConnected()){
                                    int len = in.read(buffer);
                                    if ( len > 0 ){
                                        out.write(buffer);
                                    }
                                }
                            } catch (IOException e){
                                e.printStackTrace();
                            }
                        }
                );
        }
    }
}
