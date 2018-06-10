package ABC;

/**
 * Created by tianbingleng on 3/12/2017.
 */
public class ArrayRotation {
    public static void main(String[] args) {
        int[] arr1 = {15, 18, 2, 3, 6, 12}; //2

        int[] arr2 = {7, 9, 11, 12, 5}; //4

        int[] arr3 = {7, 9, 11, 12, 15}; //0

        int[] arr4 = {15, 18, 18, 2, 2, 2, 2, 2, 3, 6, 12}; //3
        //            0   1    2   3  4  5 6  7  8  9  10

        int[] arr5 = {7, 7, 1, 7}; //2



        System.out.println(linearSolution(arr1));
        System.out.println(binarySearchSolution(arr1));

        System.out.println(linearSolution(arr2));
        System.out.println(binarySearchSolution(arr2));

        System.out.println(linearSolution(arr3));
        System.out.println(binarySearchSolution(arr3));

        System.out.println(linearSolution(arr4));
        System.out.println(binarySearchSolution(arr4));

        System.out.println(linearSolution(arr5));
        System.out.println(binarySearchSolution(arr5));



    }

    // Integer array. Original, it is sorted, but rotate at some point.
    // Return that point index.
    // ALLOW DUPLICATION OF ELEMENT
    public static int linearSolution(int[] array) {
        for (int i = 1; i < array.length; i++) {
            if (array[i] < array[i - 1]) {
                return i;
            }
        }
        return 0;
    }

    // 12 3 45   [mid] < [right]  go left
    // 51 2 34   [mid] < [right]  go left
    // 45 1 23   mid is the result
    // 34 5 12   [mid] > [right]  go right (mid+1 is the result)
    // 23 4 51   [mid] > [right]  go right


    /*
    In this case, we will not use O(n) time, instead, we use binary search for this problem.
    We assume there are 9 elements in the array, and we just simple have a try.
    [1]234 [5] 678[9] [left] < [mid] < [right] -> go to Left
    [2]345 [6] 789[1] [left] < [mid] > [right] -> go to Right
    [3]456 [7] 891[2] [left] < [mid] > [right] -> go to Right
    [4]567 [8] 912[3] [left] < [mid] > [right] -> go to Right
    [5]678 [9] 123[4] [left] < [mid] > [right] -> go to Right
    [6]789 [1] 234[5] [left] > [mid] < [right]  special case, keep it
    [7]891 [2] 345[6] [left] > [mid] < [right] -> go to Left
    [8]912 [3] 456[7] [left] > [mid] < [right] -> go to Left
    [9]123 [4] 567[8] [left] > [mid] < [right] -> go to Left

    Normal Case: No duplicate
    first:(if) [mid] > [right] -> left = mid + 1 (go to Right)
    then: (else) [mid] < [right] -> right = mid (non-Right)

    Followup Case
    DUPLICATE CASE [mid] < [right] -> right = mid (non-Right)
    (if) [mid] > [right] -> left = mid + 1 (go to Right)
    (else)
           (if)   [mid] == [right] -> right-- [3,3,1,3] example
           (else) [mid] < [right] -> right = mid (non-Right)
*/

    // this is for NOT duplicate
    // and work for all situation!!!
    public static int binarySearchSolution(int[] array) {
        int left = 0;
        int right = array.length - 1;

        // if left != right, then keep looping, until left == right
        while (left < right) {
            int mid = (left + right) / 2;
            //array[mid] > array[right]
            if (array[mid] > array[right]) {
                left = mid + 1;
            } else {
                //array[mid] <= array[right]
                if (array[mid] == array[right]) {
                    right--;
                } else {
                    right = mid;
                }
            }
        }

        return left;
    }
}
