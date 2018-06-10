package PocketGem;

/**
 * Created by tianbingleng on 10/10/2017.
 */
public class TenaryParser {
    public static void main(String[] args) {
        String test = "a?b?d:e:c";
        TreeNode root = build(test);
        preorder(root);
    }
    // a?(b?d:e):(c)
    public static TreeNode build(String s) {
        if (s.length() == 0) {
            return null;
        }
        int index1 = s.indexOf('?');
        int index2 = s.lastIndexOf(':');
        char val = s.charAt(0);
        TreeNode root = new TreeNode(val);
        if (index1 == -1 || index2 == -1) {
            return root;
        }
//        System.out.println(s);
//        System.out.println("index1="+index1);
//        System.out.println("index2="+index2);
        root.left = build(s.substring(index1 + 1, index2));
        root.right = build(s.substring(index2 + 1));
        return root;
    }

    public static void preorder(TreeNode root) {
        if (root == null) return;
        System.out.print(root.val + " ");
        preorder(root.left);
        preorder(root.right);
    }

    static class TreeNode {
        char val;
        TreeNode left;
        TreeNode right;
        public TreeNode(char val) {
            this.val = val;
        }
    }

}
