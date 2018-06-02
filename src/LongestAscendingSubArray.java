/**
 * Created by tianbingleng on 21/6/2017.
 */
public class LongestAscendingSubArray {
    static public void main(String[] args) {
        /*
        Given an unsorted array, find the length of the longest subarray in which the numbers are in ascending order.

        Assumptions

        The given array is not null

        Examples

        {7, 2, 3, 1, 5, 8, 9, 6}, longest ascending subarray is {1, 5, 8, 9}, length is 4.

        {1, 2, 3, 3, 4, 4, 5}, longest ascending subarray is {1, 2, 3}, length is 3.
        */

//        int[] array = new int[]{7, 2, 3, 1, 5, 8, 9, 6};
//        int[] array = new int[]{1, 2, 3, 3, 4, 4, 5};
        int[] array = new int[]{1, 2, 3, 3, 4, 4, 5, 6, 7, 6, 5};

        System.out.println((longestSubArray(array)));

    }
    static public int longestSubArray(int[] array) {
        if (array.length == 0) {
            return 0;
        }

        int[] sizeArray = new int[array.length];
        int longestArrayStartIndex = 0;
        sizeArray[0] = 1;

        int longestSize = sizeArray[0];

        for (int i = 1; i < array.length; i++) {
            if (array[i] > array[i - 1]) {
                sizeArray[i] = sizeArray[i - 1] + 1;
                if (sizeArray[i] > longestSize) {
                    longestSize = sizeArray[i];
                    longestArrayStartIndex = i - sizeArray[i] + 1;
                }
            } else {
                sizeArray[i] = 1;
            }
        }
        System.out.println("The start index for longest subArray ="+longestArrayStartIndex);
        return longestSize;
    }
}
