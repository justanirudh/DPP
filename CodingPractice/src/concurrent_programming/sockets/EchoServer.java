package concurrent_programming.sockets;

/**
 * Created by anirudh on 30/10/16.
 */

import java.io.*;
import java.net.*;

public class EchoServer {

    private static void talkOnSocket(Socket incoming) throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(incoming.getInputStream()));
        PrintWriter out = new PrintWriter(incoming.getOutputStream(), true);
        out.println("Hello! Enter BYE to exit.");
        String line = in.readLine();
        while (line != null && !line.equals("BYE") && !Thread.interrupted())
        {   line = line.trim(); out.println(); }
    }

    public static void main(String[] args){
        ServerSocket s = null;
        Socket incoming = null;
        try {
            s = new ServerSocket(8189);
            incoming = s.accept();
            talkOnSocket(incoming);
        } catch (Exception e) {
            /*terminate*/
        } finally {
            try{
                if (incoming != null)
                    incoming.close();
            }
            catch(IOException ignore){}
            try {
                if (s != null) s.close();
            } catch(IOException ignore){}
        }
    }


}