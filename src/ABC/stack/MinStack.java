package ABC.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by tianbingleng on 5/12/2017.
 */
public class MinStack {
    //https://leetcode.com/problems/min-stack/description/

    // https://leetcode.com/submissions/detail/119737874/

    /*
    *   Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
        push(x) -- Push element x onto stack.
        pop() -- Removes the element on top of the stack.
        top() -- Get the top element.
        getMin() -- Retrieve the minimum element in the stack.
    * */



    private Deque<Integer> stack;
    private Deque<Integer> minStack;

    public MinStack() {
        stack = new ArrayDeque<>();
        minStack = new ArrayDeque<>();
    }



}
