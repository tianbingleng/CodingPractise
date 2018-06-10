package PocketGem;

/**
 * Created by tianbingleng on 14/11/2017.
 */
public class LongestPalindrome {
    static public void main(String[] args) {
        String str1 = "abcdfcba";
        //System.out.println(getLongestPalSequence(str1));
        String str2 = "aba"; //5
        String str3 = "abcba"; //13
//        System.out.println(getTotalCountPalSequence(str3));


    int[] x = {1,2,3,4,5,6,7,8,9,10};
    int i = 0;
    int j = 9;
    while (i < j) {
        int temp = x[i];
        x[i] = 2 * x[j] + 1;
        x[j] = 3 * temp;
        i++;
        j-=2;
    }
    for (int y:x) {
        System.out.print(y);
    }
    //System.out.println(fib(10000));

    }
    static public int fib(int x) {
        if (x == 0) {
            return 0;
        }
        if (x == 1) {
            return 1;
        }
        return fib(x-1)+ fib(x-2);
    }

    /*
    http://www.cnblogs.com/AndyJee/p/4465696.html
    动态规划思想

    对于任意字符串，
    如果头尾字符相同，那么字符串的最长子序列等于去掉首尾的字符串的最长子序列加上首尾；
    如果首尾字符不同，则最长子序列等于去掉头的字符串的最长子序列和去掉尾的字符串的最长子序列的较大者。

    因此动态规划的状态转移方程为：

    设字符串为str，长度为n，dp[i][j]表示第i到第j个字符间的子序列的个数（i<=j），则：

    状态初始条件：dp[i][i] = 1 （i=0：n-1）

    状态转移方程：dp[i][j] =  dp[i+1][j-1] + 2  if（str[i]==str[j]）

                dp[i][j] = max(dp[i+1][j],dp[i][j-1])  if （str[i]!=str[j])
     */

    // this function return the count of chars for the longest pal sequence in the string
    // abcdfcba -> abcdcba (abcfcba) -> 7
    static public int getLongestPalSequence(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        int n = str.length();

        int[][] dp = new int[n][n];
        // assign all i, j into 1
        // which means, it is only one character, just it is 1
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        //printMatrix(dp);

        // i is the first
        // j is the end
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (str.charAt(i) == str.charAt(j)) {
                    dp[i][j] = 2 + dp[i + 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }

        printMatrix(dp);
        // top right corner
        /*
        *   11111357
            01111355
            00111333
            00011111
            00001111
            00000111
            00000011
            00000001
        */
        return dp[0][n - 1];
    }

    /*
    *
    * 给定字符串，求它的回文子序列个数。回文子序列反转字符顺序后仍然与原序列相同。
    * 例如字符串aba中，回文子序列为"a", "a", "aa", "b", "aba"，共5个。
    * 内容相同位置不同的子序列算不同的子序列。
    *
    *
    *
    *
    * 动态规划思想

      对于任意字符串，
      如果头尾字符不相等，则字符串的回文子序列个数就等于去掉头的字符串的回文子序列个数+去掉尾的字符串的回文子序列个数-去掉头尾的字符串的回文子序列个数；
      如果头尾字符相等，那么除了上述的子序列个数之外，还要加上首尾相等时新增的子序列个数，1+去掉头尾的字符串的回文子序列个数，1指的是加上头尾组成的回文子序列，如aa，bb等。

      因此动态规划的状态转移方程为：

    设字符串为str，长度为n，p[i][j]表示第i到第j个字符间的最长子序列的长度（i<=j），则：

    状态初始条件：dp[i][i]=1 （i=0：n-1）

    状态转移方程：
                if（str[i] != str[j]）
                dp[i][j] = dp[i+1][j] + dp[i][j-1] - dp[i+1][j-1]
                           [i][-----中间-----][j]
                              |-----------------     + dp[i+1][j]
                           -----------------|        + dp[i][j-1]
                              |-------------|        - dp[i+1][j-1] (duplicate, need to remove)

                if（str[i] == str[j]）
                dp[i][j] = dp[i+1][j] + dp[i][j-1] - dp[i+1][j-1] + dp[i+1][j-1] + 1
                         = dp[i+1][j] + dp[i][j-1]+1
                           [i][-----中间-----][j]
                              |-----------------     +  dp[i+1][j]
                           -----------------|        +  dp[i][j-1]
                              |-------------|        -  dp[i+1][j-1] (duplicate, need to remove)
                           [i][-----中间-----][j]     +  dp[i+1][j-1] (since i,j is same, we need to include middle with ij)
                                     1                +1  the [i][j] only, like 'aa'

    */

    static public int getTotalCountPalSequence(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        int n = str.length();

        int[][] dp = new int[n][n];
        // assign all i, j into 1
        // which means, it is only one character, just it is 1
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        //printMatrix(dp);

        // i is the first
        // j is the end
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (str.charAt(i) == str.charAt(j)) {
                    dp[i][j] = dp[i + 1][j] + dp[i][j - 1] + 1;
                } else {
                    dp[i][j] = dp[i + 1][j] + dp[i][j - 1] - dp[i + 1][j - 1];
                }
            }
        }

        printMatrix(dp);
        return dp[0][n - 1];
    }

    static public void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
    }
}
