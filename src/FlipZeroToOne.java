/**
 * Created by tianbingleng on 18/6/2017.
 */
public class FlipZeroToOne {
    static public void main(String[] args) {
//        Given a binary array and an integer m, find the position of zeroes flipping which creates maximum number
//        of consecutive 1’s in array.

        int array[] = new int[]{1, 0, 0, 1, 1, 0, 1, 0, 1, 1};
        int k = 2;
//        Output:  5 7

//        int array[] = new int[]{1, 0, 0, 1, 1, 0, 1, 0, 1, 1, 1};
//        int k = 1;
//        Output:  7

//        int array[] = new int[]{0, 0, 0, 1};
//        int k = 4;
//        Output:  0 1 2
        findZeroes(array, k);
        System.out.println();
        System.out.println("sdfsdf = "+findMaxConsecutiveOnes(array));
    }
    private static void findZeroes(int[] array, int k) {
        // sliding window problem
        // two pointer for in-place movement
        int left = 0, right = 0, countZero = 0;
        // the left index for the output window, also the length of the output window
        int bestWindowLeft = 0, bestWindowSize = 0;

        while (right < array.length) {
            // normally, this if (left index) will not happen at the same time with if (right index)
            // if the number of zeros in the window <= k, we can keep moving right by one position
            if (countZero <= k) {
                if (array[right] == 0) {
                    countZero++;
                }
                right++;
            }

            // normally, this if (left index) will not happen at the same time with if (right index)
            // if the number of zeros in the window > k, we can keep moving left by one position
            if (countZero > k) {
                if (array[left] == 0) {
                    countZero--;
                }
                left++;
            }

            // each time, we need to check whether current the window is the biggest
            // each time, we arrive here,
            // the window can be INVALID, but in this case, since countZero++, right++, we move to left++
            // Thus, the bestWindow size wont update, because the length of current window size is same as
            // last round
            // Only it > than bestWindowSize, it can belong to the new windowSize
            int currentWindowSize = right - left;
            if (currentWindowSize > bestWindowSize) {
                bestWindowLeft = left;
                bestWindowSize = currentWindowSize;
            }
        }
        // Once continue, let's print out the window:
        System.out.print("The maximun size of window is: "+ bestWindowSize +". with '0' index : " );
        for (int i = 0; i < bestWindowSize; i++) {
            if (array[bestWindowLeft + i] == 0) {
                System.out.print(bestWindowLeft + i+", ");
            }
        }

    }

    public static int findMaxConsecutiveOnes(int[] nums) {
        int k = 2;
        int max = 0;
        int flipCount = 0;
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] == 1) {
                max = Math.max(max, fast - slow + 1);
            } else {
                if (flipCount < k) {
                    flipCount++;
                    max = Math.max(max, fast - slow + 1);
                } else {
                    while (nums[slow++] != 0);
                    max = Math.max(max, fast - slow + 1);
                }
            }
        }
        return max;
    }

}
