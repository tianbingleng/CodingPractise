/**
 * Created by tianbingleng on 21/6/2017.
 */
public class LargestSubArraySum {
    static public void main(String[] args) {
        /*
        Given an unsorted integer array, find the subarray that has the greatest sum. Return the sum.

        Assumptions

        The given array is not null and has length of at least 1.
        Examples

        {2, -1, 4, -2, 1}, the largest subarray sum is 2 + (-1) + 4 = 5

        {-2, -1, -3}, the largest subarray sum is -1

        Follow-up
        (1) Can you optimize Space Complexity in O(1)?
        (2) Can you also provide the start/end index of the largest sub-array?

        */

        int[] array1 = new int[]{2, -1, 4, -2, 1};
        int[] array2 = new int[]{-2, -1, -3};

        System.out.println((largestSubArray(array1)));
        System.out.println((largestSubArray(array2)));

    }
    static public int largestSubArray(int[] array) {
        //Time: O(n)
        //Space: O(n)
        int n = array.length;
        int[] sumArray = new int[n];
        sumArray[0] = array[0];
        int globalMax = Integer.MIN_VALUE;
        int start = 0;
        int global_start = start;
        int global_end = start;
        for (int i = 1; i < n; i++) {
            // try whether has current one has positive contribution (加了之后比本身小)
            if (sumArray[i - 1] + array[i] < array[i]) {
                sumArray[i] = array[i];
                start = i; //new start here
            } else {
                sumArray[i] = sumArray[i - 1] + array[i];
                // nothing change for the start
            }

            if (globalMax < sumArray[i]) {
                globalMax = sumArray[i];
                global_start = start;
                global_end = i; // i is the end, current end
            }
        }

        System.out.println("global_start="+global_start+",global_end="+global_end);

        return globalMax;
    }

    static public int largestSubArray2(int[] array) {
        //Time: O(n)
        //Space: O(1) -using lastMax
        int n = array.length;
        int lastMax = array[0];
        int globalMax = Integer.MIN_VALUE;
        for (int i = 1; i < n; i++) {
            // try whether has current one has positive contribution (加了之后比本身小)
            lastMax = Math.max (lastMax + array[i], array[i]);
            globalMax = Math.max(globalMax, lastMax);
        }

        return globalMax;
    }
}
