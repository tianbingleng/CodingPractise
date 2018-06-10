package PocketGem;

import java.util.Random;

/**
 * Created by tianbingleng on 10/10/2017.
 */
public class RandomTest {
    public static void main (String[] args) {
//        System.out.println(usingBigGenerateSmall());
//        System.out.println(usingBigGenerateSmall());
//        System.out.println(usingBigGenerateSmall());
//        System.out.println(usingBigGenerateSmall());
//        System.out.println(usingBigGenerateSmall());
//        System.out.println(usingBigGenerateSmall());
//        System.out.println(usingBigGenerateSmall());

        System.out.println(usingSmallGenerateBig());
        System.out.println(usingSmallGenerateBig());
        System.out.println(usingSmallGenerateBig());
        System.out.println(usingSmallGenerateBig());
        System.out.println(usingSmallGenerateBig());
        System.out.println(usingSmallGenerateBig());
        System.out.println(usingSmallGenerateBig());

    }

    /*
    * 给定一个getMax()函数，能随机等可能生成0-Max -1的数，
    * 要求生成一个getIntRange(int range),可以等可能返回0 - range - 1之间的数。
    * (就是相当于给你random(bigNum) ，要你生成random(smallNum))
    *
    * */

    // O(25/21) times for the loop
    static public int usingSmallGenerateBig() {
        int big = 7;
        int small = 5;
        // we use 5 * 5 = 25 to gengerate
        // 0 - 24 (only take 0 - 20). so 21,22,23,24 will keep generate
        int result;
        do {
            Random rand = new Random();
            result = small * rand.nextInt(small) + rand.nextInt(small);
        } while (result > 20);

        return result % big;

    }

    //O (7/5) time for the loop
    static public int usingBigGenerateSmall() {
        int big = 7;
        int small = 5;
        int result;
        do {
            result = randomBig(big);
        } while (result >= small); // only want 0-4

        return result;
    }
    static public int randomBig(int big) {
        Random rand = new Random();
        int  n = rand.nextInt(big); // 0 ~ big - 1
        return n;
    }

}
