package competitions_and_interviews;

/**
 * Created by paanir on 11/15/17.
 */
class Rubrik {

    //precondition: square matrix
    static public int minSwaps(int[][] mat, int k) {
        int side = mat.length;
        int minCount = Integer.MAX_VALUE;
        for (int i = 0; i < side - k + 1; ++i) {
            for (int j = 0; j < side - k + 1; ++j) {
                int currCount = 0;
                for (int ii = i; ii < k + i; ++ii) {
                    for (int jj = j; jj < k + j; ++jj) {
                        if (mat[ii][jj] == 0)
                            currCount++;
                    }
                }
                if (currCount < minCount)
                    minCount = currCount;
            }
        }
        return minCount;
    }

// i,j -> i+1,j
// M(i,j) <- number of 0s from i, j -> i+k, j
// S(i, j) <- number of 0s in k*k matrix with left-top i,j

// S(i+1,j) = S(i,j) - M(i,j) + M(i+1,j)


    public static void main(String[] args) {
//   Ex:
// N = 4, k = 2
// 0 0 1 0
// 1 0 0 0
// 1 1 0 0
// 0 0 0 0
        int[][] mat = {{0, 0, 1, 0}, {1, 0, 0, 0}, {1, 1, 0, 0}, {0, 0, 0, 0}};
        int k = 2;
        System.out.println(minSwaps(mat, k));

    }
}
