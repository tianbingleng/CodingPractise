package audible;

import java.util.Arrays;
import java.util.Stack;

/**
 * Created by tianbingleng on 27/10/2017.
 */
public class PrefixToPostfix {
    static public void main(String[] args) {
        String[] prefixs = {"*34", "+1*23", "+12", "+1**23/14"};
        System.out.println(Arrays.toString(prefixs));
        System.out.println(Arrays.toString(solution(prefixs)));
    }

    static public String[] solution(String[] prefixArray) {
        if (prefixArray == null || prefixArray.length == 0) {
            return new String[]{};
        }
        String[] postfixArray = new String[prefixArray.length];
        for (int i = 0; i < postfixArray.length; i++) {
            postfixArray[i] = convert(prefixArray[i]);
        }
        return postfixArray;
    }
    static public String convert(String prefix) {
        if (prefix == null || prefix.length() == 0) {
            return "";
        }
        Stack<String> stack = new Stack<>();
        for (int i = prefix.length() - 1; i >= 0; i--) {
            char c = prefix.charAt(i);
            if (c >= '0' && c <= '9') {
                stack.push(c + "");
            } else {
                String first = stack.pop();
                String second = stack.pop();
                String order = first + second + c;
                stack.push(order);
            }
        }
        return stack.pop();
    }
}
