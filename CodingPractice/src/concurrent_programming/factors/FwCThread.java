package concurrent_programming.factors;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 * Created by anirudh on 4/9/16.
 */
//Correct way to call a static method from different threads
public class FwCThread extends Thread{

    BigInteger num;

    FwCThread(BigInteger num){this.num = num;}

    @Override
    public void run(){
        ArrayList<BigInteger> factors = FactorizerWithCacheStatic.getFactors(num);
        for(BigInteger x: factors){
            System.out.print(x + " ");
        }
        System.out.println();
    }
}
