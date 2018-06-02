package DrawBridge;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by tianbingleng on 12/11/2017.
 */
public class ValidParenthese {
    public static void main(String[] args) {
        String input1 = "><<<>>>>";
        String input2 = "<><<>>>";
        String input3 = "<<<><>>";
        int max = 2;
        System.out.println(valid(input1, max));
        System.out.println(valid(input2, max));
        System.out.println(valid(input3, max));
    }

    static public boolean valid(String input, int max) {
        Deque<Character> stack = new ArrayDeque<>();
        int countToChange = 0;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '<') {
                stack.push(c);
            } else {
                // it is '>'
                if (stack.isEmpty()) {
                    countToChange++;
                } else {
                    // it is always have '<'
                    stack.pop();
                }
            }
        }
        System.out.print("countToChange="+countToChange);
        if(!stack.isEmpty() || countToChange > max) {
            return false;
        } else {
            return true;
        }

    }

}
