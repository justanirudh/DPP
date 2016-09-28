package concurrent_programming.account;

/**
 * Created by anirudh on 27/9/16.
 */
public class Account {

    int MINBALANCE = 1000;
    String name;
    String address;
    double balance;

    double compute_fee(double parameter){
        return parameter *2 - 100 *12 - 1;
    }

    double compute_proj(double parameter){
        return parameter *2 - 100 *12 - 1;
    }

    double computeFeeAndDeduct(double param) {
        double fee = compute_fee(param);
        synchronized(this) {
            if (balance < MINBALANCE)
                balance = balance - fee;
            else
                balance = balance - fee/2;
        }
        return fee;
    }

    double getProjectedBalance(double rate) {
        double b, projected_balance;
        synchronized(this){
            b = balance;
        }
        projected_balance = compute_proj(b * rate);
        return projected_balance;
    }

}
