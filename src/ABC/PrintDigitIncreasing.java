package ABC;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianbingleng on 3/12/2017.
 */
public class PrintDigitIncreasing {
    public static void main(String[] args) {
        int n = 3;
        List<Integer> result1 = solution1(n);
        System.out.println(result1);

        List<Integer> result2 = solution2(n);
        System.out.println(result2);
    }

    /*
    * Given the value of n (say n=3),
    * print all 3 digit numbers starting from 100 to 999
    * in which all digits are in an increasing sequence
    * (i.e 123,124 etc… should be printed and 100,101,… shouldn’t be printed).
    * */

    // using string
    // total n level (3) here
    // each level, we iterate 9 times (1-9)
    // total solution (worst case) 9^n
    public static List<Integer> solution2(int n) {
        List<Integer> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder("");
        DFSHelper(1, 0, n, sb, result);
        return result;
    }

    public static void DFSHelper(int startDigit, int currLevel, int n, StringBuilder sb, List<Integer> result) {
        if (currLevel == n) {
            result.add(Integer.parseInt(sb.toString()));
            return;
        }
        for (int i = startDigit; i <= 9; i++) {
            sb.append(i);
            DFSHelper(i + 1, currLevel + 1, n, sb, result);
            sb.deleteCharAt(sb.length() - 1);
        }
    }


    // using integer array
    // total n level (3) here
    // each level, we iterate 9 times (1-9)
    // total solution (worst case) 9^n
    public static List<Integer> solution1(int n) {
        List<Integer> result = new ArrayList<>();
        int[] digits = new int[n];
        DFSHelper(0, n, digits, result);
        return result;
    }

    public static void DFSHelper(int currLevel, int n, int[] digits, List<Integer> result) {
        if (currLevel == n) {
            // we gonna generate the result
            int number = generateNumber(digits);
            result.add(number);
            return;
        }
        for (int index = currLevel; index < n; index++) {
            for (int digit = 1; digit <= 9; digit++) {
                if (index == 0) {
                    digits[index] = digit;
                    DFSHelper(index + 1, n, digits, result);
                } else if (digit > digits[index - 1]) {
                    digits[index] = digit;
                    DFSHelper(index + 1, n, digits, result);
                }
            }
        }
    }

    public static int generateNumber(int[] digits) {
        int result = 0;
        for (int digit : digits) {
            result = result * 10 + digit;
        }
        return result;
    }

}
