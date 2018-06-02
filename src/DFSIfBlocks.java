import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianbingleng on 21/6/2017.
 */
public class DFSIfBlocks {
    static public void main(String[] args) {
    int n = 3;
    List<String> result = new ArrayList<String>();
    StringBuilder sb = new StringBuilder("");
    DFS(n, sb, 0, 0, result);
    printResult(result);
}

    static public void DFS(int pairs, StringBuilder sb, int leftCount, int rightCount, List<String> result) {
        // base case
        if (leftCount == pairs && rightCount == pairs) {
            result.add(sb.toString());
            return;
        }

        if (leftCount < pairs) {
            // add temp first
            sb.append("{");
            // DFS
            DFS(pairs, sb, leftCount + 1, rightCount, result);
            // backtracking to remove
            sb.deleteCharAt(sb.length() - 1);
        }

        if (rightCount < leftCount) {
            // add temp first
            sb.append("}");
            // DFS
            DFS(pairs, sb, leftCount, rightCount + 1, result);
            // backtracking to remove
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    static public void printResult(List<String> result) {
        for (String str : result) {
            int heading = 0;
            for (char c : str.toCharArray()) {
                if (c == '{') {
                    printSpace(heading);
                    System.out.println("if {");
                    heading += 2;
                } else {
                    heading -= 2;
                    printSpace(heading);
                    System.out.println("}");
                }
            }
            System.out.println();
        }
    }
    static public void printSpace(int heading) {
        for (int i = 0; i < heading; i++) {
            System.out.print(" ");
        }
    }
}
