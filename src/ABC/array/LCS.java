package ABC.array;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianbingleng on 9/12/2017.
 */
public class LCS {
    public static void main(String[] args) {
        String s1 = "LCLC";
        String s2 = "CLCL";
        List<String> result1 = longestCommonSubString(s1, s2);
        System.out.println(result1);

    }
    // lintcode
    public static List<String> longestCommonSubString(String s1, String s2) {
        //https://www.youtube.com/watch?v=tABtJbLOQho
        // 看对角线 只看对角线！！
        int len1 = s1.length();
        int len2 = s2.length();
        int[][] match = new int[len1][len2];
        int max = -1;
        List<String> result = new ArrayList<>();
        //DP
        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    if (i == 0 || j == 0) {
                        match[i][j] = 1;
                    } else {
                        match[i][j] = match[i - 1][j - 1] + 1;
                    }
                    // now check whether need to update
                    if (match[i][j] > max) {
                        max = match[i][j];
                        result = new ArrayList<>();
                        result.add(s1.substring(i - max + 1, i + 1));
                    } else if (match[i][j] == max) {
                        result.add(s1.substring(i - max + 1, i + 1));
                    }
                }
            }
        }
        return result;
    }

    // lintcode
    public static int longestCommonSubSequence(String s1, String s2) {
        // https://www.jiuzhang.com/solution/longest-common-subsequence/
        // https://www.youtube.com/watch?v=NnD96abizww
        int len1 = s1.length();
        int len2 = s2.length();
        int[][] match = new int[len1 + 1][len2 + 1];
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    match[i][j] = match[i - 1][j - 1] + 1;
                } else {
                    match[i][j] = Math.max(match[i - 1][j], match[i][j - 1]);
                }
            }
        }
        return match[len1][len2];
    }
}
