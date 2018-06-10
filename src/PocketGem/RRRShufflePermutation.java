package PocketGem;

import java.util.Arrays;

/**
 * Created by tianbingleng on 10/10/2017.
 */
public class RRRShufflePermutation {
    public static void main (String[] args) {

    }

    /*
    * 给定一个数组，随机抽取k个样本，要求保证每一次的k size的样本概率相同。
    * （shuffle，或者k - permutation）
    *
    *  k-permutation: a permutation containing k elements of the original array.
    *
    *  P(n, k) = n!/ (n-k)!
    *          = n (n-1) (n-2)...(n - k + 1)
    *  So the possibility for pick one time
    *  = 1/ P (n,k)
    *  = 1/ [n (n-1) (n-2)...(n - k + 1)]
    *  = 1/n * 1/n-1 * 1/n-2 *... 1/n-k+1
    * */

    // we using swap k times
    // from right to left
    //===================================
    // k = 1
    // randomly pick random = [0 ~ n-1] = [0,n)
    // swap(array, random, index n-1)
    // 1/n
    //===================================
    // k = 2
    // randomly pick random = [0 ~ n-2] = [0,n-1)
    // swap(array, random, index n-2
    // 1/n-2

    static public void shuffle(int[] array, int k) {
        if (array.length <= 1) {
            return;
        }

        // from right to left
        for (int i = array.length; i >= 1; i--) {
            int index = (int) (Math.random() * i); // i = n first time
            swap(array, index, i - 1); // index = n-1
        }

        // if we look at array[n-1], array[n-2],..., array[n-k],
        //                     1         2                k
        // that's the k-permutation, return them

        // if we gonna return
        int[] result = Arrays.copyOfRange(array, array.length - k, k);

    }
    static public void swap(int[] array, int a, int b) {
        // ignore
    }

}
