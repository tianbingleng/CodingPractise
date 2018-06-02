package amazon;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianbingleng on 4/9/2017.
 */
public class LongestPalindrome {
    public static void main(String[] args) {
        // String str="abcba";
        String str = "abaxabaxabybaxabyb";

        System.out.println(longestPalindrome(str));
        // String result=preProcess(str);
        // System.out.println(result);
    }

    public static String longestPalindrome(String str) {
        int n = str.length();
        boolean[][] isPal = new boolean[n][n];
        int maxLen = 0;
        int start = 0;
        // index i is the end of the substring
        for (int i = 0; i < n; i++) {
            // index j is the start of the substring
            for (int j = i; j >= 0; j--) {
                if ((str.charAt(i) == str.charAt(j)) && ((i - j <= 1) || isPal[j + 1][i - 1])) {
                    isPal[j][i] = true;
                    if ((i - j + 1) > maxLen) {
                        maxLen = i - j + 1;
                        start = j;
                    }
                }
            }
        }
        return str.substring(start, start + maxLen);
    }
}