import java.util.*;

/**
 * Created by tianbingleng on 21/6/2017.
 */
public class ZZZRainbowSort {
    static public void main(String[] args) {

        /*
        Question 0.

        Rainbow Sort.

        Assumptions

        The array is not null

        Examples
        {1, 1, -1, 0, -1, 1, -1, 0} → {-1, -1, -1, 0, 0, 1, 1, 1}

        */
        int[] array0 = new int[]{1, 1, -1, 0, -1, 1, -1, 0};
        System.out.println(Arrays.toString(array0));
        rainbowSort(array0);
        System.out.println(Arrays.toString(array0));

        // Question 1.
        // What if there is four color need to sort like 0, 1, 2, 3
        int[] array1 = new int[]{1, 1, 3, 2, 1, 2, 0, 3, 1, 3, 0, 2};
        System.out.println(Arrays.toString(array1));
        left = 0;
        right = array1.length - 1;
        rainbowSort(array1, 0, 3);
        System.out.println(Arrays.toString(array1));
        rainbowSort(array1, 1, 2);
        System.out.println(Arrays.toString(array1));

        // Question 2.
        // if it is more than three colors, we may use a comparator
        Node n1 = new Node(Color.Red);
        Node n2 = new Node(Color.Green);
        Node n3 = new Node(Color.Red);
        Node n4 = new Node(Color.Green);
        Node n5 = new Node(Color.Blue);
        Node n6 = new Node(Color.Green);
        Node n7 = new Node(Color.Red);
        Node n8 = new Node(Color.Blue);
        Node n9 = new Node(Color.Red);
        Node n10 = new Node(Color.Yello);
        List<Node> list = new ArrayList<>();
        list.add(n1);
        list.add(n2);
        list.add(n3);
        list.add(n4);
        list.add(n5);
        list.add(n6);
        list.add(n7);
        list.add(n8);
        list.add(n9);
        list.add(n10);
        System.out.println(list);
        Collections.sort(list, new NodeColorComparator());
        System.out.println(list);

    }

    static class NodeColorComparator implements Comparator<Node> {
        @Override
        public int compare(Node n1, Node n2) {
            return n1.color.ordinal() - n2.color.ordinal();
        }
    }

    static class Node {
        Color color;
        public Node(Color color) {
            this.color = color;
        }
        public String toString() {
            return this.color.toString();
        }
    }

    enum Color{
        Red,
        Yello,
        Blue,
        Green
    }

    // for question 2.
    static int left; //global for rainbowsort 4 colors
    static int right; //global for rainbowsort 4 colors

    // for question 2.
    public static void rainbowSort(int[] array, int a, int b) {
        if (array == null || array.length == 0) {
            return;
        }
        int i = left;
        int j = left;
        int k = right;

        // 三个区域，由 i, j, k 分开
        // [0, i) 为 负数， -1， left side of i, excluding i.
        // [i, j) 为 0
        // [j, k] 为 正在进行，探索的区域
        // (k, length - 1] 为 正数， right side of k, excluding k.

        while (j <= k) {
            if (array[j] == b) {
                swap(array, j, k);
                k--;
            } else if (array[j] == a){
                swap(array, i, j);
                i++;
                j++;
            } else {
                j++;
            }
        }
        left = i;
        right = k;
    }

    // for question 1.
    public static void rainbowSort(int[] array) {
        if (array == null || array.length == 0) {
            return;
        }
        int i = 0;
        int j = 0;
        int k = array.length - 1;

        // 三个区域，由 i, j, k 分开
        // [0, i) 为 负数， -1， left side of i, excluding i.
        // [i, j) 为 0
        // [j, k] 为 正在进行，探索的区域
        // (k, length - 1] 为 正数， right side of k, excluding k.

        while (j <= k) {
            if (array[j] > 0) {
                swap(array, j, k);
                k--;
            } else if (array[j] < 0){
                swap(array, i, j);
                i++;
                j++;
            } else {
                j++;
            }
        }
    }
    public static void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    // Follow up
    // what about many colors? Sort two each time. And then call again.
}
