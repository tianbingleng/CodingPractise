package ABC.array;

/**
 * Created by tianbingleng on 9/12/2017.
 */
public class SubSequence {
    public static void main(String[] args) {

    }

    // This has two problem
    // Q1. is subsequence
    //https://leetcode.com/problems/is-subsequence/discuss/
    //https://www.youtube.com/watch?v=yZWmS6CXbQc



    // Q2. How many distinct sub-sequence between two string
    //https://leetcode.com/problems/distinct-subsequences/description/
    // Given a string S and a string T, count the number of distinct sub-sequences of S which equals T.

    // A subsequence of a string is a new string which is formed from the original string
    // by deleting some (can be none) of the characters without disturbing the relative positions
    // of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).

    // Here is an example:
    // S = "rabbbit", T = "rabbit"

    // Return 3.
    static public int solution(String s, String t) {
        // String s is the LONG string - rabbbit
        // String t is the SHORT string - rabbit
        // We using DP, int table DP[i][j],
        // i is for SHORT STRING, t[i]
        // j is for LONG STRING, s[j]
        // DP[i][j] is 当前 how many distinct ways s[j] has t[i] 的个数

        //    ""  r  a  b  b  b  i  t  ---> s[j]
        // ""  1  1  1  1  1  1  1  1
        //  r  0
        //  a  0
        //  b  0
        //  b  0
        //  i  0
        //  t  0
        // t[i]

        int[][] dp = new int[t.length() + 1][s.length() + 1];
        // all default are 0

        // set first row as 1. because they will all can have one way contain "" empty string
        for (int j = 0; j < dp[0].length; j++) {
            dp[0][j] = 1;
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                // start from row = 1, col = 1 (first char in s and t)

                // IT IS ALWAYS CLEAR, dp[i][j] at least = dp[i][j-1]
                // We don't count current new jth element in String s
                //         [j]     [i]
                // e.g. rab[b] & ra[b] = rab & rab + sth if any
                //         [j]     [i]
                //      rab[c] & ra[b] = rab & rab + sth if any

                dp[i][j] = dp[i][j - 1]; // dont take the current jth first

                // NOW we need to check if S[jth] == T[ith], we need add more results
                if (s.charAt(j - 1) == t.charAt(i - 1)) {
                    // it means current S[jth] == T[ith] is one combination, we need to add previous all result
                    // the result removed the current S[jth] == T[ith], which is dp[i-1][j-1]
                    // e.g. rab[b] & ra[b] =   rab & rab  ------> only remove j,
                    //                         + dp(rab  , ra  )  -----> since S[jth] == T[ith], we remove both i,j
                    //                                [b]    [b] , both [b] are removed, we just use last as result
                    dp[i][j] += dp[i - 1][j - 1];
                }
            }
        }
        return dp[dp.length - 1][dp[0].length - 1];
    }
}
