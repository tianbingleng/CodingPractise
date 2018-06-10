package ABC.stack;

/**
 * Created by tianbingleng on 5/12/2017.
 */
public class StackTest {
    public static void main(String[] args) {
        ArrayStack<Integer> stack = new ArrayStack<Integer>(6);
        stack.push(1);
        stack.push(3);
        System.out.println(stack.pop()); // 3
        System.out.println(stack.peek()); // 1
        stack.push(2);
        stack.push(10);
        stack.push(5);
        stack.push(4);
        stack.push(8);
        System.out.println(stack.peek()); // 8
        System.out.println(stack.pop()); // 8
        System.out.println(stack.peek()); // 4
        stack.push(9);
        stack.push(11); // exception : stack is full
    }
}
