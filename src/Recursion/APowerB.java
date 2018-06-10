package Recursion;

public class APowerB {
    /*
    get the a^b
    */

    public static void main(String[] args) {
        System.out.println(recursion(2,1));
        System.out.println(recursion(2,2));
        System.out.println(recursion(2,3));
        System.out.println(recursion(2,4));
        System.out.println(recursion(2,10));
    }

    // Time O(lg n) - it is a linear top to bottom.
    // Space O(lg n) - it is a linear top to bottom.
    // Don't care about the corner case for now, like a = 0, b < 0
    static int recursion(int a, int b) {
        // base case
        if (b == 1) {
            return a;
        }
        int half_result = recursion(a, b / 2);
        if (b % 2 == 1) {
            return half_result * half_result * a;
        } else {
            return half_result * half_result;
        }
    }
}
