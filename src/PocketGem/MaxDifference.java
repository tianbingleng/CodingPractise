package PocketGem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tianbingleng on 10/10/2017.
 */
public class MaxDifference {
    public static void main (String[] args) {
        int g_nodes = 7;
        int[] g_from = new int[]{1,2,3,4,5,6};
        int[] g_to = new int[]{2,3,1,5,6,7};

        System.out.println(maximumDifference(g_nodes, g_from, g_to));
    }
    // Q1. CanReach
    static public String canReach(int x1, int y1, int x2, int y2) {
        return canReachHelper(x1, y1, x2, y2) ? "Yes" : "No";
    }

    static public boolean canReachHelper(int x1, int y1, int x2, int y2) {
        if (x1 > x2 || y1 > x2) {
            return false;
        }
        if (x1 == x2 && y1 == y2) {
            return true;
        }
        return canReachHelper(x1 + y1, y1, x2, y2) || canReachHelper(x1, x1 + y1, x2, y2);
    }

    // Q2. MaxDifference
    static public int maximumDifference(int g_nodes, int[] g_from, int[] g_to) {
        if (g_nodes == 0 || g_from == null || g_to == null) {
            return 0;
        }
        // build a nodeMap
        Map<Integer, List<Integer>> nodeMap = new HashMap<>();
        for (int i = 0; i < g_from.length; i++) {
            if (!nodeMap.containsKey(g_from[i])) {
                List<Integer> list = new ArrayList<>();
                list.add(g_to[i]);
                nodeMap.put(g_from[i], list);
            } else {
                nodeMap.put(g_from[i], nodeMap.get(g_from[i]));
            }
        }
        // dfs for all the values
        int maxDiff = 0;
        boolean[] visited = new boolean[g_nodes + 1];
        for (int node : nodeMap.keySet()) {
            if (!visited[node]) {
                visited[node] = true;
                // [0] is max, [1] is min
                // initial must be the current node
                int[] globalMaxMin = new int[]{node, node};
                DFSHelper(nodeMap, node, globalMaxMin, visited);
                maxDiff = Math.max(maxDiff, globalMaxMin[0] - globalMaxMin[1]);
                //System.out.println("this graph maxDiff="+ maxDiff);
            }
        }
        return maxDiff;
    }
    static public void DFSHelper(Map<Integer, List<Integer>> nodeMap, int node, int[] globalMaxMin, boolean[] visited) {
        if (!nodeMap.containsKey(node)) {
            return;
        }
        // map contain this key, it MUST has a neighbor
        for (int neighbor : nodeMap.get(node)) {
            if (!visited[neighbor]) {
                visited[neighbor] = true;
                //System.out.println("current neighbor="+ neighbor);
                globalMaxMin[0] = Math.max(globalMaxMin[0], neighbor);
                globalMaxMin[1] = Math.min(globalMaxMin[1], neighbor);
                DFSHelper(nodeMap, neighbor, globalMaxMin, visited);
            }
        }
    }
}
