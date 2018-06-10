package PocketGem;

/**
 * Created by tianbingleng on 13/11/2017.
 */
public class TreeNodeWithParent {
    String value;
    TreeNodeWithParent left;
    TreeNodeWithParent right;
    TreeNodeWithParent parent;

    public TreeNodeWithParent(String value) {
        this.value = value;
    }

    public String toString() {
        return value;
    }
}
