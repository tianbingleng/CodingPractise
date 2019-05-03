
package amazon;
import java.util.*;

public class FindWord {
    static Set<String> set;
    public static void main(String[] args) {
        set = new HashSet<>();
        set.add("abc");
        set.add("mode");
        set.add("abcde");
        char[][] grid = new char[][]{{'m', 'b','c','d'},
                                     {'o', 'a','e','f'},
                                     {'a', 'd','a', 'k'}};
        System.out.println(getAllWords(grid));
        System.out.println("haha");
    }

    static private List<String> getAllWords(char[][] grid) {
        List<String> result = new ArrayList<>();
        if (grid == null || grid.length == 0) {
            return result;
        }

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                boolean[][] visited = new boolean[grid.length][grid[0].length];
                explore(visited, grid, i, j, new StringBuilder(), result);
            }
        }
        return result;
    }

    static private void explore(boolean[][] visited, char[][] grid, int i, int j, StringBuilder sb, List<String> result) {
        // corner case: index out of bound, visited
        if (i < 0 || i == grid.length || j < 0 || j == grid[0].length || visited[i][j]) {
            return;
        }
        char ch = grid[i][j];
        visited[i][j] = true;
        sb.append(ch);
//        System.out.println("sb="+sb.toString());
        if (set.contains(sb.toString())) {
            result.add(sb.toString());
        }
        // continue explore
        for (int a = -1; a <= 1; a++) {
            for (int b = -1; b <= 1; b++) {
                explore(visited, grid, i + a, j + b, sb, result);
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        visited[i][j] = false;
    }

}
