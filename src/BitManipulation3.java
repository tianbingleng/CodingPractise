/**
 * Created by tianbingleng on 18/6/2017.
 */
public class BitManipulation3 {
    static public void main(String[] args) {
//        Test Case for bitReverse
        int x = 11;
        int y = bitReverse(x);
        System.out.println(Integer.toBinaryString(x));
        System.out.println(Integer.toBinaryString(y));


    }

    static public int bitReverse(int num) {
        /*
        Assume we would like to reverse a number, no better integer/double/long.

        First we need to get the size = sizeof(num) * 8 = bits

        We iterate i from 0 to n/2,
                   i from right most to the mid
                   j = n - i - 1, from right most to mid

        Case 1. i and j are same, do thing.

           j         i
           |         |
        0001000xxx0001000

        Case 2. i and j are not same. we need to reverse.
           j         i
           |         |
        0000000xxx0001000
        00010000000001000   (1 << i | 1 << j) only i,j are 1, others are 0.
        --------------------XOR
        0001000xxx0000000
           |         |
           j         i

        Since 0^0 = 0, 1^0 = 1, all the other field will NOT change.
        Only, the one with ^1 will flip its bit.
        */
        int n = 32;
        for (int i = 0; i < n/2; i++) {
            num = swapPairOfBits(num, i, n - i - 1);
        }
        return num;
    }
    static int swapPairOfBits(int num, int i, int j) {
        // a >> b (a shift right b bits)
        // a << b (a shift left b bits)
        int rightBit = (num >> i) & 1;
        int leftBit = (num >> j) & 1;
        if (rightBit != leftBit) {
            num = num ^ ( (1 << i) | (1 << j));
        }
        return num;
    }

}