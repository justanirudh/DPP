package concurrent_programming.conditional_sync_readers_writers;

/**
 * Created by anirudh on 29/10/16.
 */
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReadersWriters {
    private int nr = 0;
    private int nw = 0;

    private final ReentrantLock lock;
    private final Condition readerCond;// nw==0
    private final Condition writerCond;// nw == 0 && nr == 0

    public ReadersWriters() {
        lock = new ReentrantLock();
        readerCond = lock.newCondition();
        writerCond = lock.newCondition();
    }

    public void startRead() throws InterruptedException{
        lock.lock();
        try{
            while (nw != 0)
            {readerCond.await();}
            nr++;
        }
        finally {lock.unlock();}
    }

    public void endRead() throws InterruptedException{
        lock.lock();
        try{
            nr--;
            if(nr==0)
            {writerCond.signalAll();}
        }
        finally{lock.unlock();}
    }

    public void startWrite() throws InterruptedException{
        lock.lock();
        try{
            while(nr!=0 && nw!=0)
            {writerCond.await();}
            nw++;
        }
        finally{lock.unlock();}
    }

    public void endWrite(){
        lock.lock();
        try{
            nw--;
            writerCond.signalAll();
            readerCond.signalAll();
        }
        finally{lock.unlock();}
    }

}
