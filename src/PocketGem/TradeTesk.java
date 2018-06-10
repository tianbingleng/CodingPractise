package PocketGem;

/**
 * Created by tianbingleng on 10/10/2017.
 */
public class TradeTesk {
    public static void main (String[] args) {
        String[] test1 = {};
        String[] test2 = {""};
        String[] test3 = {"ace", "bdf"};
        String[] test4 = {"greeneggs", "ham", "sam", "i", "am"};
        String[] test5 = {"foobar"};
        String[] test6 = {"relgkhay", "df", "cmbojvun", "s", "tpqx", "ziw"};
        String[] test7 = {"cbaABC", ",s875", "kkKK", ".,98mmMM", "NNnn"};
        System.out.println(MergeStrings(test1));
        System.out.println(MergeStrings(test2));
        System.out.println(MergeStrings(test3));
        System.out.println(MergeStrings(test4));
        System.out.println(MergeStrings(test5));
        System.out.println(MergeStrings(test6));
        System.out.println(MergeStrings(test7));
    }

    static public String MergeStrings(String[] array) {
        StringBuilder sb = new StringBuilder("");
        if (array == null || array.length == 0) {
            return sb.toString();
        }
        int[] carray = new int[26];
        for (String str : array) {
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                if (c >= 'a' && c <= 'z') {
                    carray[c - 'a']++;
                }
            }
        }
        for (int i = 0; i < carray.length; i++) {
            int count = carray[i];
            char c = (char) (i + 'a');
            while (count > 0) {
                sb.append(c);
                count--;
            }
        }
        return sb.toString();
    }


}
