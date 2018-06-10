package ABC;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tianbingleng on 3/12/2017.
 */
public class ATM {
    public static void main(String[] args) {
        Map<Integer, Integer> coinMap = new HashMap<>();

//        coinMap.put(500, 10);
//        coinMap.put(100, 10);
//        coinMap.put(50, 10);
        coinMap.put(20, 10);
        coinMap.put(10, 10);
        coinMap.put(5, 10);
        coinMap.put(1, 10);

        //Map<Integer, Integer> result = solution(coinMap, 2457);
        Map<Integer, Integer> result = solution(coinMap, 3);

        System.out.println(result);
    }

    public static Map<Integer, Integer> solution(Map<Integer, Integer> inputs, int target) {
        Map<Integer, Integer> solutionMap = new HashMap<>();
        //int[] order = new int[]{500, 100, 50, 20, 10, 5, 1};
        int[] order = new int[]{ 20, 10, 5, 1};
        int currIndex = 0;
        while (currIndex != order.length) {
            int faceValue = order[currIndex];
            // ideal how many count should get
            int count = target / faceValue;
            // get some of them
            if (count <= inputs.get(faceValue)) {
                inputs.put(faceValue, inputs.get(faceValue) - count);
                target -= count * faceValue;
                solutionMap.put(faceValue, count);
            } else { // get them all
                solutionMap.put(faceValue, inputs.get(faceValue));
                target -= inputs.get(faceValue) * faceValue;
                inputs.put(faceValue, 0);
            }
            // exact get the money successful
            if (target == 0) {
                return solutionMap;
            }
            currIndex++;
        }
        // just return empty map, no result
        return new HashMap<>();
    }
}
