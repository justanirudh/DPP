package competitions;

import java.util.*;

/**
 * Created by paanir on 10/24/17.
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
