package ABC.array;

/**
 * Created by tianbingleng on 9/12/2017.
 */
public class LongestPalindrome {
    public static void main(String[] args) {
        String str="babad";
        //String str = "abaxabaxabybaxabyb";

        System.out.println(longestSubStringPal(str));
        // String result=preProcess(str);
        // System.out.println(result);
    }

    // Question 2. 最长回文子序列
    // https://discuss.leetcode.com/topic/78603/straight-forward-java-dp-solution
    // https://www.youtube.com/watch?v=yZWmS6CXbQc
    public static int longestSubSequencePal(String s) {
        int n = s.length();
        int[][] dp = new int[n][n]; // 不需要加0,col row
        // 从右下角开始，每次(1,22,333,4444的顺序)x'x'x
        // x4444
        // 0x333
        // 00x22
        // 000x1
        // 0000x

        // mark all x to 1
        for (int i = 0; i < dp.length; i++) {
            dp[i][i] = 1;
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                // it is one result
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = 2 + dp[i + 1][j - 1]; // ↙ 的这个
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]); // 左边 和 下边的 最大
                }
            }
        }

        return dp[0][n - 1];
    }


    // Question 1. 最长回文字符串
    // https://leetcode.com/problems/longest-palindromic-substring/description/
    public static String longestSubStringPal(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];

        // all default is false

        int maxLen = 0;
        int maxStart = 0;


        // all are index
        // x - start (col)
        // y - end (row)
        for (int end = 0; end < n; end++) {
            for (int start = end; start >= 0; start--) {
                //  it is same                         it just one elment/next to each other OR its smaller part is true
                if (s.charAt(start) == s.charAt(end) && ((end - start <= 1) || dp[end - 1][start + 1])) {
                    dp[end][start] = true;
                    int currLen = end - start + 1;
                    if (currLen > maxLen) {
                        maxLen = currLen;
                        maxStart = start;
                    }
                }
            }
        }
        printMatrix(dp, n);
        return s.substring(maxStart, maxStart + maxLen); // startIndex inclusive, endIndex exclusive
    }

    public static void printMatrix(boolean[][] matrix,int n) {
        System.out.println(" 012345");
        System.out.print("0");

        int count = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (j == 0 && i > 0) {
                    System.out.print(++count);
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
