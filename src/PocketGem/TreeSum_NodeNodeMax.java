package PocketGem;

/**
 * Created by tianbingleng on 10/10/2017.
 */
public class TreeSum_NodeNodeMax {

    public static void main (String[] args) {
        TreeNode a = new TreeNode(5);
        TreeNode b = new TreeNode(-4);
        TreeNode c = new TreeNode(8);
        TreeNode d = new TreeNode(11);
        TreeNode e = new TreeNode(13);
        TreeNode f = new TreeNode(-4);
        TreeNode g = new TreeNode(7);
        TreeNode h = new TreeNode(2);
        TreeNode i = new TreeNode(5);
        TreeNode j = new TreeNode(1);
//                 5a
//                / \
//              -4b   8c
//             /     /   \
//           11d   13e   -4f
//         /  \         /  \
//        7g   2h      5i  1j

        a.left = b;
        a.right = c;

        b.left = d;
        c.left = e;
        c.right = f;

        d.left = g;
        d.right = h;
        f.left = i;
        f.right = j;

        System.out.println(maxPathSum(a));
    }

    static int maxSum = Integer.MIN_VALUE;

    // O(n) time all nodes
    // O(1) space
    private static int maxPathSum(TreeNode root) {
        findSumPath(root);
        return maxSum;
    }

    private static int findSumPath(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftSum = Math.max(findSumPath(root.left), 0);
        int rightSum = Math.max(findSumPath(root.right), 0);

        // if current is the max, then update
        maxSum = Math.max(maxSum, leftSum + rightSum + root.intVal);

        // update the single path to upper level
        return Math.max(leftSum, rightSum) + root.intVal;
    }

}
