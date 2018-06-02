import java.util.*;

/**
 * Created by tianbingleng on 21/6/2017.
 */
public class ZZZFinal {
    static public void main(String[] args) {

        /*
        Question 0.

        Given an array of strings, find if all the strings can be chained to form a circle.
        Two string s1 and s2 can be chained, iff the last letter of s1 is identical to the first letter of s2.
        For example,   “abc” and “cd” can be chained,
                        “abc” and “dz” can not be chained.
        Example Input: arr[] = {"aaa", "bbb", "baa", "aab"};
        Output: True,
        The given input strings can be chained to form a circle.
        The strings can be chained as "aaa", "aab", "bbb"  and "baa"

        */
        System.out.println("This handle all permutations of the array");
        String[] input = new String[]{"aaa", "bbb", "baa", "aab"};
        List<List<String>> allPermutation = allPermutation(input);
        System.out.println(allPermutation);
        System.out.println(allPermutation.size());

        System.out.println("The answer for this question");
        List<List<String>> allValidChains = allValidChains(input);
        System.out.println(allValidChains);

    }

    public static List<List<String>> allValidChains(String[] input) {
        List<List<String>> result = new ArrayList<>();
        if (input == null || input.length == 0) {
            return result;
        }
        boolean whether = DFSHelper2(input, 0, result);
        System.out.println("find? " + whether);
        return result;
    }

    public static boolean DFSHelper2(String[] input, int currIndex, List<List<String>> result) {
        if (currIndex == input.length - 1) {
            if (canChained(input, currIndex, 0)) {
                result.add(new ArrayList<String>(Arrays.asList(input)));
                return true;
            } else {
                return false;
            }
        }
        // i is always one more than current one, since it is looking for chain
        for (int i = currIndex + 1; i < input.length; i++) {
            // check first, if there is a chain. then we go next
            if (canChained(input, currIndex, i)) {
                swap(input, currIndex + 1, i);
                if (DFSHelper2(input, currIndex + 1, result)) {
                    return true;
                }
                swap(input, currIndex + 1, i);
            }
        }
        // if it is ture, it will return in before, wont touch this
        return false;
    }

    public static boolean canChained(String[] input, int front, int end) {
        return input[front].charAt(input[front].length() - 1) == input[end].charAt(0);
    }


    public static List<List<String>> allPermutation(String[] input) {
        List<List<String>> result = new ArrayList<>();
        if (input == null || input.length == 0) {
            return result;
        }
        DFSHelper(input, 0, result);
        return result;
    }

    public static void DFSHelper(String[] input, int currIndex, List<List<String>> result) {
        if (currIndex == input.length) {
            result.add(new ArrayList<String>(Arrays.asList(input)));
            return;
        }
        for (int i = currIndex; i < input.length; i++) {
            swap(input, currIndex, i);
            DFSHelper(input, currIndex + 1, result);
            swap(input, currIndex, i);
        }
    }

    public static void swap(String[] array, int a, int b) {
        String temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    // Follow up
    // what about many colors? Sort two each time. And then call again.
}
