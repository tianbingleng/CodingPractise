package ABC.tree;

/**
 * Created by tianbingleng on 6/12/2017
 */
public class TreeOperation {
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
        root = operation.buildTree(array);
//        System.out.println("search for key: 1"+search(1));
//        System.out.println("search for key: 2"+search(2));
//        System.out.println("search for key: 3"+search(3));
//        System.out.println("search for key: 4"+search(4));
//        System.out.println("search for key: 5"+search(5));
//        System.out.println("search for key: 6"+search(6));
//        System.out.println("search for key: 7"+search(7));
//        System.out.println("search for key: 8"+search(8));
//        System.out.println("search for key: 9"+search(9));
//        System.out.println("search for key: 10"+search(10));
//        System.out.println("search for key: 11"+search(11));
//        System.out.println("search for key: 0"+search(0));
//


        System.out.println("search for key: 1"+search(1));
        root = deleteNode(root, 1);
        System.out.println("search for key: 1"+search(1));
        System.out.println("search for key: 2"+search(2));
        root = deleteNode(root, 2);
        System.out.println("search for key: 2"+search(2));
        System.out.println("search for key: 3"+search(3));
        root = deleteNode(root, 3);
        System.out.println("search for key: 3"+search(3));
        System.out.println("search for key: 4"+search(4));
        root = deleteNode(root, 4);
        System.out.println("search for key: 4"+search(4));
        System.out.println("search for key: 5"+search(5));
        root = deleteNode(root, 5);
        System.out.println("search for key: 5"+search(5));
        System.out.println("search for key: 6"+search(6));
        root = deleteNode(root, 6);
        System.out.println("search for key: 6"+search(6));
        System.out.println("search for key: 7"+search(7));
        root = deleteNode(root, 7);
        System.out.println("search for key: 7"+search(7));
        System.out.println("search for key: 8"+search(8));
        root = deleteNode(root, 8);
        System.out.println("search for key: 8"+search(8));


    }

    private static TreeNode root;

    public static TreeNode buildTree(int[] array) {
        if (array == null || array.length == 0) {
            return null;
        }

        for (int i = 0; i < array.length; i++) {
            insertNode(array[i]);
        }
        return root;
    }

    public static void insertNode(int key) {
        TreeNode newNode = new TreeNode(key);
        if (root == null) {
            root = newNode;
            return;
        }

        TreeNode currNode = root;
        TreeNode parent;
        while (true) {
            // no duplicate allowed
            if (currNode.intVal == key) {
                return;
            }
            parent = currNode;
            if (currNode.intVal > key) {
                // go left
                currNode = currNode.left;
                if (currNode == null) {
                    parent.left = newNode;
                    return;
                }
            } else {
                // go right
                currNode = currNode.right;
                if (currNode == null) {
                    parent.right = newNode;
                    return;
                }
            }
        }
    }

    public static boolean search(int key) {
        if (root == null) {
            return false;
        }
        TreeNode currNode = root;
        while (currNode != null) {
            if (currNode.intVal == key) {
                return true;
            } else if (currNode.intVal > key) {
                currNode = currNode.left;
            } else {
                currNode = currNode.right;
            }
        }
        return false;
    }

    static public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        // root not null
        if (root.intVal < key) {
            root.right = deleteNode(root.right, key);
        } else if (root.intVal > key) {
            root.left = deleteNode(root.left, key);
        } else {
            // now it is equal the key, we gonna delete
            if (root.left == null && root.right == null) {
                return null;
            } else if (root.left == null || root.right == null) {
                // it has one child
                return root.left != null ? root.left : root.right;
            } else {
                // it has two children
                TreeNode smallest = findSmallest(root.right);
                root.intVal = smallest.intVal;
                root.right = deleteNode(root.right, smallest.intVal);
            }
        }
        return root;
    }

    static public TreeNode findSmallest(TreeNode root) {
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }


}
