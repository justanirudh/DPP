package concurrent_programming.optimistic_sync;

/**
 * Created by anirudh on 27/9/16.
 */
public class OptComplexCASRunnable implements Runnable{

    public OptComplexCAS cx;

    double factor;

    public OptComplexCASRunnable(OptComplexCAS cx, double factor){
        this.cx = cx;
        this.factor =factor;
    }

    @Override
    public void run(){
        cx.scale(factor);
        System.out.println("Real: " +  cx.v.get().real + " and imaginary: " + cx.v.get().imag);
    }

}
