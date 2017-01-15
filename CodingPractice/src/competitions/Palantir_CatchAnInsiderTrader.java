package competitions;


import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by paanir on 1/7/17.
 */
public class Palantir_CatchAnInsiderTrader {

    static class Amts {
        Integer amt;
        Integer totalAmt;

        public Amts(Integer amt, Integer totalAmt) {
            this.amt = amt;
            this.totalAmt = totalAmt;
        }
    }

    static class DayAndTrader {
        Integer day;
        String trader;

        public DayAndTrader(Integer day, String trader) {
            this.day = day;
            this.trader = trader;
        }
    }


    static String[] findPotentialInsiderTraders(String[] datafeed) {

        HashMap<Integer, HashMap<String, Amts>> traderInfo = new HashMap<>();
        ArrayList<DayAndTrader> dat = new ArrayList<>();

        Integer recentDate = 0;
        Integer recentPrice = 0;

        for (int i = 0; i < datafeed.length; ++i) {
            String[] args = datafeed[i].split("\\|");
            if (args.length == 2) { //date info
                recentDate = Integer.parseInt(args[0]);
                recentPrice = Integer.parseInt(args[1]);
                if (!traderInfo.isEmpty()) {
                    Integer fromDate = recentDate - 3;
                    for (int j = fromDate; j < recentDate; ++j) {
                        HashMap<String, Amts> allTrades = traderInfo.get(j); //trader to totalAmt

                        for (String trader : allTrades.keySet()) {
                            Integer totalAmtBuyOrSell = allTrades.get(trader).totalAmt;
                            Integer amtBuyOrSell = allTrades.get(trader).amt;

                            Integer totalAmtCurr = amtBuyOrSell * recentPrice;
                            if (totalAmtBuyOrSell - totalAmtCurr > 5000000) {
                                DayAndTrader dt = new DayAndTrader(j, trader);
                                dat.add(dt);
                            }
                        }
                    }
                }
            } else { //trader info
                Integer day = Integer.parseInt(args[0]);
                String traderName = args[1];
                String type = args[2];
                Integer amt = Integer.parseInt(args[3]);

                Integer totalAmt;
                if (type.equals("BUY"))
                    totalAmt = -1 * amt * recentPrice;
                else
                    totalAmt = amt * recentPrice;

                if (traderInfo.containsKey(day)) {
                    HashMap<String, Amts> oldTraderMap = traderInfo.get(day);
                    oldTraderMap.put(traderName, new Amts(amt, totalAmt));
                    traderInfo.put(day, oldTraderMap);
                } else {
                    HashMap<String, Amts> traderMap = new HashMap<>();
                    traderMap.put(traderName, new Amts(amt, totalAmt));
                    traderInfo.put(day, traderMap);
                }
            }

        }
        //obtained day and trader information. Now, need to print it in sorted format
        return new String[3];
    }

    public static void main(String[] args) {

    }
}
