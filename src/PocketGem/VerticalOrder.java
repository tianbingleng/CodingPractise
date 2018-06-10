package PocketGem;

import java.util.*;

/**
 * Created by tianbingleng on 10/10/2017.
 */
public class VerticalOrder {

    public static void main (String[] args) {
        TreeNode a = new TreeNode("A");
        TreeNode b = new TreeNode("B");
        TreeNode c = new TreeNode("C");
        TreeNode d = new TreeNode("D");
        TreeNode e = new TreeNode("E");
        TreeNode f = new TreeNode("F");
        TreeNode g = new TreeNode("G");
        //           a
        //      b          c
        // d        e  f        g

        a.left = b;
        a.right = c;
        b.left = d;
        b.right = e;
        c.left = f;
        c.right = g;

        System.out.println(verticalViewTraversal(a));
    }

    // return the value of the total amount of the items
    // time O(n)
    // Space O(n) - max nodes in one level (the bottom level)
    private static List<List<TreeNode>> verticalViewTraversal(TreeNode root) {
        List<List<TreeNode>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Deque<TreeNode> nodeQueue = new ArrayDeque<>();
        Deque<Integer> hdQueue = new ArrayDeque<>();
        Map<Integer, List<TreeNode>> map = new HashMap<>();

        nodeQueue.offer(root);
        hdQueue.offer(0);

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        while (!nodeQueue.isEmpty()) {
            int size = nodeQueue.size();
            for (int i = 0; i < size; i++) {
                TreeNode currNode = nodeQueue.poll();
                int currHD = hdQueue.poll();

                min = Math.min(min, currHD);
                max = Math.max(max, currHD);

                // add the current node into result list
                if (!map.containsKey(currHD)) {
                    List<TreeNode> list = new ArrayList<>();
                    list.add(currNode);
                    map.put(currHD, list);
                } else {
                    map.get(currHD).add(currNode);
                }

                if (currNode.left != null) {
                    nodeQueue.offer(currNode.left);
                    hdQueue.offer(currHD - 1);
                }

                if (currNode.right != null) {
                    nodeQueue.offer(currNode.right);
                    hdQueue.offer(currHD + 1);
                }
            }
        }

        // another requirement
        List<TreeNode> topResultLine = new ArrayList<>();

        // we need to generate the result
        for (int distance = min; distance <= max; distance++) {
            topResultLine.add(map.get(distance).get(0));
            result.add(map.get(distance));
        }

        System.out.println(topResultLine);

        return result;
    }

}
