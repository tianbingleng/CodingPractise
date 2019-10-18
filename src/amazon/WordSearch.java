
package amazon;
import java.util.*;

public class WordSearch {
    public static void main(String[] args) {
        String[] words = new String[]{"abc", "mode", "abcde", "abcdf", "moae", "moae"};
        char[][] grid = new char[][]{{'m', 'b','c','d'},
                                     {'o', 'a','e','f'},
                                     {'a', 'd','a', 'k'}};
        System.out.println(findWords(grid, words));
    }

    // https://www.youtube.com/watch?v=aEEJ3xHIF5o
    // Time: (m*n*4^L*单词数)
    static private List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();
        if (words == null || words.length == 0) {
            return result;
        }


        boolean[][] isVisited = new boolean[board.length][board[0].length];

        for (String word : words) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (DFSHelper(board, isVisited, word, i, j, 0)) {
                        if (!result.contains(word)) result.add(word);
                    }
                }
            }
        }

        return result;
    }
    // Word Search I. 没有变
    static private boolean DFSHelper(char[][] board, boolean[][] isVisited, String word, int i, int j, int index) {
        // we found the word!!
        if (index == word.length()) return true;

        if (i < 0 || i == board.length || j < 0 || j == board[0].length) {
            return false;
        }

        if (isVisited[i][j]) return false;
        if (board[i][j] != word.charAt(index)) return false;

        //mark for visited
        isVisited[i][j] = true;
        boolean exist = DFSHelper(board, isVisited, word, i + 1, j, index + 1)
                        || DFSHelper(board, isVisited, word, i - 1, j, index + 1)
                        || DFSHelper(board, isVisited, word, i, j + 1, index + 1)
                        || DFSHelper(board, isVisited, word, i, j - 1, index + 1);

        isVisited[i][j] = false;
        return exist;
    }

    // Example for 8 direction
//        for (int a = -1; a <= 1; a++) {
//            for (int b = -1; b <= 1; b++) {
//                explore(visited, grid, i + a, j + b, sb, result);
//            }
//        }

}
