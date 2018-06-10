package ABC.tree;

import java.util.*;

/**
 * Created by tianbingleng on 6/12/2017.
 */
public class TreeTraversal {
    public static void main(String[] args) {

        int[] array = {5, 3, 9, 2, 4, 1, 7, 10, 6, 8};

        TreeOperation operation = new TreeOperation();
        TreeNode root = operation.buildTree(array);

        List<Integer> result = new ArrayList<>();
        traverseRecursive(root, result);
        System.out.println(result);

//        List<Integer> preOrderIteration = preOrderIteration(root);
//        System.out.println(preOrderIteration);

//        List<Integer> inOrderIteration = inOrderIteration(root);
//        System.out.println(inOrderIteration);

        List<Integer> postOrderIteration1 = postOrderIteration1(root);
        System.out.println(postOrderIteration1);

        List<Integer> postOrderIteration2 = postOrderIteration2(root);
        System.out.println(postOrderIteration2);

    }

    public static void traverseRecursive(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }

        traverseRecursive(root.left, result);
        traverseRecursive(root.right, result);
        result.add(root.intVal);

    }

    public static List<Integer> preOrderIteration(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode currNode = stack.pop();
            result.add(currNode.intVal);
            if (currNode.right != null) {
                stack.push(currNode.right);
            }
            if (currNode.left != null) {
                stack.push(currNode.left);
            }
        }
        return result;
    }

    //root NOT push first
    public static List<Integer> inOrderIteration(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode currNode = root;
        while (!stack.isEmpty() || currNode != null) {
            while (currNode != null) {
                stack.push(currNode);
                currNode = currNode.left;
            }
            currNode = stack.pop();
            result.add(currNode.intVal);
            currNode = currNode.right;
        }
        return result;
    }

    //root push first
    // (node, right, left) - similar like pre-order
    // Reverse it -> (left, right, node)
    public static List<Integer> postOrderIteration1(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode currNode = stack.pop();
            result.add(currNode.intVal);
            if (currNode.left != null) {
                stack.push(currNode.left);
            }
            if (currNode.right != null) {
                stack.push(currNode.right);
            }
        }
        Collections.reverse(result);
        return result;
    }

    //root push first
    public static List<Integer> postOrderIteration2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        TreeNode prevNode = null;

        while (!stack.isEmpty()) {
            // only peek, not get out
            TreeNode currNode = stack.peek();
            // going down, left, right, itself
            if (prevNode == null || currNode == prevNode.left || currNode == prevNode.right) {
                if (currNode.left != null) {
                    stack.push(currNode.left);
                } else if (currNode.right != null) {
                    stack.push(currNode.right);
                } else {
                    result.add(currNode.intVal);
                    stack.pop();
                }
            }
            // return from left childen
            // then go right, itself
            else if (currNode.left == prevNode) {
                if (currNode.right != null) {
                    stack.push(currNode.right);
                } else {
                    result.add(currNode.intVal);
                    stack.pop();
                }
            }
            // return from right
            // then just return itself
            else {
                result.add(currNode.intVal);
                stack.pop();
            }
            prevNode = currNode;
        }
        return result;
    }
}
