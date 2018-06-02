/**
 * Created by tianbingleng on 21/6/2017.
 */
public class ArrayHopper {
    static public void main(String[] args) {
        /*
        Question 1.
        Given an array A of non-negative integers, you are initially positioned at index 0 of the array.
        A[i] means the **maximum** jump distance from that position (you can only jump towards the end of the array).
        Determine if you are able to reach the last index.

        Assumptions
        The given array is not null and has length of at least 1.

        Examples

        {1, 3, 2, 0, 3}, we are able to reach the end of array(jump to index 1 then reach the end of the array)

        {2, 1, 1, 0, 2}, we are not able to reach the end of array
        */

        int[] array1 = new int[]{1, 3, 2, 0, 3};
        int[] array2 = new int[]{2, 1, 1, 10, 2};

        System.out.println((canJump(array1)));
        System.out.println((canJump(array2)));
        // true & false


        /*
        Question 2.
        Given an array A of non-negative integers, you are initially positioned at index 0 of the array. A[i] means the maximum jump distance from index i (you can only jump towards the end of the array). Determine the minimum number of jumps you need to reach the end of array. If you can not reach the end of the array, return -1.

        Assumptions
        The given array is not null and has length of at least 1.

        Examples
        {3, 3, 1, 0, 4}, the minimum jumps needed is 2 (jump to index 1 then to the end of array)
        {2, 1, 1, 0, 2}, you are not able to reach the end of array, return -1 in this case.
        */
        int[] array3 = new int[]{2, 3, 1, 1, 4};
        int[] array4 = new int[]{2, 3, 1};
        System.out.println((minJump(array4)));

    }
    static public int minJump(int[] array) {
        // O(n * k), k is maximum of array[i]
        int n = array.length;
        int[] minJumpCount = new int[n];
        minJumpCount[n - 1] = 0;
        for (int i = n - 2; i >= 0; i--) {
            int minReachableCount = Integer.MAX_VALUE;
            boolean reachableMark = false;
            // j to check min step for reachable counts
            for (int j = 1; j <= array[i] && i + j < n; j++) {
                if (minJumpCount[i + j] >= 0) {
                    reachableMark = true;
                    minReachableCount = Math.min(minReachableCount, minJumpCount[i + j]);
                }
            }
            if (reachableMark) {
                minJumpCount[i] = 1 + minReachableCount;
            } else {
                minJumpCount[i] = -1;
            }
        }
//        for (int element : minJumpCount) {
//            System.out.print(element+",");
//        }
//        System.out.println();
        return minJumpCount[0];
    }

    static public boolean canJump(int[] array) {
    /*
        DP 问题，从小问题还是，一点一点组成大问题的答案。
        Base Case: Last Position.
                    M[n-1] = True (because it is the target)
        Induction Rule:
                    M[i] represents whether or not we can reach the target from the i-th position array[i]
                    M[i] = True --> If there exists a M[i+j] where  1 <= j <= array[i]
                           False ---> Else above.

                                 0  1  2  3  4
                           array[x, x, x, x, x]
                                             T
     */

        int n = array.length;
        boolean[] M = new boolean[n];
        M[n - 1] = true; //it is the target
        for (int i = n - 2; i >=0 ; i--) { // i starts from the 2nd last
            boolean existPositionCanReachTarget = false;
            for (int j = 1; j <= array[i]; j++) { // j must bigger than 0, else will return false
                // if there is a position that are true
                if (M[i + j]) {
                    existPositionCanReachTarget = true;
                    break;
                }
            }
            if (existPositionCanReachTarget) {
                M[i] = true;
            } else {
                M[i] = false;
            }
        }
        return M[0];
    }
}
