import java.util.HashSet;
import java.util.Set;

/**
 * Created by tianbingleng on 21/6/2017.
 */
public class EditDistance {
    static public void main(String[] args) {
        /*
        Given two strings of alphanumeric characters, determine the minimum number of Replace, Delete, and Insert operations needed to transform one string into the other.

        Assumptions
        Both strings are not null

        Examples

        string one: “sigh”, string two : “asith”
        the edit distance between one and two is 2 (one insert “a” at front then replace “g” with “t”).
        */

//        String one = "sigh";
//        String two = "asith";
        String one = "sigh";
        String two = "asith";

        System.out.println((editDistance1(one, two)));
        System.out.println((editDistance2(one, two)));

    }
    static public int editDistance1(String one, String two) {
        /*
           Example:
           s1 = "asdf"
           s2 = "sghj"

           s1 = c1 | s1r
           s2 = c2 | s2r

           Important! All the actions are performed on S1!!!!!!

           (1) Replace: a->s
           s1 = s | sdf
           s2 = s | ghj

           Replace = editDistance(sdf, ghj) + 1

           (2) Delete: a
           s1 = a | sdf
           s2 =     sghj

           Delete = editDistance(sdf, sghj) + 1

           (3) Add: s
           s1 = s | asdf
           s2 = s | ghj

           Add = editDistance(asdf, ghj) + 1

           Since using Recursion.
           We have 4 cases, total M + N level of trees. O(4^(M+N)).
         */
        // base case
        if (one.length() == 0) {
            return two.length();
        }
        if (two.length() == 0) {
            return one.length();
        }
        // Case 1. Same first letter. Nothing happens, continue to next character.
        if (one.charAt(0) == two.charAt(0)) {
            return editDistance1(one.substring(1), two.substring(1));
        }
        // Replace
        int replace = 1 + editDistance1(one.substring(1), two.substring(1));
        // Delete
        int delete = 1 + editDistance1(one.substring(1), two);
        // Add
        int add = 1 + editDistance1(one, two.substring(1));

        // Return the minimum one
        return Math.min(Math.min(replace, delete), add);
    }

    static public int editDistance2(String one, String two) {
        /*
        Using DP.
        s1 = xxxxx + 'a'        size = i
        s2 = yyyyyy + 's'       size = j

        s1 = s1r + c1 = xxxxx + 'a'        size = i
        s2 = s2r + c2 = yyyyyy + 's'       size = j

        M[i][j] present the Min number of actions to transform substring
        (first i letters of s1) to (first j letters of s2)
        --> transform from i to j

        Case 1. Do nothing. s1[0] == s2[0]
        editDistance(i,j) = editDistance(i-1, j-1)

        Case 2. Replace c1 with c2. (整个加一，i,j都向前一位)
        distance(s1r + c1, s2r + c2) = 1 + distance(s1r, s2r)
        editDistance(1,1) = 1 + editDistance(0, 0)
        等价~~~~~~~~
        editDistance(i,j) = 1 + editDistance(i-1, j-1)

        Case 2. Delete c1. (i减了一位，j不变)
        distance(s1r + c1, s2) = 1 + distance(s1r, s2)
        editDistance(1,1) = 1 + editDistance(0,1)
        等价~~~~~~~~
        editDistance(i,j) = 1 + editDistance(i-1,j)

        Case 3. Add c2 into s1. (insert了一个s,跟下面的s抵消了) 比较怪，a不变，s没了。。
        a|s
        s
        distance(s1, s2r + c2) = 1 + distance(s1 + c2, s2r + c2)
        editDistance(1,1) = 1 + editDistance(1,0)
        等价~~~~~~~~
        editDistance(i,j) = 1 + editDistance(i,j-1)
        */
        int m = one.length();
        int n = two.length();
        int[][] distanceArray = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0) {
                    distanceArray[i][j] = j;
                }
                else if (j == 0) {
                    distanceArray[i][j] = i;
                }
                // do nothing, if the last element of i/j are same
                else if (one.charAt(i - 1) == two.charAt(j - 1)) {
                    distanceArray[i][j] = distanceArray[i - 1][j - 1];
                }
                else {
                    distanceArray[i][j] = 1 +
                            Math.min(Math.min(distanceArray[i - 1][j - 1],  // replace
                                    distanceArray[i][j - 1]),   // insert
                                    distanceArray[i - 1][j]);   // delete
                }
            }
        }
        printMatrix(distanceArray, one, two);
        return distanceArray[m][n];
    }

    static public void printMatrix(int[][] distanceArray, String one, String two) {
        int row = distanceArray.length;
        int col = distanceArray[0].length;
        int k=0;
        System.out.println("=======Matrix=======");
        for(int i = 0; i < row; i++) {
            if (i == 0) {
                System.out.println("    "+two);
            }
            if (i == 1) {
                System.out.println("--------------------");
                System.out.print("  |");
                for(int l = 0 ; l < col ; l++) {
                    System.out.print(l);
                }
                System.out.println();
            }
            for(int j = 0; j < col; j++) {
                if (j == 0){
                    if (i != 0) {
                        System.out.print(one.charAt(k++));
                        System.out.print(i);
                        System.out.print("|");
                    }
                    else
                        System.out.print("   ");
                }
                System.out.print(distanceArray[i][j]);
            }
            System.out.println();
        }
        System.out.println("=======Matrix=======");

    }


}
