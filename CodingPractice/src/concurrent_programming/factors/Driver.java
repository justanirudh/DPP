package concurrent_programming.factors;

import java.math.BigInteger;

/**
 * Created by anirudh on 3/9/16.
 */
public class Driver {

    static final int NUM_THREADS = 8;
    public static void main(String[] args) {

        Thread[] threads = new Thread[NUM_THREADS];
        BigInteger[] numbers = {BigInteger.valueOf(35), BigInteger.valueOf(45),BigInteger.valueOf(55),
                BigInteger.valueOf(88),BigInteger.valueOf(100),BigInteger.valueOf(41),
                BigInteger.valueOf(43),BigInteger.valueOf(76)};

//        for (int t = 0; t< NUM_THREADS; ++t){
//            threads[t]= new Thread(new FactorsRunnable(numbers[t]));
//            threads[t].start();
//        }
//
//        for (int t = 0; t< NUM_THREADS; ++t){
//            threads[t]= new FactorizerWithAHCThread(numbers[t]);
//            threads[t].start();
//        }
        for (int t = 0; t< NUM_THREADS; ++t){
            threads[t]= new FwCThread(numbers[t]);
            threads[t].start();
        }

    }

}
