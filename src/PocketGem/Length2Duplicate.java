package PocketGem;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by tianbingleng on 10/10/2017.
 */
public class Length2Duplicate {
    public static void main (String[] args) {
        String a = "apple";
        String b = "app";
        System.out.println(find(a, b));
    }

    static public int find(String str1, String str2) {
        Set<String> set = new HashSet<>();
        for (int i = 0; i < str1.length() - 1; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(str1.charAt(i));
            sb.append(str1.charAt(i + 1));
            //System.out.println(sb.toString());
            set.add(sb.toString());
        }
        int count = 0;
        for (int i = 0; i < str2.length() - 1; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(str2.charAt(i));
            sb.append(str2.charAt(i + 1));
            if (set.contains(sb.toString())) {
                System.out.println(sb.toString());
                count++;
            }
        }
        return count;
    }


}
