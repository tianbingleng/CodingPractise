package PocketGem;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Created by tianbingleng on 10/10/2017.
 */
public class TreeSum_RootLeafMaxPath {

    public static void main (String[] args) {
        TreeNode a = new TreeNode(5);
        TreeNode b = new TreeNode(4);
        TreeNode c = new TreeNode(8);
        TreeNode d = new TreeNode(11);
        TreeNode e = new TreeNode(13);
        TreeNode f = new TreeNode(4);
        TreeNode g = new TreeNode(7);
        TreeNode h = new TreeNode(2);
        TreeNode i = new TreeNode(5);
        TreeNode j = new TreeNode(1);
//                 5a
//                / \
//              4b   8c
//             /   /   \
//           11d  13e   4f
//         /  \        /  \
//        7g   2h     5i  1j

        a.left = b;
        a.right = c;

        b.left = d;
        c.left = e;
        c.right = f;

        d.left = g;
        d.right = h;
        f.left = i;
        f.right = j;

        System.out.println(generateMaxPath(a));
    }

    // O(n)
    // we get the target leaf first
    // then generate the path
    private static List<Integer> generateMaxPath(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        getTargetLeaf(root, 0);

        generateResult(root, targetNode);

        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }

        return result;
    }
    static Deque<Integer> stack = new ArrayDeque<>();
    static TreeNode targetNode;
    static int maxSum = Integer.MIN_VALUE;


    // O(n) time all nodes
    // O(1) space
    private static void getTargetLeaf(TreeNode root, int currSum) {
        if (root == null) {
            return;
        }

        currSum = currSum + root.intVal;

        // it is a leaf now
        if (root.left == null && root.right == null) {
            if (currSum > maxSum) {
                maxSum = currSum;
                targetNode = root;
            }
        }
        // else we just go left/right
        getTargetLeaf(root.left, currSum);
        getTargetLeaf(root.right, currSum);
    }

    // O (n) for worst case, if the path is in bottom right corner, we need to go all nodes
    // if it is in left most, just O(height)
    // O(n) space
    private static boolean generateResult(TreeNode root, TreeNode target) {
        // base case
        if (root == null) {
            return false;
        }

        // if we found current target
        if (root == target) {
            stack.push(root.intVal);
            return true;
        }

        // if our child has find the target
        if (generateResult(root.left, target) || generateResult(root.right, target)) {
            stack.push(root.intVal);
            return true;
        }
        return false;
    }

}
