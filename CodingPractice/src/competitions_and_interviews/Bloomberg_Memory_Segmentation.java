package competitions_and_interviews;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by paanir on 1/24/17.
 */
public class Bloomberg_Memory_Segmentation {
    public static void main(String[] args) {

        Scanner stdin = new Scanner(System.in);

        int numFiles = Integer.parseInt(stdin.nextLine());
        int totalSize = 0;
        ArrayList<Integer> fileSizes = new ArrayList<>();
        for( int i = 0; i < numFiles; i++ )
        {
             fileSizes.add(Integer.parseInt(stdin.nextLine()));

        }

        int sectionSize = Integer.parseInt(stdin.nextLine());

        int sum = 0;

        for(int i = 0; i < fileSizes.size(); ++i){
            int fileSize = fileSizes.get(i);

            int sizeReqd = sectionSize;
            int sections  = 1;
            while(sizeReqd < fileSize){
                sections++;
                sizeReqd =  sections *  sectionSize;
            }
            sum += sizeReqd;
        }

        System.out.println(sum);
        stdin.close();
    }
}
