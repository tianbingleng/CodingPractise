package ABC.tree;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by tianbingleng on 7/12/2017.
 */
public class Cousin {
    public static void main(String[] args) {
        int[] array = {5, 3, 9, 2, 4, 1, 7, 10, 6, 8};

        //                5
        //            /       \
        //          3          9
        //       /     \     /   \
        //     2        4   7    10
        //    /           /   \
        //  1            6     8

        // notice
        // when we delete 1,2,3,4, then we delete 5
        // the root should be 9!!!! read the algorighm!
        TreeOperation operation = new TreeOperation();
        TreeNode root = operation.buildTree(array);

    }

    public static boolean isCousin(TreeNode root, TreeNode a, TreeNode b) {
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode parentA = null;
            TreeNode parentB = null;
            for (int i = queue.size(); i > 0; i--) {
                TreeNode currNode = queue.poll();
                if (currNode.left != null) {
                    if (currNode.left == a) {
                        parentA = currNode;
                    }
                    if (currNode.left == b) {
                        parentB = currNode;
                    }
                    queue.offer(currNode.left);
                }
                if (currNode.right != null) {
                    if (currNode.right == a) {
                        parentA = currNode;
                    }
                    if (currNode.right == b) {
                        parentB = currNode;
                    }
                    queue.offer(currNode.right);
                }
            }
            // end of the loop
            if (parentA != null && parentB != null && parentA != parentB) {
                return true;
            }
        }
        return false;
    }

}
