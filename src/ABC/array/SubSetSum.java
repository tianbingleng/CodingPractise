package ABC.array;

/**
 * Created by tianbingleng on 8/12/2017.
 */
public class SubSetSum {
    // DP problem
    // The problem is that
    // Partition problem is to determine whether a given set can be
    // partitioned into two subsets such that the sum of elements in both subsets is same.
    // arr[] = {1, 5, 11, 5}
    //    Output: true
    //    The array can be partitioned as {1, 5, 5} and {11}
    //
    //    arr[] = {1, 5, 3}
    //    Output: false
    //    The array cannot be partitioned into equal sum sets.

    // Approach:
    // We first need to get the total sum of the array.
    // if the sum = odd, then just return false, it is IMPOSSIBLE.
    // if the sum = even, we need to get the subSum = sum / 2.

    // It change to another problem -> in an array, find subset of element, has sum of target.
    // Using DP : https://www.youtube.com/watch?v=5td2QH-x5ck
    // Time: O(sum * length of array)
    // Space: O(sum * length of array)

    // the problem will just doing the find subset Sum problem.

    public static void main (String[] args) {
        int[] array = {1, 3, 9, 2};
        int sum = 5;

        //System.out.println("Whether it is possible: "+ solution(array, sum));

        int[] array2 = {3, 1, 4, 2, 2, 1}; // sum = 13

        System.out.println("The Min Diff is: "+ solution2(array2));

    }

    // this question is little similar as question 1.
    // We want to get the MIN diff between two subsets
    // First, get the total sum.
    // Second, iterate from total sum
    public static int solution2(int[] array) {
        int sum = 0;
        for (int i : array) {
            sum += i;
        }

        boolean[][] dp = new boolean[array.length + 1][sum + 1];
        // all default is false
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = true;
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                // copy from last row first, if not taken current value
                dp[i][j] = dp[i - 1][j];

                if (dp[i][j] == false && j >= array[i - 1]) {
                    int lastIndex = j - array[i - 1];
                    dp[i][j] = dp[i - 1][lastIndex];
                }
            }
        }
        printMatrix(dp, array);
        // now since we want to get the MIN diff, so we just go into the middle of the sum
        // j = sum / 2, and keep going down. Because if j + rest set = sum, we want them close
        // so just once we found a result in current j, it must be the smallest
        int diff = Integer.MAX_VALUE;
        for (int j = sum / 2; j >= 0; j--) {
            if (dp[dp.length - 1][j]) {
                // if the last row, current sum is true
                System.out.println("find that, j ="+j);
                diff = sum - 2 * j;
                break;
            }
        }
        return diff;
    }



    //     0  1  2  3  4  5  -> sum
    //  0  T  F  F  F  F  F
    //  1  T  X                 for each X (dp[i][j]), it will get from its top (dp[i - 1][j])
    //  3  T                                            also, left, if any
    //  9  T
    //  2  T
    //  |
    //  set
    /*
    *    012345
        0TFFFFF
        1TTFFFF
        3TTFTTF
        9TTFTTF
        2TTTTTT      -> The last row is the all possibility for the sum.
    * */
    public static boolean solution(int[] array, int target) {
        boolean[][] dp = new boolean[array.length + 1][target + 1];
        // all default is False
        // we still need to know first row is only dp[0][0] = true

        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = true;
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                // copy from last element first
                dp[i][j] = dp[i - 1][j];

                if (dp[i][j] == false && array[i - 1] <= j) {
                    int notAddThisIndex = j - array[i - 1];
                    dp[i][j] = dp[i - 1][notAddThisIndex]; // true then true, false then false
                }
            }
        }
        printMatrix(dp, array);
        return dp[array.length][target];
    }

    public static void printMatrix(boolean[][] matrix, int[] array) {
        System.out.println(" 01234567890123");
        System.out.print("0");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (j == 0 && i > 0) {
                    System.out.print(array[i - 1]);
                }
                if (matrix[i][j]) {
                    System.out.print("T");
                } else {
                    System.out.print("F");
                }

            }
            System.out.println();
        }
    }
}

