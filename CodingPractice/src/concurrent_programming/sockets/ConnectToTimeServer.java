package concurrent_programming.sockets;

/**
 * Created by anirudh on 29/10/16.
 */
import java.io.*;
import java.net.Socket;
public class ConnectToTimeServer {
    public static void main(String[] args) {
        try {
            Socket s = new Socket("time-c.nist.gov", 13);
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
