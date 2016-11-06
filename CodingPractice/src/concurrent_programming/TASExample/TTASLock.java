package concurrent_programming.TASExample;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by anirudh on 24/9/16.
 */
public class TTASLock
{  AtomicBoolean state = new AtomicBoolean(false);

    public void lock() {
        while(true) {
            while(state.get()) {

        }
            if (!state.getAndSet(true))
                return;
        }
    }
    public  void unlock() {
        state.set(false);
    }
}
