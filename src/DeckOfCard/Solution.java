package DeckOfCard;

import java.util.Arrays;

/**
 * Created by tianbingleng on 1/11/2017.
 */
public class Solution {

    public static void main(String[] args) {
        int[] array = new int[100];
        for (int i = 1; i <= 100; i++) {
            array[i - 1] = i;
        }
        System.out.println(Arrays.toString(array));
        solution(array);
    }

    public static void solution(int[] array) {
        if (array == null || array.length == 0) {
            return;
        }
        for (int num : array) {
            if (num % 3 == 0 && num % 5 == 0) {
                System.out.println("fizzbuzz");
            } else if (num % 3 == 0) {
                System.out.println("fizz");
            } else if (num % 5 == 0) {
                System.out.println("buzz");
            } else {
                System.out.println(num);
            }
        }
    }

}
