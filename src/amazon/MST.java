package amazon;

import java.util.*;

/**
 * Created by tianbingleng on 4/9/2017.
 */
class Connection {
    String node1;
    String node2;
    int cost;

    public Connection(String a, String b, int c) {
        super();
        this.node1 = a;
        this.node2 = b;
        this.cost = c;
    }
}

public class MST {
    public static void main(String[] args) {
        ArrayList<Connection> connections = new ArrayList<>();
        connections.add(new Connection("A", "B", 6));
        connections.add(new Connection("B", "C", 4));
        connections.add(new Connection("C", "D", 5));
        connections.add(new Connection("D", "E", 8));
        connections.add(new Connection("E", "F", 2));
        connections.add(new Connection("B", "F", 10));
        connections.add(new Connection("E", "C", 9));
        connections.add(new Connection("F", "C", 7));
        connections.add(new Connection("B", "E", 3));
        connections.add(new Connection("A", "F", 16));

        List<Connection> res = getLowCost(connections);
        for (Connection c : res) {
            System.out.println(c.node1 + "->" + c.node2 + "," + c.cost);
        }
    }

    static int unionNum;

    public static List<Connection> getLowCost(List<Connection> connections) {
        List<Connection> result = new ArrayList<Connection>();
        if (connections == null || connections.size() == 0) {
            return result;
        }
        // sort the list based on the cost
        Collections.sort(connections, new Comparator<Connection>(){
            public int compare(Connection c1, Connection c2) {
                return c1.cost - c2.cost;
            }
        });
        Map<String, Integer> map = new HashMap<>();
        unionNum = 0;
        // add all the connections into result list
        for (Connection conn : connections) {
            String a = conn.node1;
            String b = conn.node2;
            if (union(map, a, b)) {
                result.add(conn);
            }
        }
        // check again to make sure all the points are in the union
        int currUnion = map.get(connections.get(0).node1);
        for (String str : map.keySet()) {
            if (map.get(str) != currUnion) {
                return new ArrayList<Connection>();
            }
        }
        // Successful
        // Sort the list based on the node name
        Collections.sort(result, new Comparator<Connection>(){
            public int compare(Connection c1, Connection c2) {
                if (c1.node1.equals(c2.node1)) {
                    return c1.node2.compareTo(c2.node2);
                }
                return c1.node1.compareTo(c2.node1);
            }
        });
        return result;
    }
    public static boolean union(Map<String, Integer> map, String a, String b) {
        // both a, b not in connections before, now need to connect
        if (!map.containsKey(a) && !map.containsKey(b)) {
            map.put(a, unionNum);
            map.put(b, unionNum);
            unionNum++;
            return true;
        }
        // a is in the connection, b is not
        if (map.containsKey(a) && !map.containsKey(b)) {
            map.put(b, map.get(a));
            return true;
        }
        // b is in the connection, a is not
        if (map.containsKey(b) && !map.containsKey(a)) {
            map.put(a, map.get(b));
            return true;
        }

        // both a,b are in the connection
        int unionA = map.get(a);
        int unionB = map.get(b);
        // if they are same, now it is a cycle, must return false
        if (unionA == unionB) {
            return false;
        }
        // now they are different union, we need to combine into one
        for (String str : map.keySet()) {
            if (map.get(str) == unionB) {
                map.put(str, unionA);
            }
        }
        return true;
    }

}