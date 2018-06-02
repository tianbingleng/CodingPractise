package audible;

/**
 * Created by tianbingleng on 27/10/2017.
 */
public class SumOfSubarray {
    static public void main(String[] args) {
        int[] array1 = {1, 2, 3};
        int[] array2 = {1, 1, 1};
        long result1 = subarraySum(array1);
        long result2 = subarraySum(array2);
        System.out.println(result1);
        System.out.println(result2);
        long result3 = subarraySumLinear(array1);
        long result4 = subarraySumLinear(array2);
        System.out.println(result3);
        System.out.println(result4);
    }

    // n^3 solution
    static public long subarraySum(int[] array) {
        long result = 0;

        if (array == null || array.length == 0) {
            return result;
        }

        for (int start = 0; start < array.length; start++) {
            for (int end = start; end < array.length; end++) {
                long currSum = 0;
                for (int i = start; i <= end; i++) {
                    currSum += array[i];
                }
                result += currSum;
            }
        }
        return result;
    }
    // n solution
    static public long subarraySumLinear(int[] array) {
        long result = 0;

        if (array == null || array.length == 0) {
            return result;
        }

        // computing sum of subarray using formula
        for (int i = 0; i < array.length; i++)
            result += ((long) array[i]) * ((long)(i + 1)) * ((long)(array.length - i));

        // return all subarray sum
        return result ;
    }
}
