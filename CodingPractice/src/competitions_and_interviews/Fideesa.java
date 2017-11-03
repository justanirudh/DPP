package competitions_and_interviews;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Scanner;

/**
 * Created by paanir on 2/4/17.
 */
public class Fideesa {

    public static void findFirstOccurrence() {
        Scanner stdin = new Scanner(System.in);

        String input = stdin.nextLine();

        LinkedHashMap<Character, Boolean> map = new LinkedHashMap<>();

        for (int i = 0; i < input.length(); ++i) {
            Character curr = input.charAt(i);
            if (map.containsKey(curr) && map.get(curr))
                map.put(curr, false);
            else if (!map.containsKey(curr))
                map.put(curr, true);
        }

        for (Character k : map.keySet()) {
            if (!map.get(k)) {
                System.out.println(k);
                break;
            }
        }
    }

    public static void getValidTriangle() {

        Scanner stdin = new Scanner(System.in);

        int cases = Integer.parseInt(stdin.nextLine());

        while (cases != 0) {
            String[] input = stdin.nextLine().split(" ");
            if (input.length != 3) {
                System.out.println("None of these");
                cases--;
                continue;
            }

            int side1 = Integer.parseInt(input[0]);
            int side2 = Integer.parseInt(input[1]);
            int side3 = Integer.parseInt(input[2]);

            if (side1 + side2 < side3 || side1 + side3 < side2 || side2 + side3 < side1) {
                System.out.println("None of these");
                cases--;
                continue;
            }
            HashSet<String> hs = new HashSet<>();
            for (String s : input)
                hs.add(s);
            if (hs.size() == 1)
                System.out.println("Equilateral");
            else if (hs.size() == 2)
                System.out.println("Isosceles");
            else
                System.out.println("None of these");
            cases--;
        }
    }


    public static void main(String[] args) {

    }
}
