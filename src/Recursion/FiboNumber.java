package Recursion;

public class FiboNumber {
    /*
    F(0) = 0
    F(1) = 1
    F(2) = F(1) + F(0)
    ...
    F(n) = F(n - 1) + F(n - 2)
    */

    public static void main(String[] args) {
        System.out.println(recursion(0));
        System.out.println(recursion(1));
        System.out.println(recursion(2));
        System.out.println(recursion(3));
        System.out.println(recursion(4));
    }

    // Time O(2^n) - total number of tree nodes ~= the deepest level of the nodes.
    // Space O(n) - call stack, the level of the tree.
    static int recursion(int n) {
        if (n == 0 || n == 1) {
            return n;
        } else {
            return recursion(n - 1) + recursion(n - 2);
        }
    }
}
