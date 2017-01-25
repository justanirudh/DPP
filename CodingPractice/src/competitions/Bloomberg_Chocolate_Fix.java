package competitions;

import java.util.Scanner;

/**
 * Created by paanir on 1/24/17.
 */
public class Bloomberg_Chocolate_Fix {


    public static void main(String[] args) {

        Scanner stdin = new Scanner(System.in);

        int price = Integer.parseInt(stdin.nextLine());
        System.out.println(price);

        int wrapPerChoc = Integer.parseInt(stdin.nextLine());
        System.out.println(wrapPerChoc);

        int numPpl = Integer.parseInt(stdin.nextLine());
        System.out.println(numPpl);

        for( int i = 0; i < numPpl; i++ )
        {
            int money = Integer.parseInt(stdin.nextLine());
            System.out.println("ppl: " + money);
            int numChocs = money/price;
            int wraps =  numChocs;

            while(wraps >= wrapPerChoc){
                numChocs += wraps/wrapPerChoc;
                int leftout = wraps % wrapPerChoc;
                wraps = wraps/wrapPerChoc + leftout;
            }

            System.out.println(numChocs);
        }
        stdin.close();
    }
}
