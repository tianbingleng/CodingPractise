import java.util.*;

/**
 * Created by tianbingleng on 21/6/2017.
 */
public class ZZZArrayDeduplication {
    static public void main(String[] args) {
        /*
        Question 0.

        Given a sorted integer array, remove duplicate elements. Only keep one.

        Assumptions

        The array is not null

        Examples
        {1, 2, 2, 3, 3, 3} → {1, 2, 3}

        */
        int[] array0 = new int[]{0, 0, 0, 1, 2, 2, 2, 3, 3, 3, 4};
        int[] array0_copy = new int[]{0, 0, 0, 1, 2, 2, 2, 3, 3, 3, 4};
        System.out.println("Question 0. Only Keep One.");
        System.out.println(Arrays.toString(array0));
        System.out.println(Arrays.toString(dedup_keepOne(array0)));


        /*
        Question 1.

        Given a sorted integer array, remove duplicate elements. For each group of elements with the same value keep at most two of them.
        Do this in-place, using the left side of the original array and maintain the relative order of the elements of the array.
        Return the array after deduplication.

        Assumptions

        The array is not null

        Examples
        {1, 2, 2, 3, 3, 3} → {1, 2, 3}

        */
        System.out.println("Question 1. Keep At Most Two.");
        int[] array1 = new int[]{1, 1, 1, 2, 2, 2, 3, 3, 3};
        int[] array1_copy = new int[]{1, 1, 1, 2, 2, 2, 3, 3, 3};
        int[] array2 = new int[]{1, 2, 3, 4, 4, 4, 5, 5, 5};
        int[] array2_copy = new int[]{1, 2, 3, 4, 4, 4, 5, 5, 5};
        System.out.println(Arrays.toString(array1));
        System.out.println(Arrays.toString(dedup_keepTwo(array1)));

        System.out.println("---------------");
        System.out.println(Arrays.toString(array2));
        System.out.println(Arrays.toString(dedup_keepTwo(array2)));


        System.out.println("Question 1. Keep At Most --- K.");

        int[] array3 = new int[]{1, 1, 1, 1, 2, 2, 2, 2, 3};
        int[] array3_copy = new int[]{1, 1, 1, 1, 2, 2, 2, 2, 3};
        int[] array4 = new int[]{1, 2, 3, 3, 3, 3, 3, 5, 5};

        System.out.println(Arrays.toString(array3));
        System.out.println(Arrays.toString(dedup_keepK(array3, 3)));

        System.out.println("---------------");
        System.out.println(Arrays.toString(array4));
        System.out.println(Arrays.toString(dedup_keepK(array4, 3)));

        /*
        Question 2.

        Given a sorted integer array, remove duplicate elements.
        For each group of elements with the same value do not keep any of them.
        Do this in-place, using the left side of the original array and and maintain the relative order of the elements of the array.
        Return the array after deduplication.

        Assumptions

        The given array is not null
        Examples

        {1, 2, 2, 3, 3, 3} → {1}
        */

        System.out.println("Question 2. Only Keep Unique.");

        int[] array5 = new int[]{1, 2, 3, 3, 3, 3, 3, 5, 5};
        int[] array5_copy = new int[]{1, 2, 3, 3, 3, 3, 3, 5, 5};

        System.out.println(Arrays.toString(dedupForUnique(array5)));


        /*
        Question 3.
        Given an unsorted integer array, remove adjacent duplicate elements repeatedly, from left to right.
        For each group of elements with the same value do not keep any of them.
        Do this in-place, using the left side of the original array. Return the array after deduplication.

        Assumptions
        The given array is not null

        Examples
        {1, 2, 3, 3, 3, 2, 2} → {1, 2, 2, 2} → {1}, return {1}
        */
        int[] array6 = new int[]{1, 2, 3, 3, 3, 3, 3, 4, 5, 5};

        System.out.println(Arrays.toString(dedupAdjacentRepeat(array6)));

        /*
        Question 4.
        Based on Question 3, now only delete the array which are repeated 3 times (>=3) 消消乐.

        Assumptions
        The given array is not null

        Examples
        {1, 2, 3, 3, 3, 2, 2, 1} → {1, 2, 2, 2, 1} → {1, 1}, return {1, 1}
        */

        int[] array7 = new int[]{1, 2, 3, 3, 3, 2, 2, 1};
        int[] array8 = new int[]{1, 2, 3, 3, 3, 2, 1};
        System.out.println("Question 4. 消消乐.");
        System.out.println(Arrays.toString(array8));
        System.out.println(Arrays.toString(dedupAdjacentRepeatK(array8, 3)));

    }

    static public int[] testHaha(int[] array) {
        if (array.length <= 1) {
            return array;
        }
        Deque<Integer> stack = new ArrayDeque<>();
        int i = 0;
        while (i < array.length) {
            boolean repeated = false;
            while (!stack.isEmpty() && i < array.length && stack.peek() == array[i]) {
                repeated = true;
                i++;
            }
            if (!repeated) {
                stack.push(array[i]);
                i++;
            } else {
                stack.pop();
            }
        }

        int[] result = new int[stack.size()];
        i = stack.size() - 1;
        while(!stack.isEmpty()) {
            result[i--] = stack.pop();
        }
        return result;

    }

    static public int[] dedup_keepOne(int[] array) {
        if (array.length <= 1) {
            return array;
        }

        int slow = 0;
        int fast;
        for (fast = 1; fast < array.length; fast++) {
            // slow ++ first, then copy array[fast] to the new array[slow]
            // in the very last, fast++ for next iteration
            if (array[slow] != array[fast]) {
                array[++slow] = array[fast];
            }
        }

        // left side of slow (including) will be returned.
        // copyOf(int[] original, int newLength)
        return Arrays.copyOf(array, slow + 1);
    }

    static public int[] dedup_keepTwo(int[] array) {
        if (array.length <= 2) {
            return array;
        }
        // two pointer, left side of slow (excluding) will be returned.
        // This case, array[fast] == array[slow - 2], do nothing, only fast move ++
        // since we enable at most 2 elements can dup, so slow - 2 == still safe
        // 1  1  1  2  2  2
        //       s
        //       f
        // Else, array[fast] != array[slow - 2], we need to update current slow and move both slow/fast
        // This case,
        // 1  1  1  2  2  2
        //       s
        //          f
        // Become ↓
        // 1  1  2  2  2  2
        //          s
        //             f

        int slow = 2;
        for (int fast = 2; fast < array.length; fast++) {
            if (array[fast] != array[slow - 2]) {
                array[slow++] = array[fast];
            }
        }
        // left side of slow (excluding) will be returned.
        // copyOf(int[] original, int newLength)
        return Arrays.copyOf(array, slow);
    }
    // general method, all the thing change to KKKKKKKKKK
    static public int[] dedup_keepK(int[] array, int k) {
        if (array.length <= k) {
            return array;
        }

        int slow = k;
        for (int fast = k; fast < array.length; fast++) {
            if (array[fast] != array[slow - k]) {
                array[slow++] = array[fast];
            }
        }
        // left side of slow (excluding) will be returned.
        // copyOf(int[] original, int newLength)
        return Arrays.copyOf(array, slow);
    }

    static public int[] dedupForUnique(int[] array) {
        if (array.length < 2) {
            return array;
        }
        // the one in the left hand side of slow (exluding) will be returned
        int slow = 0;
        int fast = 0;
        while (fast < array.length) {
            int begin = fast; // third pointer
            while (fast < array.length && array[fast] == array[begin]) {
                fast++;
            }
            // now array[fast] != array[begin]
            // it is the first different one, so the fast will be the new one, can be the begin now
            if (fast == begin + 1) {
                array[slow++] = array[begin];
            }
        }
        // left side of slow (excluding) will be returned.
        // copyOf(int[] original, int newLength)
        return Arrays.copyOf(array, slow);
    }

    static public int[] dedupAdjacentRepeat(int[] array) {
        if (array.length < 2) {
            return array;
        }
        Deque<Integer> stack = new ArrayDeque<Integer>();
        stack.push(array[0]);
        int i = 1;
        while (i < array.length) {
            boolean foundRepeat = false;
            // stack can be empty like 1,1,2,2,2,3
            // too many conditions to consider!!!
            while (!stack.isEmpty() && i < array.length && stack.peek().equals(array[i])) {
                foundRepeat = true;
                i++;
            }
            if (foundRepeat) {
                stack.pop();
            } else {
                stack.push(array[i]);
                i++;
            }
        }
        int[] result = new int[stack.size()];
        i = stack.size() - 1;
        while(!stack.isEmpty()) {
            result[i--] = stack.pop();
        }
        return result;
    }

    static public int[] dedupAdjacentRepeatK(int[] array, int k) {
        if (array.length < 2) {
            return array;
        }
        Deque<Integer> stackValue = new ArrayDeque<Integer>();
        Deque<Integer> stackCount = new ArrayDeque<Integer>();

        int i = 0;
        while (i < array.length) {
            // stack can be empty like [1, 2, 3, 3, 3, 2, 1]
            // too many conditions to consider!!!
            int count = 0;
            int value = array[i];
            while (i < array.length && value == array[i]) {
                count++;
                i++;
            }

//            System.out.println("value="+value);
//            System.out.println("count="+count);


            if (stackValue.isEmpty()) {
                stackValue.push(value);
                stackCount.push(count);
            }
            // if it is not empty, we need to check whether current stack has some one same before
            // same as the top
            else if (stackValue.peek().equals(value)) {
                if (stackCount.peek() + count >= k) {
                    //just disgard current and previous POP
                    stackValue.pop();
                    stackCount.pop();
                } else { // update the latest count
                    stackCount.push(stackCount.pop() + count);
                }
            } else {
                //not same, then we just need to push a new
                //only add the new one which are less than k
                if (count < k) {
                    stackValue.push(value);
                    stackCount.push(count);
                }
            }

        }

        List<Integer> result = new ArrayList<>();
        while (!stackValue.isEmpty()) {
            int value = stackValue.pop();
            int count = stackCount.pop();
            while (count > 0) {
                result.add(0, value);
                count--;
            }
        }
//        System.out.print("current result");
//        System.out.println(result);

        int[] resultArray = new int[result.size()];
        for (int j = 0; j < resultArray.length; j++) {
            resultArray[j] = result.get(j);
        }

        return resultArray;
    }


}
