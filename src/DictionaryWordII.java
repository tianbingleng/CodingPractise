import java.util.*;

/**
 * Created by tianbingleng on 21/6/2017.
 */
public class DictionaryWordII {
    static public void main(String[] args) {
        /*
        https://leetcode.com/problems/word-break-ii/description/

        Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences.

        Note:
        The same word in the dictionary may be reused multiple times in the segmentation.
        You may assume the dictionary does not contain duplicate words.

        Example:
        Input:
        s = "catsanddog"
        wordDict = ["cat", "cats", "and", "sand", "dog"]
        Output:
        [
          "cats and dog",
          "cat sand dog"
        ]
        */

        String input = "abcdef";
        String[] dict = {"ab", "cd", "ef", "cdef"};

//        String input = "catsanddog";
//        String[] dict = {"cat", "cats", "and", "sand", "dog"};

//        String input2 = "bcdbcdabc";
//        String[] dict2 = {"abc","bcd","def"};

        System.out.println(wordBreak(input, dict));
//        System.out.println((canBreak(input2, dict2)));
        // true & false
    }

    static private List<String> wordBreak(String s, String[] wordDict) {
        Set<String> set = new HashSet(Arrays.asList(wordDict));
        Map<String, List<String>> used = new HashMap<>();
        return DFSHelper(s, set, used);
    }

    static private List<String> DFSHelper(String s, Set<String> set, Map<String, List<String>> used) {
        if (used.containsKey(s)) {
            return used.get(s);
        }

        if (s.length() == 0) {
            return null;
        }
        List<String> result =  new ArrayList<>();
        for (int i = 1; i <= s.length(); i++) {
            String sub = s.substring(0, i);
            if (set.contains(sub)) {
                List<String> partResult = DFSHelper(s.substring(i), set, used);
                if (partResult == null) {
                    result.add(sub);
                } else {
                    for (String str : partResult) {
                        result.add(sub + " " + str);
                    }
                }
            }
        }
        used.put(s, result);
        return result;
    }
}
