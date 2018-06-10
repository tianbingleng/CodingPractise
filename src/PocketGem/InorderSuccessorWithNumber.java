package PocketGem;

/**
 * With Parent Node.
 * No Value.
 * Input parameter: the node for whose inorder successor needs to be find.
 * static public TreeNodeWithParent getSuccessor(TreeNodeWithParent node) {}
 */


public class InorderSuccessorWithNumber {

    /*
                   5
                /      \
              3         8
            /   \     /   \
           1    4    7     10

        p = 3

        in-order: left, node, right
        1,3,4, 5, 7,8, 10
*/

    public static void main (String[] args) {
        TreeNode a = new TreeNode(5);
        TreeNode b = new TreeNode(3);
        TreeNode c = new TreeNode(8);
        TreeNode d = new TreeNode(1);
        TreeNode e = new TreeNode(4);
        TreeNode f = new TreeNode(7);
        TreeNode g = new TreeNode(10);

        a.left = b;
        a.right = c;
        b.left = d;
        b.right = e;
        c.left = f;
        c.right = g;

        printTreeInorder(a);

        System.out.println();
        System.out.println("Successor of "+ a +" is " + getSuccessor(a, a.intVal));
        System.out.println("Successor of "+ b +" is " + getSuccessor(a, b.intVal));
        System.out.println("Successor of "+ c +" is " + getSuccessor(a, c.intVal));
        System.out.println("Successor of "+ d +" is " + getSuccessor(a, d.intVal));
        System.out.println("Successor of "+ e +" is " + getSuccessor(a, e.intVal));
        System.out.println("Successor of "+ f +" is " + getSuccessor(a, f.intVal));
        System.out.println("Successor of "+ g +" is " + getSuccessor(a, g.intVal));

        printTreeInorder(a);
        System.out.println();
        System.out.println("Predecessor of "+ a +" is " + getPredecessor(a, a.intVal));
        System.out.println("Predecessor of "+ b +" is " + getPredecessor(a, b.intVal));
        System.out.println("Predecessor of "+ c +" is " + getPredecessor(a, c.intVal));
        System.out.println("Predecessor of "+ d +" is " + getPredecessor(a, d.intVal));
        System.out.println("Predecessor of "+ e +" is " + getPredecessor(a, e.intVal));
        System.out.println("Predecessor of "+ f +" is " + getPredecessor(a, f.intVal));
        System.out.println("Predecessor of "+ g +" is " + getPredecessor(a, g.intVal));


    }


    /*
                   5
                /      \
              3         8
            /   \     /   \
           1    4    7     10

        p = 3

        in-order: left, node, right
        1,3,4, 5, 7,8, 10
*/

    static public TreeNode getSuccessor(TreeNode root, int target) {
        // if it is empty
        if (root == null) {
            return null;
        }

        TreeNode result = null;

        while (root != null) {
            // only keep the root when > target
            // it can be the result
            if (root.intVal > target) {
                result = root;
                root = root.left;
            } else { // go to right child, if it has, then it will be the result
                root = root.right;
            }
        }

        return result;
    }

    static public TreeNode getPredecessor(TreeNode root, int target) {
        // if it is empty
        if (root == null) {
            return null;
        }

        TreeNode result = null;

        while (root != null) {
            // only keep the root when < target
            // it can be the result
            if (root.intVal < target) {
                result = root;
                root = root.right;
            } else { // go to left child, if it has, then it will be the result
                root = root.left;
            }
        }

        return result;
    }


    static public void printTreeInorder(TreeNode root) {
        if (root == null) {
            return;
        }
        printTreeInorder(root.left);
        System.out.print(root+" ");
        printTreeInorder(root.right);
    }


}
