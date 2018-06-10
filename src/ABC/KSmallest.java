package ABC;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Created by tianbingleng on 3/12/2017.
 */
public class KSmallest {
    public static void main(String[] args) {
        int[] arr1 = {65, 28, 59, 33, 21, 56, 22, 95, 50, 12, 90, 53, 28, 77, 39};
        int[] arr2 = {65, 28, 59, 33, 21, 56, 22, 95, 50, 12, 90, 53, 28, 77, 39};
        int k = 8; // kth smallest one, index = k - 1

        System.out.println(Arrays.toString(arr1));
        Arrays.sort(arr1);
        System.out.println(Arrays.toString(arr1));
        System.out.println(quickSelect(arr2, k - 1));
        System.out.println(usingHeap(arr2, k));
    }

    /*
    * For this question, no matter K largest or smallest
    * ALWAYS change to K-smallest, because it is easy to sort, no matter quicksort/heap.
    * */

    // Heap
    // K-smallest
    // Approach 1. MinHeap. build heap in all elements O(n), poll the top k times O(klog(n))
    //                      Time = O(n) + O(klog(n))

    // Approach 2. MaxHeap. only keep k elements in the heap. O(k) - build heap time
    //                      then, when a new element X come, compare,
    //                             if X < top, then poll heap, and offer this element into heap
    //                      finally, just return the top of the heap (kth smallest)
    //                      Time = O(k) + O((n-k)log(k)) ----> O(nlogk)

    //MaxHeap
    public static int usingHeap(int[] array, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < array.length; i++) {
            if (i < k) {
                maxHeap.offer(array[i]);
            } else {
                // just use <, it is reasonable!!!! it is smaller anyway
                if (array[i] < maxHeap.peek()) { // <= both OK, tested in leetcode
                    maxHeap.poll();
                    maxHeap.offer(array[i]);
                }
            }
        }
        return maxHeap.poll();
    }




    // QuickSelect - Average Case
    // Pick Pivot, if Pivot can divide the array equally
    // =================== pivot ======================    n
    //                      ========= pivot ===========    n/2
    //                                 ===== pivot ====    n/4
    //                          ...
    // Time = O(n) + O(n/2) + O(n/4) + ... = O(2n) = O(n)


    // QuickSelect - Worst Case
    // Pick Pivot, if Pivot always the largest/smallest
    // ========================================= pivot      n
    // ======================================= pivot        n-1
    // ===================================== pivot          n-2
    // =================================== pivot            n-3
    // ================================= pivot              n-4

    // Time = O(n) + O(n-1) + O(n-2) + ... = O(n^2)
    public static int quickSelect(int[] array, int k) {
        int left = 0;
        int right = array.length - 1;
        return quickSelect(array, left, right, k); // k has been changed
    }

    // 推荐这一种！！不容易错！！记下来
    public static int quickSelect(int[] array, int start, int end, int k) {

        int pivot = array[end];

        // partition the array into two part
        // <<<<<<<<<<<<<<<<<<<<<<  pivot  >>>>>>>>>>>>>>>>>>>>>>
        // less/equal than pivot           more/equal than pivot

        int left = start;

        for (int i = left; i < end; i++) {
            // array[i] will ignore the bigger one
            // left pointer will stay at the bigger one (if any)
            // if we got a small/pivot, we just swap with left pointer
            // in the end, the left pointer should be the place the pivot put in
            if (array[i] <= pivot) {
                swap(array, left, i);
                left++;
            }
        }

        swap(array, left, end);

        //leftPointer is the pivot index

        if (left == k) {
            return array[left];
        } else if (left > k) {
            return quickSelect(array, start, left - 1, k);
        } else {
            return quickSelect(array, left + 1, end, k);
        }
    }


    // 这种方法容易错，corner case太多，不推荐！！！！！
    public static int quickSelect2(int[] array, int left, int right, int k) {

        int pivot = array[right];

        // partition the array into two part
        // <<<<<<<<<<<<<<<<<<<<<<  pivot  >>>>>>>>>>>>>>>>>>>>>>
        // less/equal than pivot           more/equal than pivot

        int leftPointer = left;
        int rightPointer = right - 1;

        while(true) {

            while (leftPointer < right && array[leftPointer] <= pivot) { leftPointer++; }
            // [4, 3, 2] pivot = 2
            //  l  r
            //  right should stop when it is reach 0. or it will out of bound
            while (rightPointer > left && array[rightPointer] > pivot) { rightPointer--; }

            if (leftPointer >= rightPointer) {
                break;
            }
            swap(array, leftPointer, rightPointer);
        }

        swap(array, leftPointer, right);

        //leftPointer is the pivot index

        if (leftPointer == k) {
            return array[leftPointer];
        } else if (leftPointer > k) {
            return quickSelect(array, left, leftPointer - 1, k);
        } else {
            return quickSelect(array, leftPointer + 1, right, k);
        }
    }

    public static void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }



}
