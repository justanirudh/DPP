package concurrent_programming.optimistic_sync;

/**
 * Created by anirudh on 29/9/16.
 */
public class COWComplexRunnable implements Runnable{
    public COWComplex cx;

    double factor;

    public COWComplexRunnable(COWComplex cx, double factor){
        this.cx = cx;
        this.factor =factor;
    }

    @Override
    public void run(){
        cx.scale(factor);
        System.out.println("Real: " +  cx.getComplex().getReal() + " and imaginary: " +
                cx.getComplex().getImag());
    }

}
