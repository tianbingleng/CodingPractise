package ABC.array;

/**
 * Created by tianbingleng on 5/12/2017.
 */
public class MaxSumSubarray {
    /*
    * You are given a one dimensional array that may contain both positive and negative integers,
    * find the sum of contiguous subarray of numbers which has the largest sum.

    For example, if the given array is {-2, -5, 6, -2, -3, 1, 5, -6},
                                                ---------------
    then the maximum subarray sum is 7 (see highlighted elements).
    * */

    public static void main(String[] args) {
        //int[] array = {-2, -5, 6, -2, -3, 1, 5, -6}; // 7, start=2, end=6
        int[] array = {1, 2, 4, -1, -2, 10, -1}; // 14, start=0, end=5

        System.out.println(maxSum(array));
        System.out.println(maxSumDP(array));
        System.out.println(maxSumDPStartEnd(array));
    }

    public static int maxSum(int[] array) {
        int maxSum = 0;
        for (int i = 0; i < array.length; i++) {
            int currSum = 0;
            for (int j = i; j < array.length; j++) {
                currSum += array[j];
                maxSum = Math.max(maxSum, currSum);
            }
        }
        return maxSum;
    }

    public static int maxSumDP(int[] array) {
        int globalSum = Integer.MIN_VALUE;
        int currSum = 0;
        for (int i = 0; i < array.length; i++) {
            currSum = Math.max(currSum + array[i], array[i]);
            globalSum = Math.max(globalSum, currSum);
        }

        return globalSum;
    }

    public static int maxSumDPStartEnd(int[] array) {
        int globalSum = Integer.MIN_VALUE;
        int currSum = 0;
        int start = 0;
        int end = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > currSum + array[i]) {
                currSum = array[i];
                start = i;
            } else {
                currSum = currSum + array[i];
            }
            if (currSum > globalSum) {
                globalSum = currSum;
                end = i;
            }
        }
        System.out.println("start="+start+", end="+end);
        return globalSum;
    }

}

