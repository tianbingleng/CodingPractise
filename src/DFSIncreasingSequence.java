import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianbingleng on 21/6/2017.
 */
public class DFSIncreasingSequence {
    static public void main(String[] args) {
        // http://www.geeksforgeeks.org/print-all-n-digit-strictly-increasing-numbers/
        // give a n = number of digit
        int n = 3;
        List<String> result = new ArrayList<>();
        DFS(result, new StringBuilder(), 0, 0, n);
        System.out.println(result);
    }

    static public void DFS(List<String> result, StringBuilder sb, int start, int level, int n) {
        // base case
        if (level == n) {
            result.add(sb.toString());
            return;
        }

        for (int i = start; i <= 9; i++) {
            sb.append(i);
            DFS(result, sb, i + 1, level + 1, n);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
