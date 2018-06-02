import java.util.List;

public interface BSTInterface {

    /**
     * Searches for the specified key in the tree.
     * @param key key of the element to search
     * @return boolean value indication of success or failure
     */
    boolean find(int key);

    /**
     * Inserts a new element into the tree.
     * @param key key of the element
     * @param value value of the element
     */
    void insert(int key, double value);

    /**
     * Deletes an element from the tree using the specified key.
     * @param key key of the element to delete
     */
    void delete(int key);

    /**
     * Traverses and prints values of the tree in ascending order based on key.
     */
    List<Integer> preOrderTraverse();
    List<Integer> inOrderTraverse();
    List<Integer> postOrderTraverse();

}
