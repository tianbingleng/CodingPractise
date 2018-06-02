package amazon;
import java.util.*;

/**
 * Created by tianbingleng on 4/9/2017.
 */
class Node { // 这个是题目给好的
    int val;
    ArrayList<Node> children;

    public Node(int val) {
        this.val = val;
        children = new ArrayList<Node>();
    }
}

public class CompanyTree {
    public static void main(String[] args) {
        Node root = new Node(1);
        Node l21 = new Node(2);
        Node l22 = new Node(3);
        Node l23 = new Node(4);
        Node l31 = new Node(5);
        Node l32 = new Node(5);
        Node l33 = new Node(5);
        Node l34 = new Node(5);
        Node l35 = new Node(5);
        Node l36 = new Node(5);

        l21.children.add(l31);
        l21.children.add(l32);

        l22.children.add(l33);
        l22.children.add(l34);

        l23.children.add(l35);
        l23.children.add(l36);

        root.children.add(l21);
        root.children.add(l22);
        root.children.add(l23);


        double[] globalAve = new double[]{0};
        Node[] globalMaxRoot = new Node[]{null};
        // array[0] means total sum so far
        // array[1] means the number of counts
        double[] array = new double[2];
        getHighAveHelper(root, array, globalAve, globalMaxRoot);
        System.out.println(globalMaxRoot[0].val + " " + globalAve[0]);
    }

    public static double[] getHighAveHelper(Node root, double[] array, double[] globalAve, Node[] globalMaxRoot) {
        if (root.children == null || root.children.size() == 0) {
            array[0] = root.val;
            array[1] = 1;
            return array;
        }

        double currSum = root.val;
        double currCount = 1;

        for (Node child : root.children) {
            array = getHighAveHelper(child, array, globalAve, globalMaxRoot);
            currSum = currSum + array[0]; // sum
            currCount = currCount + array[1]; // count
        }

        double currAve = currSum /  currCount;

        System.out.println("current node:" + root.val + " sum:"+currSum+" count:"+currCount+" avg:" + currAve);

        if (currAve > globalAve[0]) {
            globalAve[0] = currAve;
            globalMaxRoot[0] = root;
        }

        array[0] = currSum;
        array[1] = currCount;
        return array;
    }


}