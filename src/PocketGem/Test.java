package PocketGem;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tianbingleng on 10/10/2017.
 */
public class Test {
    public static void main (String[] args) {
        String test1 = "1 + 3 * 2 - 5 / 2";  // 1 + 6 + 5 = 12
        System.out.println(mysolution(test1));
        System.out.println(caclulator(test1));
        System.out.println(exercise(test1));

        String test2 = "1 + 3 * 2 ^ 2 ^ 2 / 4 + 4/2";
        System.out.println(mysolution(test2));
        System.out.println(caclulator(test2));
        System.out.println(exercise(test2));

    }
    // +*^
    // too many orders, we need to set some priorities
    // numbers stack
    // ops stack

    static public int exercise(String input) {
        // sign - last round, char
        // curr - int
        if (input == null || input.length() == 0) {
            return 0;
        }

        HashMap<Character, Integer> map = new HashMap<>();
        map.put('+', 1);
        map.put('-', 1);
        map.put('*', 2);
        map.put('/', 2);
        map.put('^', 3);

        Deque<Integer> numStack = new ArrayDeque<>();
        Deque<Character> opStack = new ArrayDeque<>();

        input = input.trim().replace(" ", "");

        int i = 0;
        while (i < input.length()) {
            int curr = 0;
            while (i < input.length() && Character.isDigit(input.charAt(i))) {
                curr = curr * 10 + input.charAt(i) - '0';
                i++;
            }

            numStack.push(curr);

            if (i == input.length()) {
                break;
            }

            char c = input.charAt(i);
            if (opStack.isEmpty() || map.get(c) > map.get(opStack.peek())) {
                opStack.push(c);
            } else {
                while (!opStack.isEmpty() && map.get(c) <= map.get(opStack.peek())) {
                    helper(numStack, opStack);
                }
                opStack.push(c);
            }

            i++;
        }

        while (!opStack.isEmpty()) {
            helper(numStack, opStack);
        }
        return numStack.pop();
    }

    static public int mysolution(String input) {
        if (input == null || input.length() == 0) {
            return 0;
        }
        // replace all spaces first
        input = input.trim().replace(" ","");
        Deque<Integer> numStack = new ArrayDeque<>();
        Deque<Character> opStack = new ArrayDeque<>();
        Map<Character, Integer> map = new HashMap<>();
        map.put('+', 1);
        map.put('-', 1);
        map.put('*', 2);
        map.put('/', 2);
        map.put('^', 3);
        int curr = 0;
        for (int i = 0; i < input.length(); i++) {
            curr = 0;
            while (i < input.length() && Character.isDigit(input.charAt(i))) {
                curr = curr * 10 + input.charAt(i) - '0';
                i++;
            }

            if (i == input.length()) {
                break; //remember to add the last element into the stack!!
            }

            //push current number into stack first
            numStack.push(curr);

            // get the current character
            char c = input.charAt(i);
            // current op is > previous, we no need to calculate at the moment
            // since it has highest priority, in the top of stack, we can cal it later

            if (opStack.isEmpty() || map.get(c) > map.get(opStack.peek())) {
                opStack.push(c);
            } else {
                // now it is <= previous, we need to calculate before (high level) first
                while (!opStack.isEmpty() && map.get(c) <= map.get(opStack.peek())) {
                    helper(numStack, opStack);
                }
                opStack.push(c);
            }
            System.out.println("current numStack="+numStack);
            System.out.println("current opStack="+opStack);
            System.out.println("-----------------------------");
        }
        numStack.push(curr);
        while (!opStack.isEmpty()) {
            helper(numStack, opStack);
        }
        // only last element
        return numStack.pop();
    }

    public static int caclulator(String s) {
        s = s.replace(" ", "");
        Deque<Integer> numStack = new ArrayDeque<>();
        Deque<Character> opStack = new ArrayDeque<>();
        Map<Character, Integer> map = new HashMap<>();
        map.put('+', 1);
        map.put('-', 1);
        map.put('*', 2);
        map.put('/', 2);
        map.put('^', 3);
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            } else {
                numStack.push(num);
                num = 0;
                if (opStack.size() == 0 || map.get(c) > map.get(opStack.peek())) {
                    opStack.push(c);
                } else {
                    while (!opStack.isEmpty() && map.get(c) <= map.get(opStack.peek())) {
                        helper(numStack, opStack);
                    }
                    opStack.push(c);
                }
            }
        }
        numStack.push(num);
        while (!opStack.isEmpty()) helper(numStack, opStack);
        return numStack.pop();
    }

    static public void helper(Deque<Integer> numStack, Deque<Character> opStack) {
        int second = numStack.pop();
        int first = numStack.pop();

        char c = opStack.pop();
        if (c == '+') {
            numStack.push(first + second);
        }
        if (c == '-') {
            numStack.push(first - second);
        }
        if (c == '*') {
            numStack.push(first * second);
        }
        if (c == '/') {
            numStack.push(first / second);
        }
        if (c == '^') {
            numStack.push((int)Math.pow(first, second));
        }
    }

}
