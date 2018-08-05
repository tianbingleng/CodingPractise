package amazon;

import java.util.*;

/**
 * Created by tianbingleng on 4/9/2017.
 */
public class GetMaxSlidingWindow {

    /*

    Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Return the max sliding window.

        Example:

    Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
    Output: [3,3,5,5,6,7]
    Explanation:

    Window position                     Max
    ---------------                   -----
    [1  3  -1]  -3   5   3  6  7        3
    1  [3  -1   -3]  5   3  6  7        3
    1   3 [-1   -3   5]  3  6  7        5
    1   3  -1  [-3   5   3] 6  7        5
    1   3  -1   -3  [5   3  6] 7        6
    1   3  -1   -3   5  [3  6  7]       7
    Note:
    You may assume k is always valid, 1 ≤ k ≤ input array's size for non-empty array.

    Follow up:
    Could you solve it in linear time?

    Seen this question in a real interview before?

    */
    public static void main(String[] args) {
        int[] array = new int[]{1, 3 , -1, -3, 5, 3, 6, 7};

        int[] result = maxSlidingWindow(array, 3);
        int[] result2 = maxSlidingWindowDeque(array, 3);

        System.out.println(Arrays.toString(result));
        System.out.println(Arrays.toString(result2));
    }

    // O(n^2) time complexity
    // O(k) space complexity
    static private int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return new int[]{};
        }
        int[] result = new int[nums.length - k + 1];
        for (int i = 0; i < result.length; i++) {
            int max = nums[i];
            for (int j = 1; j < k; j++) {
                max = Math.max(max, nums[i + j]);
            }
            result[i] = max;
        }
        return result;
    }

    // We maintain a deque, [A, B, C, D, E, F] in descending order (A,B,C,D are INDEX)
    // nums[deque.getFirst] will biggest
    // nums[deque.getLast] will smallest
    // Time Complexity: O(n)
    // Space Complexity: O(k)
    static private int[] maxSlidingWindowDeque(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return new int[]{};
        }
        int[] result = new int[nums.length - k + 1];
        Deque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < nums.length; i++) {
            // each time we compare current value nums[i] to the end of the deque
            // if current value >= end of the deque, we keep popping the deque
            while (!deque.isEmpty() && nums[deque.getLast()] <= nums[i]) {
                deque.pollLast();
            }

            // there is also the case, the top value of the deque is just out of the sliding window, then we popped
            if (!deque.isEmpty() && deque.getFirst() == (i - k)) {
                deque.pollFirst();
            }

            // we will add whatever the current index (even it is small)
            deque.offerLast(i);
            System.out.println(i+" round. Deque:"+ deque);
            // we will add the result
            if (i >= k - 1) {
                result[i - k + 1] = nums[deque.getFirst()];
            }
        }
        return result;
    }
}