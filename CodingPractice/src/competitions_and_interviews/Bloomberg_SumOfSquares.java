package competitions_and_interviews;

import java.util.*;

/**
 * Created by paanir on 10/24/17.
 */
/*
Sum Sum Cryptography
Memory Limit: 1024MB Runtime Limit: 5s Score: 150
Alice and Bob are sending encrypted messages to each other. Your task is to intercept and decode the message that Alice has received.

Bob sends Alice a positive integer N) as the message, and Alice will decode each message by solving the equation x2 + y2 + z2 = N where x, y, z are positive non-zero integers.

To create the decoded output, Alice sums up all components of all unique (x,y,z) tuples together.

For example, (1,2,3) and (2,3,4) are unique tuples; (1,2,3) and (2,3,1) are not unique tuples.

Note that an entirely naive solution will timeout!


Input Specifications

The only input line will be a number 3 ≤ N ≤ 3000000.


Output Specifications

The output will contain one positive integer as the decoded result.


Sample Input/Output

INPUT
42
OUTPUT
10
EXPLANATION
For 42, the unique (x, y, z) would be (1, 4, 5), and 1 + 4 + 5 is 10.
INPUT
99
OUTPUT
47
EXPLANATION
For 99, the unique (x, y, z) could be (1, 7, 7), (3, 3, 9), (5, 5, 7), and 1 + 7 + 7 + 3 + 3 + 9 + 5 + 5 + 7 = 47
INPUT
1234566
OUTPUT
478112
EXPLANATION
 */
public class Bloomberg_SumOfSquares {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int target = Integer.parseInt(stdin.nextLine());
        Set<Set<Integer>> list = new HashSet<>();

        int max = (int)Math.sqrt(target);
        int sum = 0;
        for(int i = 1; i <= max; ++i){
            for(int j = 0; j <= max; ++j ){
                for(int k = 0; k <= max; ++k){
                    if( i*i + j*j + k*k == target){
                        Set<Integer> nums = new HashSet<>();
                        nums.add(i);
                        nums.add(j);
                        nums.add(k);
                        if(!list.contains(nums)){
                            list.add(nums);
                            sum += i + j + k;
                        }
                    }
                }
            }
        }
        System.out.println(sum);
    }
}
