package amazon;
import java.util.*;

/**
 * Created by tianbingleng on 4/9/2017.
 */
class Order {
    String order = "";

    public Order(String string) {
        this.order = string;
    }
}

class OrderDependency {
    Order cur;
    Order pre;

    public OrderDependency(Order o1, Order o2) {
        this.cur = o1;
        this.pre = o2;
    }
}

public class OrderDependencyProblem {
    public static void main(String[] args) {
        Order oa = new Order("A");
        Order ob = new Order("B");
        Order oc = new Order("C");
        Order od = new Order("D");
        Order oe = new Order("A");
        // Test Case1(DCBA)
//         OrderDependency od1 = new OrderDependency(oa, ob);
//         OrderDependency od2 = new OrderDependency(ob, oc);
//         OrderDependency od3 = new OrderDependency(oc, od);
//        // Test Case2 (DCBA)
//        OrderDependency od1 = new OrderDependency(oa, ob);
//        OrderDependency od2 = new OrderDependency(oa, oc);
//        OrderDependency od3 = new OrderDependency(oc, od);
//        OrderDependency od4 = new OrderDependency(ob, oc);
//        OrderDependency od5 = new OrderDependency(ob, od);
//        // Test Case3(DBAC)
         OrderDependency od1 = new OrderDependency(oa, ob);
         OrderDependency od2 = new OrderDependency(oe, oc);
         OrderDependency od3 = new OrderDependency(oc, od);
         OrderDependency od4 = new OrderDependency(ob, oc);

//
//        ArrayList<OrderDependency> list = new ArrayList<>();
//        list.add(od1);
//        list.add(od2);
//        list.add(od3);
//        list.add(od4);
//        list.add(od5);
//        for (Order o : getOrderList(list)) {
//            System.out.println(o.order);
//        }

//        Order oa = new Order("A");
//        Order ob = new Order("B");
//        Order oc = new Order("C");
//        Order od = new Order("D");
//        Order oe = new Order("E");
//        Order of = new Order("F");
//        OrderDependency od1 = new OrderDependency(oa, ob);
//        OrderDependency od2 = new OrderDependency(oa, oc);
//        OrderDependency od3 = new OrderDependency(oa, od);
//        OrderDependency od4 = new OrderDependency(ob, od);
//        OrderDependency od5 = new OrderDependency(oc, oe);
//        OrderDependency od6 = new OrderDependency(oe, of);

        ArrayList<OrderDependency> list = new ArrayList<>();
        list.add(od1);
        list.add(od2);
        list.add(od3);
        list.add(od4);
//        list.add(od5);
//        list.add(od6);
        for (Order o : getOrderList(list)) {
            System.out.println(o.order);
        }

    }
    public static List<Order> getOrderList(List<OrderDependency> ods) {
        List<Order> result = new ArrayList<Order>();

        Map<String, List<String>> orderMap = new HashMap<>();
        Set<String> set = new HashSet<>();
        // add all the cur, pre into list
        for (OrderDependency od : ods) {
            String cur = od.cur.order;
            String pre = od.pre.order;
            set.add(cur);
            set.add(pre);
            if (!orderMap.containsKey(cur)) {
                List<String> preList = new ArrayList<>();
                preList.add(pre);
                orderMap.put(cur, preList);
            } else {
                if (!orderMap.get(cur).contains(pre)) {
                    orderMap.get(cur).add(pre);
                }
            }
        }

        System.out.println(orderMap);

        // mark all the order as false
        Map<String, Boolean> visitedMap = new HashMap<>();
        for (String order : set) {
            visitedMap.put(order, false);
        }

        Deque<String> queue = new ArrayDeque<>();

        for (String order : set) {
            // if not visited
            if (!visitedMap.get(order)) {
                // if DFS return false, means it has an cycle, then just return false
                if (!DFSCheckingCycle(order, orderMap, visitedMap, queue, new HashSet<String>())) {
                    new ArrayList<Order>();
                }
            }
        }

        while (!queue.isEmpty()) {
            Order od = new Order(queue.poll());
            result.add(od);
        }
        return result;
    }

    public static boolean DFSCheckingCycle(String curr, Map<String, List<String>> orderMap, Map<String, Boolean> visitedMap, Deque<String> queue, HashSet<String> set) {
        if (set.contains(curr)) {
            return false;
        }

        set.add(curr);
        if (orderMap.get(curr) != null) {
            for (String pre : orderMap.get(curr)) {
                if (!DFSCheckingCycle(pre, orderMap, visitedMap, queue, set)) {
                    return false;
                }
            }
        }

        set.remove(curr);
        if (!visitedMap.get(curr)) { // if it is not been visited, we will push it into stack
            queue.offer(curr);
        }

        visitedMap.put(curr, true);
        return true;
    }


}