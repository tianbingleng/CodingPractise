import java.util.*;

/**
 * Created by tianbingleng on 21/6/2017.
 */
public class ZZZString {
    static public void main(String[] args) {

        /*
        Question 1.

        Reverse String.

        Examples
        "abc" → "cba"

        */
        System.out.println("Question 1.");
        String str1 = "abcdefg";
        System.out.println(str1);
        System.out.println(reverse(str1));

        /*
        Question 2a.

        Reverse words in string.

        Examples
        "I love Yahoo" → "Yahoo love I"

        */
        System.out.println("\nQuestion 2a.");
        String str2 = "I love Yahoo";
        System.out.println(str2);
        System.out.println(reverseWords(str2));

        /*
        Question 2b.

        Reverse some character in one word in specific position

        Examples
        "abcdefg" → "fgabcdef"

        */
        System.out.println("\nQuestion 2a.");
        String str3 = "I love Yahoo";
        System.out.println(str3);
        System.out.println(reverseWords(str3));

    }

    private static String reverse(String input) {
        char[] array = input.toCharArray();
        int left = 0;
        int right = array.length - 1;
        while (left < right) {
            swap(array, left, right);
            left++;
            right--;
        }
        return new String(array);
    }

    private static String reverseWords(String input) {
        char[] array = input.toCharArray();
        int start = 0;
        int end = 0;
        while (end < array.length) {
            //reverse each of the word, and finally reverse the whole string
            while (end < array.length && array[end] != ' ') {
                end++;
            }
            // now we need to reverse the covered string (including both sides)
            reverse(array, start, end - 1);
            start = end + 1;
            end++;
        }
        reverse(array, 0, array.length - 1);

        return new String(array);
    }

    private static void reverse(char[] array, int left, int right) {
        while (left < right) {
            swap(array, left, right);
            left++;
            right--;
        }
    }

    private static void swap(char[] array, int a, int b) {
        char temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}
