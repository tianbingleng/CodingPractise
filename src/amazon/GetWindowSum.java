package amazon;
import java.util.*;

/**
 * Created by tianbingleng on 4/9/2017.
 */
public class GetWindowSum {
    public static void main(String[] args) {
        List<Integer> window = new ArrayList<Integer>();
            window.add(1);
            window.add(2);
            window.add(3);
            window.add(4);
            window.add(5);
            window.add(6);

        List<Integer> res = new ArrayList<Integer>();
        res = getWindowSum(window, 3);
        System.out.println(res);
    }

    static public List<Integer> getWindowSum(List<Integer> list, int k) {
        List<Integer> result = new ArrayList<Integer>();
        if (list == null || list.size() == 0 || k <= 0) {
            return result;
        }
        // 0 1 2 3 4 5
        // - - -
        // i = 0 ~ size - k + 1
        int size = list.size();
        for (int i = 0; i < size - k + 1; i++) {
            int sum = 0;
            for (int j = i; j < i + k; j++) {
                sum += list.get(j);
            }
            result.add(sum);
        }
        return result;
    }
}