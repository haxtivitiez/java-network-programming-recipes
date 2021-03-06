package com.un4.d;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        try {

            Socket socket = new Socket("www.packtpub.com",80);
            OutputStream os = socket.getOutputStream();
            os.write(
                    ("GET / HTTP/1.1\r\n"+
                        "Host: www.packtpub.com\r\n\r\n").getBytes());
            os.flush();
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null){
                System.out.println(line);
            }
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
}
