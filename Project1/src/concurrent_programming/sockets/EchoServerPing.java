package concurrent_programming.sockets;

/**
 * Created by anirudh on 1/11/16.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class EchoServerPing {
    public static void main(String[] args) {
        try {
            Socket s = new Socket("localhost", 8189);
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String line;
            do {
                line = in.readLine(); // read line of text from socket
                if (line != null)
                    System.out.println(line);
            } while (line != null);
        }
        catch (IOException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
    }
}

