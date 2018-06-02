import java.util.Arrays;

/**
 * Created by tianbingleng on 21/6/2017.
 */
public class MinimumCutsForPalindromes {
    static public void main(String[] args) {
        /*
        Given a string, a partitioning of the string is a palindrome partitioning if every
        substring of the partition is a palindrome. Determine the fewest cuts needed for
        a palindrome partitioning of a given string.

        Assumptions
        The given string is not null

        Examples
        “a | babbbab | bab | aba” is a palindrome partitioning of “ababbbabbababa”.
        The minimum number of cuts needed is 3.
        */
        String str1 = "ababbbabbababa"; // 3
        String str2 = "ababb"; // 1
        String str3 = "ab"; //1

        System.out.println((minCuts(str1)));
        System.out.println((minCuts(str2)));
        System.out.println((minCuts(str3)));
    }
    static public int minCuts(String str) {
        /* 左大段，右小段 (DP通法，强烈推荐！！)
            想法：不管你怎么切，右边永远会剩一段，最少为1段。
            如果size = 10m, 可以有如下情况：
     (左边9段) _ _ _ _ _ _ _ _ _ | _ (右边1段)，做完它再进行下面
     (左边8段) _ _ _ _ _ _ _ _ | _ _ (右边2段)，做完它再进行下面，不会与上面重复
     (左边7段) _ _ _ _ _ _ _ | _ _ _ (右边3段)，做完它再进行下面，不会与上面重复
     (左边6段) _ _ _ _ _ _ | _ _ _ _ (右边4段)，做完它再进行下面，不会与上面重复
     (左边5段) _ _ _ _ _ | _ _ _ _ _ (右边5段)，做完它再进行下面，不会与上面重复
     (左边4段) _ _ _ _ | _ _ _ _ _ _ (右边6段)，做完它再进行下面，不会与上面重复（可能重，但general不会）
     (左边3段) _ _ _ | _ _ _ _ _ _ _ (右边7段)，做完它再进行下面，不会与上面重复（可能重，但general不会）
     (左边2段) _ _ | _ _ _ _ _ _ _ _ (右边8段)，做完它再进行下面，不会与上面重复（可能重，但general不会）
     (左边1段) _ | _ _ _ _ _ _ _ _ _ (右边9段)，做完它再进行下面，不会与上面重复（可能重，但general不会）

     所以，general, i 为总长度， j 为左边的长度 （i - j） 为右边长度。
     M[i] = Max(
                 for (j = 1; j <= i - 1; j++)
          （左大段）左边不切 左DP |  右小段长度（不切）
                 {Max(j, M[j]) * (i - j)}
              )
     这里，j <= i - 1 不要写成 j <= i / 2, 这种情况虽然可以，但是其他情况不通的！老老实实做！


     For example, "ababb"

     "a"
     size = 1, no cut. Thus, DP[1] = 0.

     "ab"
     size = 2,
                Case 1. No cut. isPalindromes(1,2) - false (invalid)
                Case 2. it is a | b which is DP[1] + 1

                Thus, M[2] = min(case 2) = 1.

     "aba"
     size = 3,
                Case 1. No cut. isPalindromes(1,3) - true, which is 0.
                Case 2. a | ba which is DP[1] + isPalindromes(2,3) - false (invalid)
                Case 3. ab | a which is DP[2] + isPalindromes(3,3) = DP[2] + 1 = 2

                Thus, M[3] = min (case 1 & 3) = 0.

     "abab"
     size = 4,
                Case 1. No cut. isPalindromes(1,4) - false (invalid)
                Case 2. a | bab which is DP[1] + isPalindromes(2,4) = DP[1] + 1 = 2
                Case 3. ab| ba which is DP[2] + isPalindromes(3,4) - false (invalid)
                Case 4. aba | b which is DP[3] + isPalindromes(4,4) = DP[3] + 1 = 1

                Thus, M[4] = min (case 2 & 4) = 1.

     To summarize,
                (i = size) from 1 ~ length
                Base Case: M[1] = 1 (One character no cut)
                M[i] = Min(
                            for (j = 2; j <= i; j++) // j 为第j个字符
                             if (isPalindromes(1,i)) // including, 第几个字符, no cut
                                no cut, which is DP[i] = 0, can just break
                             else {
                                if(isPalindromes(j,i) // 第j,i个字符, including
                                    DP[i] = Min(DP[i], ValidCase(e,g, DP[j] + 1))
                             }
                          )
         */
        int length = str.length();
        if (length == 0) {
            return 0;
        }
        int[] DP = new int[length + 1];
        DP[1] = 0;

        for (int i = 2; i <= length; i++) {
            DP[i] = i; //assign a max value here
            for (int j = 2; j <= i; j++) {
                if(isPalindrome(str, 1,i)) { //no cut
                    DP[i] = 0;
                    break;
                } else {
                    if (isPalindrome(str, j, i)) { //valid cut from 1~j-1| j~i
                        DP[i] = Math.min(DP[i], DP[j-1] + 1); // DP[j-1] 个数
                    }
                }
            }
        }
        return DP[length];
    }

    static public boolean isPalindrome(String str, int i, int j) { // total number not index
        int left = i - 1; //left index in string
        int right = j - 1; // right index in string
        while (left < right) {
            if (str.charAt(left)!= str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
