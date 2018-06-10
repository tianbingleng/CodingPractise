package PocketGem;

import java.util.*;

/**
 * Created by tianbingleng on 10/10/2017.
 */

/*
* Problem Statement. From 1point 3acres bbs
Suppose you have a 2-D grid. Each point is either land or water. There is also a start point and a goal.
There are now keys that open up doors. Each key corresponds to one door.

Implement a function that returns the shortest path from the start to the goal using land tiles, keys and open doors.

Data Representation
The map will be passed as an array of strings.

A map can have the following tiles

0 = Water
1 = Land
2 = Start
3 = Goal

uppercase = door
lowercase = key

Example Maps (keys at each step are not required)
`No doors`.
MAP_1 = ['02111',
         '01001',
         '01003',
         '01001',
         '01111']

Solution
(1, 0) with keys ''
(2, 0) with keys ''
(3, 0) with keys ''
(4, 0) with keys ''
(4, 1) with keys ''
(4, 2) with keys ''
`One door`
MAP_2 = ['02a11',.
         '0100A',
         '01003',
         '01001',
         '01111']

Solution
Keys needed: a
(1, 0) with keys ''
(2, 0) with keys ''
(3, 0) with keys 'a'
(4, 0) with keys 'a'
(4, 1) with keys 'a'
(4, 2) with keys 'a'

* */
public class OpenDoorBFS {
    public static void main(String[] args) {
        char[][] board =
                        {{'0', '2', '1', '1', '1'},
                        {'0', '1', '0', '0', '1'},
                        {'0', '0', '0', '0', '1'},
                        {'0', '0', 'A', '0', '1'},
                        {'1', '1', 'a', '1', '1'},
                        {'1', 'b', '0', '0', 'B'},
                        {'1', '1', '0', '0', '1'},
                        {'0', '1', '0', '0', '3'}};
        Node dest =  shortestPath(board);
        if (dest == null) {
            System.out.println("No path.");
        } else {
            while (dest != null) {
                System.out.println(dest);
                dest = dest.parent;
            }
        }
    }

    public static Node shortestPath(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '2') {
                    return findShortestPath(board, i, j);
                }
            }
        }
        return null;
    }

    public static Node findShortestPath(char[][] board, int x, int y) {

        Deque<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(x, y));
        Set<Node> visited = new HashSet<>();

        while (!queue.isEmpty()) {
            Node curr = queue.poll();

            // if it is the target
            if (board[curr.x][curr.y] == '3') {
                return curr;
            }

            // try four directions
            int[] direction = new int[]{-1, 0, 1, 0, -1};
            for (int i = 0; i < direction.length - 1; i++) {
                int nextX = curr.x + direction[i];
                int nextY = curr.y + direction[i + 1];

                // out of bound
                if (nextX < 0 || nextY < 0 || nextX == board.length || nextY == board[0].length) {
                    continue;
                }

                char c = board[nextX][nextY];

                // if it is water
                if (c == '0') {
                    continue;
                }

                // if it is a door
                if (c >= 'A' && c <= 'Z') {
                    // if we dont have the key
                    if (!curr.keys.contains(c)) {
                        continue;
                    }
                }

                Node neighbor = new Node(nextX, nextY);
                neighbor.parent = curr;
                neighbor.keys.addAll(curr.keys);
                // if we found a key
                if (c >= 'a' && c <= 'z') {
                    neighbor.keys.add(Character.toUpperCase(c));
                }
                // whether it is in the same situation
                if (visited.contains(neighbor)) {
                    continue;
                }

                // now we can push the node into the queue
                queue.offer(neighbor);
                visited.add(neighbor);
            }
        }
        // we already finished
        return null;
    }

    public static String parsePath(List<Node> res) {
        StringBuilder sb = new StringBuilder();
        for (Node node : res) {
            sb.append("(").append(node.x).append(",").append(node.y).append(")");
            sb.append("->");
        }
        return sb.toString();
    }

    static class Node {
        int x;
        int y;
        Set<Character> keys;
        Node parent;
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
            keys = new HashSet<>();
            parent = null;
        }
        @Override
        public boolean equals(Object object) {
            Node o = (Node) object;
            return x == o.x && y == o.y && keys.containsAll(o.keys) && o.keys.containsAll(keys);
        }
        @Override
        public String toString() {
            return "x="+ x + ", y=" + y + ". keys = " + keys;
        }
    }


}
