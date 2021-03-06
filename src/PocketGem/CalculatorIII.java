package PocketGem;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by tianbingleng on 10/10/2017.
 */
public class CalculatorIII {
    public static void main (String[] args) {
        String test1 = "5-(8-2)-(3+1) + 7"; //5-6-4+7=2
        String test2 = "(1+(4+5+2)-3)+(6+8)";
        String test3 = "1+2-(3+4)+6";
        System.out.println(sol1stack(test3));

    }

    // +-()
    // when it is +- just do calculation
    // when it is ()
    // when it is ( using stack to tempray store data and sign BEFORE the (
    // when it is ) retrieve the latest stack pop them, to get the result
    // time O(n)
    // space O(stack#)
    static public int sol1stack(String input) {
        if (input == null || input.length() == 0) {
            return 0;
        }
        // replace all spaces first
        input = input.trim().replace(" ","");
        Deque<Integer> stack = new ArrayDeque<>();
        int result = 0;
        int sign = 1;
        for (int i = 0; i < input.length(); i++) {
            int curr = 0;
            while (i < input.length() && Character.isDigit(input.charAt(i))) {
                curr = curr * 10 + input.charAt(i) - '0';
                i++;
            }
            //    0       +      a
            // result    sign   curr

            //    a        +      b
            // result    sign   curr

            // based on last sign
            // the current number +- so far
            result += curr * sign;

            if (i == input.length()) {
                break;
            }
            // get the current character
            char c = input.charAt(i);
            if (c == '+') {
                sign = 1;
            } else if (c == '-') {
                sign = -1;
            } else if (c == '(') {
                stack.push(result); // last number
                stack.push(sign); //last sign
                result = 0;
                sign = 1;
            } else if (c == ')'){
                result = result * stack.pop() + stack.pop();
                // curr = num inside () * last sign + num before ()
            }


            //"1+2     -        3+4)+6";
            //                     )
            // pop2  pop1 * (result)

//            System.out.println("current result ="+result);
//            System.out.println("current sign ="+c);
//            System.out.println("=============================");
        }
        return result;
    }

}
