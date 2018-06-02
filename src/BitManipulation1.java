/**
 * Created by tianbingleng on 18/6/2017.
 */
public class BitManipulation1 {
    static public void main(String[] args) {
//        Test Case for isPowerOfTwo
//        int x = 64;
//        int y = x-1;
//        System.out.println(Integer.toBinaryString(x));
//        System.out.println(Integer.toBinaryString(y));
//        System.out.println(Integer.toBinaryString(x&y));
//        System.out.println(isPowerOfTwo(x));

//        Test Case for diffBits
//        int x = 28;
//        int y = 44;
//        System.out.println(Integer.toBinaryString(x));
//        System.out.println(Integer.toBinaryString(y));
//        System.out.println(Integer.toBinaryString(x^y));
//        System.out.println(diffBits(x,y));
//
//
//        System.out.println(Integer.toBinaryString(4));
//        System.out.println(Integer.toBinaryString(14));



    }
    static public boolean isPowerOfTwo(int value){
        /*
        If a number is power of two, it only have 1 digit in its bit representation.
        Because in bit representation:
        the value = 2^(0)*<1/0> + 2^(1)*<1/0> + 2^(2)*<1/0>...
        2 = 2^(1)*<1> + 2^(0)*<0>
        4 = 2^(2)*<1> + 2^(1)*<0> + 2^(0)*<0>
        8 = 2^(3)*<1> + 2^(2)*<0> + 2^(1)*<0> + 2^(0)*<0>
        ....
        That means, only '1' in the bit-representation.
        But how to prove a number only have one '1'????
        Do a value - 1, it will become all 111s in the right of that position.
        e.g. 64 = 1000000
             63 = 0111111
             ------------ &
                  0000000 = 0
        Thus, if the result is 0, the value is a power of two.
        */
        return ((value & (value - 1)) == 0);
    }

    static public int diffBits(int a, int b) {
        /*
        Idea, first get the difference of a ^ b (XOR, get the different part of A, B)
        e.g. x = 28 =  11100
             y = 44 = 101100
             ---------------- XOR (^)
             diff =   110000
        Now, we need to count how many '1' in diff.

        We have a count variable, count = 0,
        each time, we diff & 1, if the result is 1, we will add it to count
        e.g.
        diff =   110000
        1    =   000001
        ----------------- &
                 000000 (the value is 0, no need to count++)

        Then, we shift diff one bit to the right -> diff = diff >>> 1

        e.g.
        diff =   000011
        1    =   000001
        ----------------- &
                 000001 (the value is 1, count++) -> WE FOUND ONE '1' here.

        Continue, until diff = 0, because all the value has been shifted..
         */
        int diff = a ^ b;
        int count = 0;
        while (diff != 0) {
            count += (diff & 1);
            diff = diff >>> 1;
        }
        return count;
    }

}
