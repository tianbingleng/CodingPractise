package amazon;
import java.util.*;

public class CommonWords {
    public static void main (String[] args) {
//        String paragraph = "Jack and Jill went to the market to buy bread and cheese. Cheese is Jack's and Jill's favorite food.";
//        String[] bannedArray = {"and", "he", "the", "to", "is"};
//        String paragraph = "Rose is a flower red rose are flower.";
//        String[] bannedArray = {"is", "are", "a"};
        String paragraph = "Jack and Jill went to the market to buy bread and cheese. Cheese is Jack's and Jill's favorite food.";
        String[] bannedArray = {"and", "he", "the", "to", "is", "Jack", "Jill"};
        List<String> banned = Arrays.asList(bannedArray);
        List<String> result = getCommonWords(paragraph, banned);

        System.out.println("Paragraph: "+ paragraph);
        System.out.println("result is: "+ result);
    }

    static public List<String> getCommonWords(String paragraph, List<String> banned) {
        List<String> result = new ArrayList<>();
        if (paragraph == null || paragraph.equals("")) {
            return result;
        }
        // generate tokens strings based on requirement
        String[] tokens = paragraph.toLowerCase().split("\\W+");

        // create set for banned word
        Set<String> banSet = new HashSet<>();
        for (String word : banned) {
            banSet.add(word.toLowerCase());
        }

        Map<String, Integer> wordCount = new HashMap<>();
        int maxCount = 0;

        for (String word : tokens) {
            if (!banSet.contains(word)) {
                wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
                int currentCount = wordCount.get(word);
                if ( currentCount > maxCount ) {
                    result.clear();
                    result.add(word);
                    maxCount = currentCount;
                }
                else if ( currentCount == maxCount ) {
                    result.add(word);
                }
            }
        }

        return result;
    }
}
