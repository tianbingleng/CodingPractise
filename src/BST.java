import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 08-722 Data Structures for Application Programmers.
 * Lecture 15 Binary Trees, mainly Binary Search Trees
 *
 * A very simple Binary Search Tree implementation
 * No duplicate keys allowed
 *
 * Note: This is only to help your understanding of the concepts
 *
 * @author Terry Lee
 */
public class BST implements BSTInterface {
    /**
     * static nested Node class for Node.
     */
    private static class Node {
        /**
         * Key integer.
         */
        private int key;
        /**
         * double value mapped to the key.
         */
        private double value;
        /**
         * references to left and right children nodes.
         */
        private Node left, right;

        /**
         * Constructs a new node with key and value.
         * @param k integer key
         * @param v double value
         */
        Node(int k, double v) {
            key = k;
            value = v;
            left = null;
            right = null;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("[").append(key).append(",").append(value).append("]");
            return sb.toString();
        }
    }
    /**
     * reference to root node.
     */
    private Node root;

    /**
     * Constructs an empty BST.
     */
    public BST() {
        root = null;
    }

    /**
     * Searches for the specified key in the tree.
     * @param key key of the element to search
     * @return boolean value indication of success or failure
     */
    @Override
    public boolean find(int key) {
        // tree is empty
        if (root == null) {
            return false;
        }

        Node curr = root;
        // while not found
        while (curr.key != key) {
            if (curr.key < key) {
                // go right
                curr = curr.right;
            } else {
                // go left
                curr = curr.left;
            }

            // not found
            if (curr == null) {
                return false;
            }
        }
        return true; // found


    }

    /**
     * Inserts a new element into the tree.
     * @param key key of the element
     * @param value value of the element
     */
    @Override
    public void insert(int key, double value) {
        Node newNode = new Node(key, value);
        // empty tree
        if (root == null) {
            root = newNode;
            return;
        }

        Node parent = root; // keep track of parent
        Node curr = root;
        while (true) {
            // no duplicate keys allowed
            // simply keep the existing one here
            if (curr.key == key) {
                return;
            }

            parent = curr; // update parent
            if (curr.key < key) {
                // go right
                curr = curr.right;
                if (curr == null) {
                    // found a spot
                    parent.right = newNode;
                    return;
                }
            } else {
                // go left
                curr = curr.left;
                if (curr == null) {
                    // found a spot
                    parent.left = newNode;
                    return;
                }
            } // end of if-else to go right or left
        } // end of while
    } // end of insert method

    /**
     * Deletes an element from the tree using the specified key.
     * @param key key of the element to delete
     */
    @Override
    public void delete(int key) {
        // empty tree
        if (root == null) {
            return;
        }

        Node parent = root;
        Node curr = root;
        /*
         * flag to check left child
         * use this flag during actual deletion process
         * which happens after the while loop
         */
        boolean isLeftChild = true;

        while (curr.key != key) {
            parent = curr; // update parent first
            if (curr.key < key) { // go right
                isLeftChild = false;
                curr = curr.right;
            } else { // go left
                isLeftChild = true;
                curr = curr.left;
            }

            // case 1: not found
            if (curr == null) {
                return;
            }
        }

        if (curr.left == null && curr.right == null) {
            // case 2: leaf
            if (curr == root) {
                root = null;
            } else if (isLeftChild) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        } else if (curr.right == null) {
            // case 3: one child (no right child)
            if (curr == root) {
                root = curr.left;
            } else if (isLeftChild) {
                parent.left = curr.left;
            } else {
                parent.right = curr.left;
            }
        } else if (curr.left == null) {
            // case 3: one child (no left child)
            if (curr == root) {
                root = curr.right;
            } else if (isLeftChild) {
                parent.left = curr.right;
            } else {
                parent.right = curr.right;
            }
        } else {
            // case 4: two children
            // here we use successor but using predecessor is also an option
            Node successor = getSuccessor(curr);

            if(curr == root) {
                root = successor;
            } else if(isLeftChild) {
                parent.left = successor;
            } else {
                parent.right = successor;
            }
            successor.left = curr.left;
        }
    }

    /**
     * Helper method to find the successor of the toDelete node.
     * This tries to find the smallest value of the right subtree
     * of the toDelete node by going down to the left most node in the subtree
     * @param toDelete node to delete
     * @return the successor of the toDelete node
     */
    private Node getSuccessor(Node toDelete) {
        Node successorParent = toDelete;
        Node successor = toDelete;
        // start the search from the root of the right subtree
        Node curr = toDelete.right;

        // move down to left as far as possible in the right subtree
        // successor's left child must be null
        while (curr != null) {
            successorParent = successor;
            successor = curr;
            curr = curr.left;
        }

        /*
         * If successor is NOT the right child of the node to delete, then
         * need to take care of two connections in the right subtree
         */
        if (successor != toDelete.right) {
            successorParent.left = successor.right;
            successor.right = toDelete.right;
        }

        return successor;
    }

    public List<Integer> preOrderTraverse() {
        List<Integer> list = new ArrayList<Integer>();
        if(root == null) {
            return list;
        }

        Stack<Node> stack = new Stack<Node>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node curr = stack.pop();
            // put right first, pop it later
            if (curr.right != null) {
                stack.push(curr.right);
            }
            // put left last, pop it first
            if (curr.left != null) {
                stack.push(curr.left);
            }
            // this is current node key
            list.add(curr.key);
        }
        return list;
    }
    public List<Integer> inOrderTraverse() {
        // inorder, left - node - right
        List<Integer> list = new ArrayList<Integer>();
        if(root == null) {
            return list;
        }
        Stack<Node> stack = new Stack<Node>();
        Node curr = root;
        while (curr != null || !stack.isEmpty()) {
            // if current node is not null, always to push it into stack, *DONT* process yet
            // go to assign curr = curr.left, even its left child is null
            while(curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            // curr == null, we need to get the top from the stack, last valid one
            // we put its parent out, and print *parent* out first, then go his right child
            // go to assign curr = curr.right, even its right child is null
            // continue loop
            curr = stack.pop();
            list.add(curr.key);
            curr = curr.right;
        }
        return list;
    }
    public List<Integer> postOrderTraverse() {
/*

Summary:
Post-Order: left, right, node

Total Three Major Case:
Each time, we got the **currNode** from the top of the stack (stack.peek())
We also get the **prevNode** from the last iteration.

Case1. prevNode == NULL OR prevNode is the PARENT of currNode => Going down
When going down, we follow -> curr.left first (if any), curr.right then (if any), finally return currNode (pop)

Case2. prevNode == currNode.left => We are back from the leftSubtree (leftSubtree is finished)
We follow -> curr.right (if any), OR, finally return currNode (pop)

Case3. prevNode == currNode.right => We are back from the rightSubtree (rightSubtree is finished)
In this case, we just return the currNode (pop). Because it is postOrder, left, right, node.

*/
        List<Integer> list = new ArrayList<Integer>();
        if (root == null) {
            return list;
        }
        Stack<Node> stack = new Stack<Node>();
        stack.push(root);
        Node prev = null;

        while(!stack.isEmpty()) {
            // get the current top of the value, NOT POP yet
            Node curr = stack.peek();
            //Case1. going down
            if (prev == null || prev.left == curr || prev.right == curr) {
                // push left child into stack first
                if (curr.left != null) {
                    stack.push(curr.left);
                } // then push right child into stack
                else if (curr.right != null) {
                    stack.push(curr.right);
                } // if the current is a leaf, just return it and pop it from the stack
                else {
                    list.add(curr.key);
                    stack.pop();
                }
            }
            //Case2. back from left subTree (we finish left part, we need to consider right & node)
            else if (prev == curr.left) {
                // push the right child into stack to later processing
                if (curr.right != null) {
                    stack.push(curr.right);
                }
                // no left, no right now. just return the node. and pop it from the stack.
                // it like a left only linked list
                else {
                    list.add(curr.key);
                    stack.pop();
                }
            }
            //Case3. back from the right subTree
            // (we finish both left/right part, now we just need to return the node)
            else {
                list.add(curr.key);
                stack.pop();
            }
            prev = curr; //dont forget
        }

        return list;
    }



    /**
     * A few simple test cases.
     * @param args arguments
     */
    public static void main(String[] args) {
        BST theBST = new BST();

        theBST.insert(5, 5);
        theBST.insert(2, 2);
        theBST.insert(8, 8);
        theBST.insert(1, 1);
        theBST.insert(3, 3);
        theBST.insert(9, 9);

        System.out.println(theBST.preOrderTraverse());
        System.out.println(theBST.inOrderTraverse());
        System.out.println(theBST.postOrderTraverse());

    }

}
