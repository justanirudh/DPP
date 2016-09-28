package concurrent_programming.factors;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by anirudh on 3/9/16.
 */
//Factorizer with Atomic Hit Counter
public class FactorizerWithAHCThread extends Thread{

    BigInteger num;

    FactorizerWithAHCThread(BigInteger num){this.num = num;}

    private static AtomicLong counter= new AtomicLong(0);

    public static long get_counter(){return counter.get();}

    @Override
    public void run() {
        BigInteger curr = BigInteger.valueOf(0).add(num);
        BigInteger fac = BigInteger.valueOf(2);
        ArrayList<BigInteger> factors = new ArrayList<BigInteger>();
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

        System.out.println("\nFactorsThread for number " + num + " are:");
        for(BigInteger x: factors){
            counter.incrementAndGet();
            System.out.print(x + " ");
        }
        System.out.println("\nCounter for num " + num + " is " + get_counter());
        counter= new AtomicLong(0);
    }
}
