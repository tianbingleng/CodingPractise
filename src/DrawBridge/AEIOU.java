package DrawBridge;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by tianbingleng on 12/11/2017.
 */
public class AEIOU {
    public static void main(String[] args) {
        String input1 = "aeioo";
        //String input2 = "aeiouuuuo";
        //String input3 = "aeeiooua";
        System.out.println(solution(input1));
        //System.out.println(solution(input2));
        //System.out.println(solution(input3));
    }

    static public int solution(String input) {
        char[] vowels = {'a', 'e', 'i', 'o', 'u'};
        int nextIndex = 0;
        int length = 0;
        int maxLength = 0;
        boolean newString = true;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            //System.out.println("start: i="+i+",c="+c);
            // if it match
            if (c == 'a') {
                // new string
                if (newString) {
                    length++;
                    nextIndex++;
                    newString = false;
                } else {
                    // old string
                    if (nextIndex == 1) {
                        length++;
                    } else {
                        //need to restart
                        newString = true;
                        nextIndex = 0;
                        length = 0;
                        i--; //stay here
                    }
                }
            } else { // not 'a'
                if (!newString) {
                    if (c != 'u') {
                        // if new next one
                        if (c == vowels[nextIndex]) {
                            length++;
                            nextIndex++;
                        } else if (c == vowels[nextIndex - 1]){ //if repeat last one
                            length++;
                        } else { //new string
                            nextIndex = 0;
                            length = 0;
                            newString = true;
                        }
                    } else { // one result
                        length++;
                        maxLength = Math.max(maxLength, length);
                    }
                }

            }
            //System.out.println("end: i="+i+",c="+c+",length="+length);
        }
        return maxLength;
    }

}
