package competitions;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by paanir on 11/3/17.
 */
public class GrubHub2 {
//    Question 2- Make Change
//    The goal of this interview is to take some finite amount of USD currency and dispense it into regular USD physical currency units. Namely in the form of bills and coins. These are the coins and bills that you must support and their colloquial name:
//
//    Penny → $ 0.01
//    Nickel → $  0.05
//    Dime → $ 0.10
//    Quarter → $ 0.25
//    Dollar → $ 1.00
//    Five → $ 5.00
//    Ten → $ 10.00
//    Twenty → $ 20.00
//    Hundred → $100.00
//
//    Implement a solution
//    You are responsible for defining a data structure for your returns
//
//    Analyze your answer
//    What is the runtime complexity of your solution?
//    Could it be better?
//
//    Write your test
//    Using any frameworking of your choice.
//
//
//
//
//    Support finite amounts of each coin & bill
//    What will you do?
//    Dispense extra of smaller units?
//    Dispense too much money at one unit up?
//    Throw an error?
//    Why?
//
//    Support alternate currencies
//    Euro:
//    Coins 1c, 2c, 5c, 10c, 20c, 50c, €1, €2
//    Notes: €5, €10, €20, €50, €100, €200, €500
//
//    Yen:
//    Coins: ¥1, ¥5, ¥10, ¥50, ¥100, ¥500
//    Notes: ¥1000, ¥2000, ¥5000, ¥10,000
//
//    Pound Sterling: (commonly used)
//    Coins: 1p, 2p, 5p, 10p, 20p, 50p, £1, £2
//    Notes: £5, £10, £20

    //precondition: denoms are in reverse sorted order
//heuristics
//O(total)
    Map<Double, Integer> getChange(double total, double[] denoms) {
        Map<Double, Integer> map = new HashMap<>();
        for (double denom : denoms) { //100, 20
            if (total <= 0)
                break;
            int count;
            count = (int) (total / denom);
            total -= count * denom;
            map.put(denom, count); //100 -> 1, 20 ->1, 10 ->1, 5 ->0, 1 ->0, .25 -> 1
        }//total =10
        return map;
    }


    double[] db = {100, 20, 10, 5, 1, .25, .1, .05, .01};

}
