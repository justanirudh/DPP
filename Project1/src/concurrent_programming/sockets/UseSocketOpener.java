package concurrent_programming.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by anirudh on 29/10/16.
 */
public class UseSocketOpener {

    static class SocketOpener extends Thread {
        volatile Socket socket;
        String host;
        int port;
        SocketOpener(String host, int port){
            this.host = host;
            this.port = port;
        }
        public void run() {
            try {
                socket = new Socket(host,port);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String line;
                do {
                    line = in.readLine(); // read line of text from socket
                    if (line != null)
                        System.out.println(line);
                } while (line != null);
                Thread.currentThread().interrupt();
                if(Thread.currentThread().isInterrupted())
                    System.out.println("interrupted yo");
            } catch(IOException exception){
                socket = null;
            }
        }
    }

    public static void main(String args[]) {
        long TIME_OUT = 50;
        String host = "time-c.nist.gov";
        int port = 13;
        SocketOpener socketOpener = new SocketOpener(host, port);
        socketOpener.start();

        try {
            socketOpener.join(TIME_OUT);
            System.out.println("joined");
        } catch(InterruptedException e) {
            socketOpener.socket = null;
        }

        if (socketOpener.socket != null)
            try {
                //do something with socket
            }
            finally {
                try{
                    socketOpener.socket.close();
                } catch(IOException i){

                }
            }
    }
}
