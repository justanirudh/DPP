package competitions.ufpt_tryouts_09182016;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by anirudh on 18/9/16.
 */
/*Description
standard input/output
Statements
The Schengen Agreement was signed by a number of countries to uniform many visa-related questions and to allow tourists from outside of the Schengen area to enter and freely travel within Schengen member states using only one visa. A multiple-entry visa owner can perform many travels to any Schengen member state using a single visa, but migration laws limit the number of days he is allowed to stay there. For any consecutive 180 days a visa owner is only allowed to be inside the Schengen area for no more than 90 days in total.
A tourist has got his 5-year Schengen multiple-entry visa on October 18th 2010, therefore he could travel to and from the Schengen area at any of 5·365 + 1 (2012 was a leap year)  = 1826 days.
Yesterday (October 17th, 2015) was the last day his visa was valid, and the tourist wants to know whether he has broken the migration laws and may face problems with obtaining a new Schengen visa. You are given the information about all trips to the Schengen member states he did using this visa and are to verify the rule about consecutive days for multiple-entry visa holders. According to the Schengen visa rules the day of arrival and the day of departure are considered to be days spent inside the Schengen area.
Input
The first line of the input contains only number of trips n (1 ≤ n ≤ 1826).
The i-th of the following n lines describes the i-th trip with two integers ai and di — the day of arrival to Schengen area and the day of departure respectively (1 ≤ ai ≤ di ≤ 1826). Days are numbered starting from the day the visa was issued.
It is guaranteed that these trips do not overlap, that is, each of 1826 days is a part of no more than one trip. Trips are given in arbitrary order.
Output
Output "Yes" (without quotes) if the tourist has followed the rules and may not worry about a new visa. Print "No" (without quotes) if he needs to start to look for an explanation to give to migration officer.
Sample Input
Input
1
2 91
Output
Yes
Input
1
1 91
Output
No
Input
2
3 91
180 200
Output
No
Input
2
181 270
1 90
Output
Yes
Hint
In the second sample the tourist was in Schengen area for 91 days in the 180-day window which starts on day 1.
In the third sample the tourist was in Schengen area for 91 days in the 180-day window which started on day 2 (89 days from day 3 to day 91 and 2 days from day 180 to day 181).*/
public class Visa {
    public static Scanner s = new Scanner(System.in);
    public static Integer time = 1826;
    public static ArrayList<Integer> allTrips = new ArrayList<>(time);

    public static void readStuff() {
        int i, j, k;
        for (i = 0; i < time; i++) {
            allTrips.add(0);
        }
        Integer trips = Integer.parseInt(s.nextLine());
        while ( trips != 0) {
            Integer num1, num2;
            String line = s.nextLine();
            String[] days = line.split(" ");
            num1 = Integer.parseInt(days[0]);
            num2 = Integer.parseInt(days[1]);
            for(i = num1 - 1; i <= num2 - 1; i++)
                allTrips.set(i, 1);
            trips--;
        }
        for(i = 0; i<time; i++){
            j =  Math.min(i + 180, time) ;
            int sum = 0;
            for(k = i; k<j; k++){
                sum += allTrips.get(k);
            }
            if(sum > 90){
                System.out.println("No");
                return;
            }
        }
        System.out.println("Yes");
        return;
    }

    public static void main(String[] args){
        readStuff();
    }
}
