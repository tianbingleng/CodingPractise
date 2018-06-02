package audible;

import java.util.*;

/**
 * Created by tianbingleng on 27/10/2017.
 */
public class BugdetShopping {
    static public void main(String[] args) {
//        int n = 50;
//        int[] bundleQuantities = {20, 19};
//        int[] bundleCosts = {24, 20};
        int n = 4;
        int[] bundleQuantities = {10};
        int[] bundleCosts = {2};
        System.out.println(solution(n, bundleQuantities, bundleCosts));

    }

    static public int solution(int n, int[] bundleQuantities, int[] bundleCosts) {
        if (n == 0 || bundleQuantities.length == 0 || bundleCosts.length == 0) {
            return 0;
        }
        int[] dp = new int[n + 1];
        List<Pair> list = new ArrayList<>();
        for (int i = 0; i < bundleQuantities.length; i++) {
            list.add(new Pair(bundleQuantities[i], bundleCosts[i]));
        }
        Collections.sort(list);
        //System.out.println(list);
        for (int i = list.get(0).cost; i <= n; i++) {
            for (Pair pair : list) {
                if (i < pair.cost) {
                    break;
                }
                //System.out.println(i - pair.cost);
                if (dp[i - pair.cost] + pair.quantity > dp[i]) {
                    dp[i] = dp[i - pair.cost] + pair.quantity;
                }
            }
        }
        //System.out.println(Arrays.toString(dp));
        return dp[dp.length - 1];

    }
    static class Pair implements Comparable<Pair>{
        int quantity;
        int cost;
        Pair (int quantity, int cost) {
            this.quantity = quantity;
            this.cost = cost;
        }
        @Override
        public int compareTo(Pair p) {
            if (this.cost == p.cost) {
                return this.quantity - p.quantity;
            } else {
                return this.cost - p.cost;
            }
        }
        public String toString() {
            return "("+ cost +","+ quantity +")";
        }
    }
}
