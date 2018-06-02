/**
 * Created by tianbingleng on 21/6/2017.
 */
public class MaxProduct {
    static public void main(String[] args) {
        /*
        Given a rope with positive integer-length n, how to cut the rope into m integer-length parts with length
        p[0], p[1], ...,p[m-1], in order to get the maximal product of p[0]*p[1]* ... *p[m-1]?
        m is determined by you and must be greater than 0 (at least one cut must be made).
        Return the max product you can have.

        Assumptions
        n >= 2

        Examples
        n = 12, the max product is 3 * 3 * 3 * 3 = 81(cut the rope into 4 pieces with length of each is 3).
        */

        System.out.println((maxProduct2(12)));
        // answer should be 81 = 3 * 3 * 3 * 3
    }
    static public int maxProduct2(int length) {
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


     size = 1m, can't cut. Thus, M[1] = 1.

     size = 2m, if we must cut one. Only one case.
                Case 1. it is __ | __ which is max(1, M[1]) * 1) = 1.
                              左    右            不切 左DP  |  右小段=1
                Thus, M[2] = Max(case 1) = 1.

     size = 3m, if we must cut one. 2 cases.
                Case 1. _ | _ _ which is max(1, M[1]) * 2) = 2.
                       左     右            不切 左DP  |  右小段=2
                Case 2. _ _ | _ which is max(2, M[2]) * 1) = 2.
                       左      右           不切 左DP  |  右小段=1
                Thus, M[2] = Max (case 1 & 2) = 2.

     size = 4m, if we must cut one. 3 cases.
                Case 1. _ | _ _ _ which is max(1, M[1]) * 3) = 3.
                      左     右             不切 左DP  |  右小段=3
                Case 2. _ _ | _ _ which is max(2, M[2]) * 2) = 4.
                      左      右            不切 左DP  |  右小段=2
                Case 3. _ _ _ | _ which is max(3, M[3]) * 1) = 3.
                      左      右            不切 左DP  |  右小段=1

                Thus, M[4] = Max (case 1 & 2 & 3) = 4.

     To summarize,
                Base Case: M[1] = 1
                M[i] = Max(
                            for (j = 1; j <= i - 1; j++)
                             左边不切 左DP |     右边不切 右DP
                            {Max(j, M[j]) * Max(i-j, M[i-j])}
                          )
                这里，j <= i - 1 也可以写成 j <= i / 2, 因为是对称的。
         */
        int[] M = new int[length + 1];
        M[1] = 1;

        for (int i = 2; i <= length; i++) {
            int maxProduct = 1;
            for (int j = 1; j <= i - 1; j++) {
                int currentProduct = Math.max(j, M[j]) * (i - j); //就这行不同
                maxProduct = Math.max(maxProduct, currentProduct);
            }
            M[i] = maxProduct;
        }
        return M[length];
    }

    static public int maxProduct1(int length) {
        /* 左大段，右大段 (DP基础理解，但是不推荐)
     size = 1m, can't cut. Thus, M[1] = 1.

     size = 2m, if we must cut one. Only one case.
                Case 1. it is __ | __ which is max(1, M[1]) * max(1, M[1]) = 1.
                      左    右             不切 左DP  |  不切  右DP
                Thus, M[2] = Max(case 1) = 1.

     size = 3m, if we must cut one. 2 cases.
                Case 1. _ | _ _ which is max(1, M[1]) * max(2, M[2]) = 2.
                      左     右             不切 左DP  |  不切  右DP
                Case 2. _ _ | _ which is max(2, M[2]) * max(1, M[1]) = 2.
                      左      右            不切 左DP  |  不切  右DP
                Thus, M[2] = Max (case 1 & 2) = 2.

     size = 4m, if we must cut one. 3 cases.
                Case 1. _ | _ _ _ which is max(1, M[1]) * max(3, M[3]) = 3.
                      左     右             不切 左DP  |  不切  右DP
                Case 2. _ _ | _ _ which is max(2, M[2]) * max(2, M[2]) = 4.
                      左      右            不切 左DP  |  不切  右DP
                Case 3. _ _ _ | _ which is max(3, M[3]) * max(1, M[1]) = 3. (same as case 1)
                      左      右            不切 左DP  |  不切  右DP
                Thus, M[4] = Max (case 1 & 2 & 3) = 4.

     To summarize,
                Base Case: M[1] = 1
                M[i] = Max(
                            for (j = 1; j <= i - 1; j++)
                             左边不切 左DP |     右边不切 右DP
                            {Max(j, M[j]) * Max(i-j, M[i-j])}
                          )
                这里，j <= i - 1 也可以写成 j <= i / 2, 因为是对称的。
                (也只有切绳子这道题才可以这样，最好都是j <= i - 1 可以)

                Time O(N^2).

         */
        int[] M = new int[length + 1];
        M[1] = 1;

        for (int i = 2; i <= length; i++) {
            int maxProduct = 1;
            for (int j = 1; j <= i - 1; j++) {
                int currentProduct = Math.max(j, M[j]) * Math.max(i - j, M[i - j]); //就这行不同
                maxProduct = Math.max(maxProduct, currentProduct);
            }
            M[i] = maxProduct;
        }
        return M[length];
    }
}
