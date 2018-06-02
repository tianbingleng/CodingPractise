import java.util.Arrays;

/**
 * Created by tianbingleng on 18/6/2017.
 */
public class BitManipulation2 {
    static public void main(String[] args) {
//        Test Case for isUnique12
        System.out.println(isUnique("student"));//false
        System.out.println(isUnique("abcdefg"));//true

        System.out.println(isUnique2("j*&ydf"));//true
        System.out.println(isUnique2("j*&dydf"));//false


    }

    static public boolean isUnique(String str) {
        /*
        If we check only 26 lower case characters. We just use one integer.
        Because the integer has 32 bits > 26 bits.

        int alphabet = 0 (all record here, if one letter exist, its digit is 1)

        For each letter, we get its position by shifting 1

        (1 << char - 'a') & alphabet == 0 => not exist, otherwise, return false

        If not exist, we need to continue, and we assign that position to 1 by:

        alphabet = alphabet | (1 << char - 'a')

        We then compare it with the alphabet.
        */
        char[] array = str.toCharArray();
        int alphabet = 0;
        for (char element : array) {
            int bitIndex = element - 'a';
            // == 0 means first time, not unique
            if ((alphabet & (1 << bitIndex)) == 0) {
                alphabet = alphabet | (1 << bitIndex);
            } else {
                return false;
            }
        }
        return true;
    }

    static public boolean isUnique2(String str) {
        /*
        If this time we use 256 ASCII code. One integer is not enough.
        We use 256 / 32 = 8 integer to finish the goal.
        Array of alphabet to finish the goal.
        */
        char[] array = str.toCharArray();
        int[] alphabet = new int[8];
        for (char element : array) {
            int bitIndex = element; //it is integer itself
            int row = bitIndex / 32;
            int col = bitIndex % 32;
            // == 0 means first time, not unique
            if ((alphabet[row] & (1 << col)) == 0) {
                alphabet[row] = alphabet[row] | (1 << col);
            } else {
                return false;
            }
        }
        return true;
    }

}