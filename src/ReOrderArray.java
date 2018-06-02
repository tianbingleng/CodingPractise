/**
 * Created by tianbingleng on 18/6/2017.
 */
public class ReOrderArray {
    static public void main(String[] args) {
        /*
        Given an array of elements, reorder it as follow:

        { N1, N2, N3, …, N2k } → { N1, Nk+1, N2, Nk+2, N3, Nk+3, … , Nk, N2k }

        { N1, N2, N3, …, N2k+1 } → { N1, Nk+1, N2, Nk+2, N3, Nk+3, … , Nk, N2k, N2k+1 }

        Try to do it in place.

        Assumptions

        The given array is not null
        Examples

        { 1, 2, 3, 4, 5, 6} → { 1, 4, 2, 5, 3, 6 }

        { 1, 2, 3, 4, 5, 6, 7, 8 } → { 1, 5, 2, 6, 3, 7, 4, 8 }

        { 1, 2, 3, 4, 5, 6, 7 } → { 1, 4, 2, 5, 3, 6, 7 }
        */
    }
    public int[] reorder(int[] array) {
        // Write your solution here.
        if (array.length % 2 == 0) {
            reorder(array, 0, array.length - 1);
        } else {
            reorder(array, 0, array.length - 2);
        }

        return array;
    }

    public void reorder(int[] array, int left, int right) {
        // get the size of the current chunk, this should be further divided into 4
        int length = right - left + 1;
        // if (A|1)|(B|2) length of A1 = 2, it is down.
        if (length <= 2) {
            return;
        }
        int mid = left + length / 2;
        int leftMid = left + length / 4;
        int rightMid = left + length * 3 / 4;

        //now we have four chunks
        //C1, left, leftMid - 1
        //C2, leftMid, mid - 1
        //C3, mid, rightMid - 1
        //C4, rightMid, right
        // now we use "i love yahoo" to reverse C2 and C3
        reverse(array, leftMid, mid - 1);
        reverse(array, mid, rightMid - 1);
        reverse(array, leftMid, rightMid - 1);

        //now, the array becomes C1,C3,C2,C4
        //after reverse, we call recursion in the C1,C3 and C2,C4
        int chunk1Size = leftMid - left;
        // notice it is ""left"" + 2xxxx
        reorder(array, left, left + 2 * chunk1Size - 1);
        reorder(array, left + 2 * chunk1Size, right);
    }

    public void reverse(int[] array, int left, int right) {
        while (left < right) {
            int temp = array[left];
            array[left] = array[right];
            array[right] = temp;
            left++;
            right--;
        }
    }
}
