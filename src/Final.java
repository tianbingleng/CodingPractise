import java.util.*;

/**
 * Created by tianbingleng on 21/6/2017.
 */
public class Final {
    static public void main(String[] args) {
        //add key value pairs to TreeMap
//        String input = "ABC";
//        List<String> result = allPermutations(input);
//        System.out.println(result);
        System.out.println(Arrays.toString(new int []{0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18}));
        int a = minCut(18);
        a = method2(18);
    }

    static public int minCut(int number) {
        int[] DP = new int[number + 1];
        DP[0] = 0;
        DP[1] = 1;
        /*
        int i = 1; i <= number; i++
        int j = 1; j * j <= i; j++
        DP[1] = 1*1 = 1
        DP[2] = 1*1 + 1*1 = DP[1] + DP[1] = 2
        DP[3] = DP[1] + DP[2] = 3
        DP[4] = DP[1] + DP[3] = 4
                DP[2] = 2
        DP[5] = DP[1] + DP[4] = 4
              = DP[2] + DP[3] = 5
        */
        for (int i = 2; i <= number; i++) {
            DP[i] = i;
            for (int j = 1; j < i; j++) {
                if (j * j == i) {
                    DP[i] = 1;
                } else {
                    DP[i] = Math.min(DP[i], DP[j] + DP[i - j]);
                }
            }
        }
        System.out.println(Arrays.toString(DP));
        return DP[number];
    }

    static public int method2(int n) {
        if (n <= 0) {
            return 0;
        }
        int[] M = new int[n + 1];
        M[0] = 0;
        for (int i = 1; i <= n; i++) {  		// grow 1 number at a time
            M[i] = i;
            for (int j = 1; j * j <= i; j++) {			//   sqrt(i)
                M[i] = Math.min(M[i], 1 +   M[i - j * j]);
            }
        }
        System.out.println(Arrays.toString(M));
        return M[n];

    }


    static public List<String> allPermutations(String input) {
        List<String> result = new ArrayList<String>();
        StringBuilder solution = new StringBuilder("");
        DFS(input, solution, result, 0);
        return result;
    }

    static public void DFS(String input, StringBuilder solution, List<String> result, int level) {
        // base case
        if (level == (input.length() - 1)) {
            solution.append(input.charAt(level));
            result.add(solution.toString());
            //solution.deleteCharAt(solution.length() - 1);
            return;
        }
// add current char first
        solution.append(input.charAt(level));

// here we add space
        solution.append(" ");
        DFS(input, solution, result, level + 1);
        solution.deleteCharAt(solution.length() - 1);

// here we dont add space
        DFS(input, solution, result, level + 1);
        solution.deleteCharAt(solution.length() - 1);
    }

}
