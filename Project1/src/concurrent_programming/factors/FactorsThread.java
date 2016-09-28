package concurrent_programming.factors;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 * Created by anirudh on 3/9/16.
 */
public class FactorsThread implements Runnable{
    BigInteger num;

    FactorsThread(BigInteger num){this.num = num;}

    private static long counter; //since static, accessed by each thread

    public long get_counter(){return counter;}

    @Override
    public /*static*/ void run() {
        BigInteger curr = num;
        BigInteger fac = BigInteger.valueOf(2);
        ArrayList<BigInteger> factors =  new ArrayList<BigInteger>();
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
            counter++;
            System.out.print(x + " "); //also prints from other threads
        }
        System.out.println("\nCounter for num " + num + " is " + get_counter());
        counter = 0;
    }

}
