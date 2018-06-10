package PocketGem;

/**
 * Created by tianbingleng on 10/10/2017.
 */
public class ReserviorSampling {

    public static void main (String[] args) {

    }

    int count;
    int result;
    int[] arrayResult;
    int k; // for k elements

    /*
    * reservoir sampling,
    * 给定一个数据流，让你随机抽样1个元素，每个元素被抽到的概率是1/n。
    * （思路就是reservoir sampling）
    * */
    // the data flow... (value)
    // current one select or not 1/n, since it is =0
    // others = (1-1/n) * (1/n-1) = 1/n (same)
    //         not select  select
    public void readOne(int value) {
        count++; // current read so far
        int prob = (int) (Math.random() * count); // Math.random() = [0,1)
        if (prob == 0) {
            result = value; // this one is selected
        }
    }

    /*
    * reservoir sampling,
    * 给定一个数据流，让你随机抽样k个元素，每个元素被抽到的概率是k/n。
    * （思路就是reservoir sampling）
    *
    * */

    // the data flow... (value)
    // current one select or not, k/n, since it is prob < k
    // others = (1-k/n) * (k/n-k) = k/n (same)
    public void readK(int value) {
        count++; // current read so far

        // first it is empty, we just add first k elements
        if (count <= k) {
            arrayResult[count - 1] = value;
        } else {
            // after it is filled, we keep replace
            int prob = (int) (Math.random() * count); // Math.random() = [0,1)
            if (prob < k) { // from [0,k)
                arrayResult[prob] = value; // this one is selected
            }
        }
    }

}
