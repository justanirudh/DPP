package concurrent_programming.factors;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by anirudh on 3/9/16.
 */

//Exposing the static class to all threads
// done here (FwCThread) via Thread override. Can also be done via Runnable implementation
public class FactorizerWithCacheStatic {
    private static BigInteger lastNumber;
    private static ArrayList<BigInteger> lastFactors;
    private static final ReentrantLock lock = new ReentrantLock(); //static as all threads share same lock

    static ArrayList<BigInteger> getFactors(BigInteger num) {
        lock.lock();
        try{
            if (num.equals(lastNumber)) return lastFactors;
        }
        finally{lock.unlock();}

        BigInteger curr = num;
        BigInteger fac = BigInteger.valueOf(2);
        ArrayList<BigInteger> factors =  new ArrayList<BigInteger>() ;
        while (fac.compareTo(curr) <= 0) {
            BigInteger[] divAndRem = curr.divideAndRemainder(fac);
            if (divAndRem[1].equals(BigInteger.ZERO)) {
                factors.add(fac);
                curr = divAndRem[0];  //curr gets smaller
            }
            else {
                fac = fac.nextProbablePrime();  //fac gets bigger
            }
        }
        lock.lock();
        try {
            lastNumber = num;
            lastFactors = factors;
            return factors;
        }finally{ lock.unlock();}

    }
}
