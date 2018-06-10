package PocketGem;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by tianbingleng on 10/10/2017.
 */
public class CalculatorI {
    public static void main (String[] args) {
        String test1 = "7*2 + 3*3 - 5/2"; //14+9-2=21
        String test2 = "7 + 2 * 7 + 9";
        System.out.println(sol1stack(test1));
        System.out.println(sol1prevalue(test1));

        System.out.println(sol1stack(test2));
        System.out.println(addMultiple(test2));

    }
    // + *
    // scan one by one (digit, sign)
    // each iteration, we need to keep track [current value], and its [last sign]
    // 1 + 2
    //   +   last sign
    //     2 current value
    // initially it is +
    // if it is +- just push the current element into stack
    // if it is */ we need to pop the stack, to get lst element, and then push result into stack

    static public int addMultiple(String input) {
        if (input == null || input.length() == 0) {
            return 0;
        }
        input = input.trim().replace(" ", "");
        int i = 0;
        char sign = '+';
        Deque<Integer> stack = new ArrayDeque<>();
        while (i < input.length()) {
            int curr = 0;
            while (i < input.length() && Character.isDigit(input.charAt(i))) {
                curr = curr * 10 + input.charAt(i) - '0';
                i++;
            }

            //we have the curr
            if (sign == '+') {
                stack.push(curr);
            }
            if (sign == '*') {
                stack.push(stack.pop() * curr);
            }

            if (i == input.length()) {
                break;
            }

            sign = input.charAt(i);
            i++;
        }
        int result = 0;
        while (!stack.isEmpty()) {
            result += stack.pop();
        }
        return result;
    }


    // +-*/
    // using one stack to store each element
    // finally sum together
    // time O(n)
    // space O(n)
    static public int sol1stack(String input) {
        if (input == null || input.length() == 0) {
            return 0;
        }
        // replace all spaces first
        input = input.trim().replace(" ","");
        Deque<Integer> stack = new ArrayDeque<>();
        int result = 0;
        char sign = '+';
        for (int i = 0; i < input.length(); i++) {
            int curr = 0;
            while (i < input.length() && Character.isDigit(input.charAt(i))) {
                curr = curr * 10 + input.charAt(i) - '0';
                i++;
            }
            // the last one
            if (sign == '+') {
                stack.push(curr);
            } else if (sign == '-') {
                stack.push(-curr);
            } else if (sign == '*') {
                stack.push(stack.pop() * curr);
            } else if (sign == '/'){
                stack.push(stack.pop() / curr);
            }
            //last element has been scanned
            if (i == input.length()) {
                break;
            }
            // current sign
            sign = input.charAt(i);
        }
        for (int element : stack) {
            result += element;
        }
        return result;
    }

    // +-*/
    // using one preValue to store first
    // then do calculation in current char
    // time O(n)
    // space O(1)
    static public int sol1prevalue(String input) {
        if (input == null || input.length() == 0) {
            return 0;
        }
        // replace all spaces first
        input = input.trim().replace(" ","");
        Deque<Integer> stack = new ArrayDeque<>();
        int result = 0;
        int preValue = 0;
        char sign = '+';
        for (int i = 0; i < input.length(); i++) {
            int curr = 0;
            while (i < input.length() && Character.isDigit(input.charAt(i))) {
                curr = curr * 10 + input.charAt(i) - '0';
                i++;
            }
            // the last one
            // ...  + curr
            // ...  - curr
            // prev
            // ...  * curr
            // ...  / curr
            if (sign == '+') {
                result += preValue;
                preValue = curr;
            } else if (sign == '-') {
                result += preValue;
                preValue = -curr;
            } else if (sign == '*') {
                preValue *= curr;
            } else if (sign == '/'){
                preValue /= curr;
            }
            // a + b
            //last element has been scanned
            if (i == input.length()) {
                result += preValue;
                break;
            }
            // current sign
            sign = input.charAt(i);
        }
        return result;
    }


}
