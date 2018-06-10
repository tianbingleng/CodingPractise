package BinarySearch;

import java.util.Arrays;

/**
 * Created by tianbingleng on 21/6/2017.
 */
public class BinarySearch1 {
    static public void main(String[] args) {

        /*

        Question 0.
        DO THIS FIRST. (AFTER THE first/last occurrence target in the array.  )
        https://leetcode.com/problems/find-smallest-letter-greater-than-target/description/

        Question 1.

        In a sorted array, find the maximum number smaller than target. (The number is exist!!)
        Return the <index> of the number, if not found, return -1.

        */
        System.out.println("Question 1.");
        int[] array0 = new int[]{1, 2, 3, 4, 5, 5, 6, 6, 7, 8};
        System.out.println(MaxSmaller(array0, 8));
        System.out.println(MaxSmaller(array0, 7));
        System.out.println(MaxSmaller(array0, 6));
        System.out.println(MaxSmaller(array0, 5));
        System.out.println(MaxSmaller(array0, 4));

        // UNDONE =======================================
        // UNDONE =======================================
        // UNDONE =======================================

        /*
        Question 2.

        In a rotated sorted array, find the maximum number smaller than target.

        */
        System.out.println("Question 2.");
        int[] array1 = new int[]{3, 4, 5, 6, 7, 8, 1, 2};
        int[] array2 = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        int[] array3 = new int[]{2, 3, 4, 5, 6, 7, 8, 1};
        System.out.println(FindSmallest(array1));
        System.out.println(FindSmallest(array2));
        System.out.println(FindSmallest(array3));

        /*
        Question 3.

        In a rotated sorted array, find the target number. If not found, return -1.

        */
        System.out.println("Question 3.");
        int[] array4 = new int[]{5, 6, 7, 8, 1, 2, 3, 4};
        System.out.println(Arrays.toString(array4));
        System.out.println(FindTarget(array4, 7));
        System.out.println(FindTarget(array4, 8));
        System.out.println(FindTarget(array4, 1));
        System.out.println(FindTarget(array4, 3));


    }

    public static int MaxSmaller(int[] array, int target) {
        if (array == null || array.length == 0) {
            return -1;
        }
        int left = 0;
        int right = array.length - 1;

        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (array[mid] == target) {
                right = mid;
            } else if (array[mid] > target) {
                right = mid;
            } else {
                left = mid;
            }
        }

        if (array[left] == target) {
            return left - 1;
        } else {
            return right - 1;
        }
    }

    public static int FindSmallest(int[] array) {
        /*
            In this case, we will not use O(n) time, instead, we use binary search for this problem.
            We assume there are 9 elements in the array, and we just simple have a try.
            [1]234 [5] 678[9] [left] < [mid] < [right] -> go to Left
            [2]345 [6] 789[1] [left] < [mid] > [right] -> go to Right
            [3]456 [7] 891[2] [left] < [mid] > [right] -> go to Right
            [4]567 [8] 912[3] [left] < [mid] > [right] -> go to Right
            [5]678 [9] 123[4] [left] < [mid] > [right] -> go to Right
            [6]789 [1] 234[5] [left] > [mid] < [right]
            [7]891 [2] 345[6] [left] > [mid] < [right] -> go to Left
            [8]912 [3] 456[7] [left] > [mid] < [right] -> go to Left
            [9]123 [4] 567[8] [left] > [mid] < [right] -> go to Left

            [mid] < [right] -> right = mid (non-Right)
            [mid] > [right] -> left = mid + 1 (to to Right)
        */
        if (array == null || array.length == 0) {
            return -1;
        }

        int left = 0;
        int right = array.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (array[mid] < array[right]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        // now left == right
        return left;
    }

    public static int FindTarget(int[] array, int target) {
        /*
            Based on FindSmallest question, we first find the min value in the array first.
            Next, we define the left and right based on the target.
            Finally, we apply binary search.

            Target = 9.
            1234 [5] 6789 target = [right] -> Return
            2345 [6] 7891 [mid] < target -> go to Right
            3456 [7] 8912 [mid] < target -> go to Right
            4567 [8] 9123 [mid] < target -> go to Right
            5678 [9] 1234 [mid] = [right] -> Return
            6789 [1] 2345 [mid] < [right]
            7891 [2] 3456 [left] > [mid] < [right] -> go to Left
            8912 [3] 4567 [left] > [mid] < [right] -> go to Left
            9123 [4] 5678 [left] > [mid] < [right] -> go to Left

            [mid] < [right] -> right = mid (non-Right)
            [mid] > [right] -> left = mid + 1 (to to Right)
        */
        if (array == null || array.length == 0) {
            return -1;
        }

        int minIndex = FindSmallest(array);
        int left, right;
        if (target <= array[array.length - 1]) {
            left = minIndex;
            right = array.length - 1;
        } else {
            left = 0;
            right = minIndex;
        }

        //simple binary search
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (array[mid] == target) {
                return mid;
            } else if (array[mid] > target){
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        // no result
        return -1;
    }

    }
