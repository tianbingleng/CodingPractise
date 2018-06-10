package PocketGem;

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


        // Question 2.1
        // if only three colors, we customize rainbow sort
        ThreeColor[] threecolorArray = new ThreeColor[10];
        threecolorArray[0] = ThreeColor.Red;
        threecolorArray[1] = ThreeColor.Green;
        threecolorArray[2] = ThreeColor.Red;
        threecolorArray[3] = ThreeColor.Green;
        threecolorArray[4] = ThreeColor.Blue;
        threecolorArray[5] = ThreeColor.Green;
        threecolorArray[6] = ThreeColor.Red;
        threecolorArray[7] = ThreeColor.Blue;
        threecolorArray[8] = ThreeColor.Red;
        threecolorArray[9] = ThreeColor.Green;
        System.out.println("==========Q2.1========");
        System.out.println(Arrays.toString(threecolorArray));
        // Arrays.sort(threecolorArray); // this works
        rainbowSort(threecolorArray, ThreeColor.Red, ThreeColor.Blue); // min & max
        System.out.println(Arrays.toString(threecolorArray));

        // Question 2.2
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
        System.out.println("==========Q2.2========");
        System.out.println(list);
        Collections.sort(list, new NodeColorComparator());
        System.out.println(list);

        // Question 3. （in array）
        // if it is more than three colors, we may use a comparator
        Color[] colorArray = new Color[10];
        colorArray[0] = Color.Red;
        colorArray[1] = Color.Green;
        colorArray[2] = Color.Red;
        colorArray[3] = Color.Green;
        colorArray[4] = Color.Blue;
        colorArray[5] = Color.Green;
        colorArray[6] = Color.Red;
        colorArray[7] = Color.Blue;
        colorArray[8] = Color.Red;
        colorArray[9] = Color.Yello;
        System.out.println("==========Q3========");
        System.out.println(Arrays.toString(colorArray));
        //Arrays.sort(colorArray);
        System.out.println(Arrays.toString(colorArray));


        // Exercise. （same as Q3）
        // Sort a array of object in 4 color
        // Red, Yello, Blue, Green
        System.out.println("==========Q.Exercise========");
        left = 0;
        right = colorArray.length - 1;
        System.out.println(Arrays.toString(colorArray));
        swapExersice(colorArray, Color.Red, Color.Green);
        swapExersice(colorArray, Color.Yello, Color.Blue);
        System.out.println(Arrays.toString(colorArray));


    }

    static void swapExersice(Color[] array, Color low, Color top) {
        if (array == null || array.length == 0) {
            return;
        }
        int i = left;
        int j = left;
        int k = right;
        while (j <= k) {
            if (array[j] == top) {
                swap(array, j, k);
                k--;
            } else if (array[j] == low) {
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

    enum ThreeColor{
        Red,
        Green,
        Blue,
    }

//    enum Color{
//        Yello,
//        Green,
//        Red,
//        Blue
//    }

    // for question 2.1
    public static void rainbowSort(ThreeColor[] array, ThreeColor a, ThreeColor b) {
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
            if (array[j] == b) {
                swap(array, j, k);
                k--;
            } else if (array[j] == a){
                swap(array, i, j);
                i++;
                j++; // swap it is middle(0), we just move one
            } else {
                j++; // 0, just move it
            }
        }
    }

    // for question 1.
    static int left; //global for rainbowsort 4 colors
    static int right; //global for rainbowsort 4 colors

    // for question 1.
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

    public static void swap(ThreeColor[] array, int a, int b) {
        ThreeColor temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    public static void swap(Color[] array, int a, int b) {
        Color temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    // Follow up
    // what about many colors? Sort two each time. And then call again.
}
