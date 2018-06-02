import java.util.HashSet;
import java.util.Set;

/**
 * Created by tianbingleng on 21/6/2017.
 */
public class DictionaryWordI {
    static public void main(String[] args) {
        /*
        Given a word and a dictionary, determine if it can be composed by concatenating words from the given dictionary.

        Assumptions
        The given word is not null and is not empty
        The given dictionary is not null and is not empty and all the words in the dictionary are not null or empty

        Examples
        Dictionary: {“bob”, “cat”, “rob”}
        Word: “robob” return false
        Word: “robcatbob” return true since it can be composed by "rob", "cat", "bob"
        */

        String input = "abcde";
        String[] dict = {"abc","ab","cd","de","def"};

        String input2 = "bcdbcdabc";
        String[] dict2 = {"abc","bcd","def"};


        System.out.println((canBreak(input, dict)));
        System.out.println((canBreak(input2, dict2)));
        // true & false

    }
    static public boolean canBreak(String input, String[] dict) {
        // Write your solution here.
        // O(n^3), substring is O(n)
        // this problem very tricky, take care for the string index, substring(stratIndex, endIndex)
        // substring copy from startIndex (including), to endIndex(not including)
        // Here we start from found[1] to found[length], we omit 0 for easy understanding.
        // found[i] means total i element we are considering...
        boolean[] found = new boolean[input.length() + 1];
        Set<String> set = toSet(dict);
        // from first element to the total element
        for (int i = 1; i <= input.length(); i++) {
            // no cut, from 0 to total size (index = i-1, thus type 'i' will not include)
            if (set.contains(input.substring(0, i))) {
                found[i] = true;
                continue;
            }
            //j how many cut total, j = 1 ~ i - 1 (total i elements)
            // has count, left part = found[j], right part = substring(j,i)
            // For example i = 3, size = 3 "bob"
            // M[3] = M[1] + substring(1,3) ||
            //      = M[2] + substring(2,3)  (Total two cuts)
            for (int j = 1; j < i; j++) {
                if (found[j] && set.contains(input.substring(j, i))) {
                    found[i] = true;
                    break;
                }
            }
        }
        return found[input.length()];

    }
    static public Set<String> toSet(String[] dict) {
        Set<String> set = new HashSet<String>();
        for (String element : dict) {
            set.add(element);
        }
        return set;
    }

}
