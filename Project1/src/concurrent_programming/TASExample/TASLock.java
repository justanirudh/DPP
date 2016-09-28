package concurrent_programming.TASExample;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by anirudh on 24/9/16.
 */

//TODO: understand this
public class TASLock
{ AtomicBoolean state = new AtomicBoolean(false);

    public void lock() {
        while(state.getAndSet(true)){ }
    }




    public void unlock()
    {  state.set(false); }
}
