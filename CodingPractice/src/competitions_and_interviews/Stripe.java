package competitions_and_interviews;

import java.util.*;

/**
 * Created by paanir on 10/12/17.
 */
public class Stripe {


    static int[] balance(String[] lines) {

        Map<String, Double> accntToAmt = new HashMap<>();
        List<Double> bals = new ArrayList<>();

        for (int i = 1; i < lines.length; ++i) {
            String line = lines[i];
            String[] parts = line.split(" ");
            if (parts[0].equals("API:")) {
                String rest = parts[1];
                String[] moreParts = rest.split("&");
                double amount = Integer.parseInt(moreParts[0].split("=")[1]); // in cents
                String merchantAccnt = moreParts[1].split("=")[1];

                double amountMinusStripe = amount - (0.029 * amount) - 30; //TODO: round down/up, USD cents

                if (moreParts.length == 2) {
                    if (!accntToAmt.containsKey(merchantAccnt))
                        accntToAmt.put(merchantAccnt, 0.0);
                    accntToAmt.put(merchantAccnt, accntToAmt.get(merchantAccnt) + amountMinusStripe);
                } else { // >2
                    String destAccnt = moreParts[2].split("=")[1];
                    int destAmt = Integer.parseInt(moreParts[3].split("=")[1]);
                    //add to dest account
                    if (!accntToAmt.containsKey(destAccnt))
                        accntToAmt.put(destAccnt, 0.0);
                    accntToAmt.put(destAccnt, accntToAmt.get(destAccnt) + destAmt);
                    //add to merchant account
                    if (!accntToAmt.containsKey(merchantAccnt))
                        accntToAmt.put(merchantAccnt, 0.0);
                    accntToAmt.put(merchantAccnt, accntToAmt.get(merchantAccnt) + amountMinusStripe - destAmt);//TODO: round down/up
                }

            } else { //is BAL
                String rest = parts[1];
                bals.add(accntToAmt.get(rest.split("=")[1]));
            }

        }
        int[] arr = new int[bals.size()];
        for (int i = 0; i < bals.size(); ++i) {
            double withcents = bals.get(i);
            String[] parts = Double.toString(withcents).split(".");
            if(parts.length == 2){
                double decimal = Integer.parseInt(Double.toString(withcents).split(".")[1]);
                if (decimal <= 49)
                    arr[i] = Integer.parseInt(Double.toString(withcents).split(".")[0]);
                else
                    arr[i] = Integer.parseInt(Double.toString(withcents).split(".")[0]) + 1;
            }
            else
                arr[i] =  (int)withcents;
        }
        return arr;

    }

    public static void main(String[] args) {
        String[] strs = {"3",
                "API: amount=1000&merchant=123456789&destination[account]=111111&destination[amount]=877",
        "BAL: merchant=123456789",
        "BAL: merchant=111111"};

        balance(strs);
    }
}
