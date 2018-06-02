package amazon;

import java.util.*;

/**
 * Created by tianbingleng on 4/9/2017.
 */

class Result{
    int id;
    int value;
    public Result(int id, int value){
        this.id = id;
        this.value = value;
    }
}

public class GetHighFive {
    public static void main(String[] args) {
        Result r1 = new Result(1, 99);
        Result r2 = new Result(1, 100);
        Result r3 = new Result(1, 91);
        Result r4 = new Result(1, 0);
        Result r5 = new Result(1, 80);
        Result r6 = new Result(1, 70);

        Result r7 = new Result(2, 80);
        Result r8 = new Result(2,0);
        Result r9 = new Result(2, 100);
        Result r10 = new Result(2, 13);
        Result r11 = new Result(2, 100);
        Result r12 = new Result(2, 99);
        Result[] arr = {r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12};
        Map<Integer, Double> res = getHighFive(arr);

        System.out.println(res.get(1) + " " +res.get(2));
    }
    static public Map<Integer, Double> getHighFive(Result[] array) {
        Map<Integer, Double> scoreMap = new HashMap<>();
        if (array == null || array.length == 0) {
            return scoreMap;
        }

        Map<Integer, PriorityQueue<Integer>> pqMap = new HashMap<>();
        for (Result element : array) {
            if (!pqMap.containsKey(element.id)) {
                PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder());
                pqMap.put(element.id, pq);
                pqMap.get(element.id).offer(element.value);
            } else {
                pqMap.get(element.id).offer(element.value);
            }
        }
        for (int id : pqMap.keySet()) {
            int sum = 0;
            int count = 0;
            while (count < 5) {
                sum += pqMap.get(id).poll();
                count++;
            }
            scoreMap.put(id, (double) sum / 5.0);
        }
        return scoreMap;
    }

}