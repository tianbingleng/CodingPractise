////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

/*
 * Copyright © 2018 Unified Social, Inc.
 * 180 Madison Avenue, 23rd Floor, New York, NY 10016, U.S.A.
 * All rights reserved.
 *
 * This software (the "Software") is provided pursuant to the license agreement you entered into with Unified Social,
 * Inc. (the "License Agreement").  The Software is the confidential and proprietary information of Unified Social,
 * Inc., and you shall use it only in accordance with the terms and conditions of the License Agreement.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND "AS AVAILABLE."  UNIFIED SOCIAL, INC. MAKES NO WARRANTIES OF ANY KIND, WHETHER
 * EXPRESS OR IMPLIED, INCLUDING, BUT NOT LIMITED TO THE IMPLIED WARRANTIES AND CONDITIONS OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT.
 */

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

package amazon;
import java.util.*;

public class SaveInBatch {
    public static void main(String[] args) {
        List<Item> lists = new ArrayList<>();
        int sizeA = 5;
        int sizeB = 4;
        int sizeC = 5;
        int batchSize = 3;
        int id = 0;
        for (int i = 0; i < sizeA; i++) {
            lists.add(new Item(id++, "A"));
        }
        for (int i = 0; i < sizeB; i++) {
            lists.add(new Item(id++, "B"));
        }
        for (int i = 0; i < sizeC; i++) {
            lists.add(new Item(id++, "C"));
        }
        System.out.println(lists);

        Map<String, List<Item>> itemMap = new HashMap<>();
        for (Item item : lists) {
            if (!itemMap.containsKey(item.key)) {
               itemMap.put(item.key, new ArrayList<>());
            }
            itemMap.get(item.key).add(item);
        }
        System.out.println(itemMap);

        PriorityQueue<Map.Entry<String, List<Item>>> maxHeap = new PriorityQueue<>(new Comparator<Map.Entry<String, List<Item>>>() {
            @Override
            public int compare (Map.Entry<String, List<Item>> o1, Map.Entry<String, List<Item>> o2) {
                return o2.getValue().size() - o1.getValue().size();
            }
        });

        for (Map.Entry<String, List<Item>> entry : itemMap.entrySet()) {
            maxHeap.offer(entry);
        }
        List<List<Item>> result = new ArrayList<>();
        List<List<Item>> buffedLists = new ArrayList<>();
        while (!maxHeap.isEmpty()) {
            Map.Entry<String, List<Item>> entry = maxHeap.poll();
            List<Item> list = entry.getValue();
            while (list.size() >= batchSize) {
                List<Item> batchList = moveFirstKItem(list, batchSize);
                result.add(batchList);
            }
            if (list.size() > 0) {
                buffedLists.add(moveFirstKItem(list, list.size()));
            }
        }
        System.out.println("RESULT LIST:" + result);
        System.out.println("BUFFER LIST:" + buffedLists);
        // now optimize buffer list into result list

    }

    static private List<Item> moveFirstKItem(List<Item> list, int k) {
        List<Item> batchList = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            batchList.add(list.remove(0));
        }
        return batchList;
    }

    static class Item {
        int id;
        String key;
        public Item(int id, String key) {
            this.id = id;
            this.key = key;
        }
        public String toString(){
            return "["+key+","+id+"]";
        }
    }
}
