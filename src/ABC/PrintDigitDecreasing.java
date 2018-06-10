package ABC;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianbingleng on 3/12/2017.
 */
public class PrintDigitDecreasing {
    public static void main(String[] args) {
        int n = 3;

        List<Integer> result2 = solution2(n);
        System.out.println(result2);
    }

    /*
    * Given the value of n (say n=3),
    * print all 3 digit numbers starting from 999 to 000
    * in which all digits are in an decreasing sequence
    * (i.e 987,986 etc… should be printed and 999,000,… shouldn’t be printed).
    * */

    // using string
    // total n level (3) here
    // each level, we iterate 9 times (9-1)
    // total solution (worst case) 9^n
    public static List<Integer> solution2(int n) {
        List<Integer> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder("");
        DFSHelper(9, 0, n, sb, result);
        return result;
    }

    public static void DFSHelper(int startDigit, int currLevel, int n, StringBuilder sb, List<Integer> result) {
        if (currLevel == n) {
            result.add(Integer.parseInt(sb.toString()));
            return;
        }
        for (int i = startDigit; i >= 0; i--) {
            sb.append(i);
            DFSHelper(i - 1, currLevel + 1, n, sb, result);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
