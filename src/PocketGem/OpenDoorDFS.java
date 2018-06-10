package PocketGem;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
public class OpenDoorDFS {
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
        List<Node> res = find(board);
        System.out.println(parsePath(res));
    }

    public static List<Node> find(char[][] board) {
        List<Node> result = new ArrayList<>();
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '2') {
                    List<Node> path = new ArrayList<>();
                    dfs(board, visited, path, result, i, j, new HashSet<Character>());
                }
            }
        }
        return result;
    }

    public static void dfs(char[][] board, boolean[][] visited, List<Node> path, List<Node> result, int x, int y, Set<Character> keys) {
        // base case , out of bound, obstacle,  CURRENT VISITED , no need to continue
        if (x < 0 || y < 0 || x == board.length || y == board[0].length || board[x][y] == '0' || visited[x][y]) {
            return;
        }

        char c = board[x][y];
        // base case, find the target
        if (c == '3') {
            // only add first time
            // or the small size path
            if (result.isEmpty() || path.size() < result.size()) {
                result.clear();
                path.add(new Node(x, y));
                result.addAll(path);
                path.remove(path.size() - 1);
            }
            return;
        }

        // now we continue
        // if it is a door
        if (c >= 'A' && c <= 'Z') {
            // if there is no key in the door, we can't go, just return
            if (!keys.contains(c)) {
                return;
            }
            // else we can open the door and keep going
        }

        // if we find a key
        if (c >= 'a' && c <= 'z') {
            // if it is already exist, we do nothing
            // but if it is a NEW key, we will add into set
            // AND ALL VISITED SET FALSE, we will go again
            if (!keys.contains(Character.toUpperCase(c))) {
                keys.add(Character.toUpperCase(c));
                visited = new boolean[board.length][board[0].length];
            }
        }

        // then the next step
        visited[x][y] = true;
        path.add(new Node(x, y));

        dfs(board, visited, path, result, x + 1, y, keys);
        dfs(board, visited, path, result, x - 1, y, keys);
        dfs(board, visited, path, result, x, y + 1, keys);
        dfs(board, visited, path, result, x, y - 1, keys);

        visited[x][y] = false;
        path.remove(path.size() - 1);
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
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }


}
