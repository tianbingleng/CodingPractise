package PocketGem;

public class SegmentTree {
    public static void main (String[] args) {
        int[] nums = {2, 4, 5, 7, 8, 9};
        NumArray segmengTree = new NumArray(nums);
        System.out.println(segmengTree.sumRange(0, 2)); //11
        segmengTree.update(2, 5);
        System.out.println(segmengTree.sumRange(0, 5)); //35
    }
}
// Segment tree
// Binary tree root  = sum (left + right)
//           tree[i] = tree[i * 2] + tree[i * 2 + 1]

// all log(n) the tree's height
class NumArray {

    int[] tree;
    int size; // size of nums NOT TREE

    public NumArray(int[] nums) {
        size = nums.length;
        tree = new int[2 * size];
        buildTree(nums);
    }


    // log(n), height of the tree
    public void update(int i, int val) {
        // update the tree index
        i += size;
        tree[i] = val;
        while (i > 0) {
            int left = i;
            int right = i;
            // now it is the left one, we need to increase right
            if (i % 2 == 0) {
                right++;
            } else { // else it is right one, we need to decrease left
                left--;
            }
            tree[i / 2] = tree[left] + tree[right];
            i /= 2;
        }
    }

    // log(n), height of the tree
    public int sumRange(int i, int j) {
        int sum = 0;
        // get the tree index first
        int left = i + size;
        int right = j + size;
        while (left <= right) {
            // if left is out of bound, add it
            if (left % 2 == 1) {
                sum += tree[left];
                left++;
            }
            // if right is out of bound, add it
            if (right % 2 == 0) {
                sum += tree[right];
                right--;
            }
            // reduce into next round
            left /= 2;
            right /= 2;
        }
        return sum;
    }

    // O (n)
    private void buildTree(int[] nums) {
        for (int i = size, j = 0; i < tree.length; i++, j++) {
            tree[i] = nums[j];
        }
        for (int i = size - 1; i > 0; i--) {
            tree[i] = tree[i * 2] + tree[i * 2 + 1];
        }
    }
}