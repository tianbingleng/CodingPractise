package PocketGem;

/**
 * Created by tianbingleng on 13/11/2017.
 */
public class TreeNode {
    String value;
    int intVal;
    TreeNode left;
    TreeNode right;

    public TreeNode(String value) {
        this.value = value;
    }
    public TreeNode(int intVal) {
        this.intVal = intVal;
    }

    public String toString() {
        return value == null ? intVal+"" : value;
    }
}
