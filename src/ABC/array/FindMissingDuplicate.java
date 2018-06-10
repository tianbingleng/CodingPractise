package ABC.array;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by tianbingleng on 8/12/2017.
 */
public class FindMissingDuplicate {
    // https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/description/
    // In this problem, you are given an integer array, length of n
    // it is supposed composed from number 1 ~ n
    // however, some could be missing, some could be duplicating.
    // find the missing AND duplicate one
    public static void main(String[] args) {
        int[] array = {4,3,2,7,8,2,3,1};
        solution2(array);
        solution3(array);

        int[] array2 = {4, 3, 6, 2, 1, 1};
        solution2(array2);
        solution3(array2);

    }

    // Approach 1.
    // n elements array, not it has n-1 elements
    // we can just use sum from (1..n) then - sum of (array)
    // get that missed one

    // Approach 2.
    // Using set.
    // first scan (array) : if it is exists, -> duplicated, mark it
    // second scan (from 1-100) : if it is missing -> missing, mark it
    // Time: O(n), Space: O(n);

    // Approach 3.
    // Using array index property, using the help of index of the array to be the count
    // first scan
    // when we scan from the (array), we get the proposed position first location = Abs(array[i]) - 1
    // if array[location] > 0, means, first time, we mark as NEGTIVE for that array[location]
    // else (array[location] < 0), means it is duplicated, we put in the result first (array[location])

    // second scan from (i = 1~100), if it is POSITIVE, means never touch, this element (i + 1) is missing

    // Time: O(n), Space: O(1), BUT the array is changed.

    public static void solution2(int[] array) {
        if (array == null || array.length == 0) {
            return;
        }
        Set<Integer> set = new HashSet<>();
        Set<Integer> duplicate = new HashSet();
        for (int element : array) {
            if (set.contains(element)) {
                duplicate.add(element);
            } else {
                set.add(element);
            }
        }
        Set<Integer> missing = new HashSet();
        for (int i = 1; i <= array.length; i++) {
            if (!set.contains(i)) {
                missing.add(i);
            }
        }
        System.out.println("Missing : "+missing);
        System.out.println("Duplicated : " + duplicate);
    }

    public static void solution3(int[] array) {
        if (array == null || array.length == 0) {
            return;
        }
        Set<Integer> missing = new HashSet<>();
        Set<Integer> duplicate = new HashSet();
        for (int i = 0; i < array.length; i++) {
            int location = Math.abs(array[i]);
            if (array[location - 1] > 0) { // mark as visited for this number
                array[location - 1] = - array[location - 1];
            } else { // duplicated
                duplicate.add(location);
            }
        }
        // iterate each element in the array
        for (int i = 0; i < array.length; i++) {
            if (array[i] > 0) {
                missing.add(i + 1);
            }
        }

        System.out.println("Missing : "+missing);
        System.out.println("Duplicated : " + duplicate);
    }

}
