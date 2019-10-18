package PocketGem;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Created by tianbingleng on 10/10/2017.
 */
public class PGBags {

    static class ItemInfo {
        String name;
        int value;
        int maximum_stack_size;

        public ItemInfo(String name, int value, int maximum_stack_size) {
            this.name = name;
            this.value = value;
            this.maximum_stack_size = maximum_stack_size;
        }
    }
    //答 可以用greedy algorithm O(MN) worst case, all item in its own stack
    //一面是RPG GAME， 之前地里有前辈发过这题，解法就是TOPK 变种， 用PQ O(Mlog(N)).
    // or using selection sort, select the top largest k, average using O(M).
    //followup： 如果物品数量很多， 背包格子很少能不能优化，
    // 可以使用Quick Select求出第x个最大值或第x个最小值是多少，这里求出第n（物品栏数量）大的物品是哪个，然后返回这个物品和其他所有比他大的物品，时间O(n+n) = O(n)。
    // 我是按每种东西的最大数量打包，不够的也打成一个包。PQ存包，comparator按照包里的总价值，然后就是k largest了。。

    public static void main (String[] args) {
        ItemInfo[] infors = new ItemInfo[3];
        infors[0] = new ItemInfo("diamond", 10, 5);
        infors[1] = new ItemInfo("ruby", 5, 5);
        infors[2] = new ItemInfo("armor", 25, 1);
        String[] items = {"diamond", "ruby", "armor", "diamond", "diamond", "ruby", "diamond", "diamond", "diamond", "diamond", "diamond", "armor"};
        System.out.println(fillBags(3, items, infors));

    }

    //一面是RPG GAME， 之前地里有前辈发过这题，解法就是TOPK 变种， 用PQ O(Mlog(N)).
    // or using selection sort, select the top largest k, average using O(M).
    // return the value of the total amount of the items
    private static int fillBags(int n, String[] items, ItemInfo[] infors) {
        if (items == null || items.length == 0) {
            return 0;
        }

        // create a map to put all item name, item object
        Map<String, ItemInfo> itemMap = new HashMap<>();
        for (ItemInfo item : infors) {
            itemMap.put(item.name, item);
        }

        // get all frequency based on item
        Map<ItemInfo, Integer> freqMap = new HashMap<>();
        for (String key : items) {
            // current count for this item in the freq-map
            int count = freqMap.getOrDefault(itemMap.get(key), 0);
            freqMap.put(itemMap.get(key), count + 1);
        }
        //System.out.println(freqMap);

        // using minHeap, contains total n element, if it is > n, poll the top
        // so the smallest always be returned
        // n log k
        // = total element * log (bag size)
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (ItemInfo item : freqMap.keySet()) {
            // filter current item first (with max stack_size)
            // if it is oversize
            while (freqMap.get(item) >= item.maximum_stack_size) {
                minHeap.offer(item.value * item.maximum_stack_size);
                freqMap.put(item, freqMap.get(item) - item.maximum_stack_size);
                //System.out.println("insert value:"+item.value * item.maximum_stack_size);
            }
            // it is not oversize, if it is > 0, add the rest into heap
            if (freqMap.get(item) > 0) {
                minHeap.offer(item.value * freqMap.get(item));
                //System.out.println("insert value:"+item.value * freqMap.get(item));
            }
            // check whether current heap is > k size
            while (minHeap.size() > n) {
                minHeap.poll();
            }
        }

        int amount = 0;
        while (!minHeap.isEmpty()) {
            amount += minHeap.poll();;
        }

        return amount;
    }


}
