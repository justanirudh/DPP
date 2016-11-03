package concurrent_programming.sockets;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

import static java.util.concurrent.Executors.newCachedThreadPool;

/**
 * Created by anirudh on 30/10/16.
 */
public class ConcurrentEchoServerWithExecutor implements Runnable {
    static final int PORT = 1040;
    Socket incoming;
    static ExecutorService executor = newCachedThreadPool();

    public static void main(String[] Args) throws IOException {
        ServerSocket s = null;
        try {
            s = new ServerSocket(PORT);
            for(;;) {
                Socket incoming  = s.accept();
                executor.execute(new ConcurrentEchoServerWithExecutor(incoming));
            }
        } finally {
            try {
                if(s!=null)
                    s.close();
            }
         catch(IOException ignore){}
        }
    }

    public ConcurrentEchoServerWithExecutor(Socket incoming) {
        this.incoming = incoming;
    }

    public void run() {
        try {
            talkOnSocket(incoming);
        } catch (Exception e){/*terminate */}
        finally {
            try{
                if (incoming != null)
                    incoming.close();
            } catch(IOException ignore){}
        }
    }

    private void talkOnSocket(Socket incoming){/**/}
}