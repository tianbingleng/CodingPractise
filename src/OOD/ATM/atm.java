package OOD.ATM;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tianbingleng on 10/11/2017.
 */
public class atm {
    static int[] money = {500,100,50,10,1};

    public static void main(String[] args) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(money[0], 3);
        map.put(money[1], 3);
        map.put(money[2], 3);
        map.put(money[3], 3);
        map.put(money[4], 7);

        int amount = 174;
        Map<Integer, Integer> result = solution(map, amount);
        System.out.println(result);
    }

    static public Map<Integer, Integer> solution(Map<Integer, Integer> map, int remain) {

        Map<Integer, Integer> result = new HashMap<>();
        int level = 0;
        while (true) {
            if (level == money.length) {
                break;
            }
            int count = remain / money[level];
            // available
            if (count > 0 && count < map.get(money[level])) {
                result.put(money[level], count);
                remain = remain - count * money[level];
            }
            level++;
        }

        if (remain == 0) {
            return result;
        } else {
            return new HashMap<Integer, Integer>();
        }
    }
}
