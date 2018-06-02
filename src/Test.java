import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by tianbingleng on 21/6/2017.
 */
public class Test {
    static public void main(String[] args) {

//    int[] numbers = new int[]{0,1,2,0};
//    System.out.println(Arrays.toString(twoSum(numbers,0)));

//        System.out.println(Integer.toBinaryString(7));
//        System.out.println(Integer.toBinaryString(8));
//        System.out.println(Integer.toBinaryString(9));

//        String str = "abc";
//        System.out.println(str.substring(0,2));
//        System.out.println(str.substring(0,3));


        TreeMap treeMap = new TreeMap();

        //add key value pairs to TreeMap
        treeMap.put("1","One");
        treeMap.put("3","Three");
        treeMap.put("2","Two");
        treeMap.put("5","Five");
        treeMap.put("4","Four");



        
        treeMap.remove("1");
        treeMap.remove("5");

        System.out.println("Lowest key Stored in Java TreeMap is : "
                + treeMap.firstKey());

        System.out.println("Highest key Stored in Java TreeMap is : "
                + treeMap.lastKey());


    }


    public static int[] twoSum(int[] numbers, int target) {
        int[] result = new int[2];
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < numbers.length; i++) {
            if (map.containsKey(target - numbers[i])) {
                result[1] = i + 1;
                result[0] = map.get(target - numbers[i]);
                return result;
            }
            map.put(numbers[i], i + 1);
        }
        return result;
    }
}
