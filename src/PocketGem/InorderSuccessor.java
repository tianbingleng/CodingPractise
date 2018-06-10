package PocketGem;

/**
 * With Parent Node.
 * No Value.
 * Input parameter: the node for whose inorder successor needs to be find.
 * static public TreeNodeWithParent getSuccessor(TreeNodeWithParent node) {}
 */


public class InorderSuccessor {
    static TreeNodeWithParent a = new TreeNodeWithParent("A");
    static TreeNodeWithParent b = new TreeNodeWithParent("B");
    static TreeNodeWithParent c = new TreeNodeWithParent("C");
    static TreeNodeWithParent d = new TreeNodeWithParent("D");
    static TreeNodeWithParent e = new TreeNodeWithParent("E");
    static TreeNodeWithParent f = new TreeNodeWithParent("F");
    static TreeNodeWithParent g = new TreeNodeWithParent("G");


    public static void main (String[] args) {
        TreeNodeWithParent root = buildTree();
        printTreeInorder(root);
        System.out.println();
        System.out.println("Successor of "+ a +" is " + getSuccessor(a));
        System.out.println("Successor of "+ b +" is " + getSuccessor(b));
        System.out.println("Successor of "+ c +" is " + getSuccessor(c));
        System.out.println("Successor of "+ d +" is " + getSuccessor(d));
        System.out.println("Successor of "+ e +" is " + getSuccessor(e));
        System.out.println("Successor of "+ f +" is " + getSuccessor(f));
        System.out.println("Successor of "+ g +" is " + getSuccessor(g));
    }


    /**
     * With Parent Node.
     * No Value.
     * Input parameter: the node for whose inorder successor needs to be find.
     * static public TreeNodeWithParent getSuccessor(TreeNodeWithParent node) {}
     *
     * log(n), tree height, worst case is O(n) ... one line
     */
    static public TreeNodeWithParent getSuccessor(TreeNodeWithParent node) {
        // if it is empty
        if (node == null) {
            return null;
        }
        // if the node have right child, then we just return its (right child) left-most child
        if (node.right != null) {
            return getLeftMost(node.right);
        }

        // then we need to get its parent up
        // if curr node is its parent's right child, we still need to go up
        // if curr node is its parent's left child, its parent is the result
        TreeNodeWithParent parent = node.parent;
        while (parent != null && node == parent.right) {
            node = parent;
            parent = node.parent;
        }
        return parent;
    }

    static public TreeNodeWithParent getLeftMost(TreeNodeWithParent node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    static public TreeNodeWithParent buildTree() {
        a.left = b;
        a.right = c;
        b.parent = a;
        c.parent = a;
        b.left = d;
        b.right = e;
        d.parent = b;
        e.parent = b;
        c.left = f;
        c.right = g;
        f.parent = c;
        g.parent = c;

        return a;
    }

    static public void printTreeInorder(TreeNodeWithParent root) {
        if (root == null) {
            return;
        }
        printTreeInorder(root.left);
        System.out.print(root+" ");
        printTreeInorder(root.right);
    }


}
