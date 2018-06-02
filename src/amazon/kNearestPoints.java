package amazon;
import java.util.*;

/**
 * Created by tianbingleng on 4/9/2017.
 */

class Point {
    double x;
    double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
}

public class kNearestPoints {
    public static void main(String[] args) {
        Point a1 = new Point(3.2, 7.8);
        Point b1 = new Point(4.1, 3.3);
        Point c1 = new Point(2.5, 6.1);
        Point d1 = new Point(-5.0, -3.1);
        Point e1 = new Point(-1.2, -3.5);
        Point f1 = new Point(-32, 0.3);
        Point g1 = new Point(6.7, -2.4);
//        Point h1 = new Point(-5.0, -3.1);
        Point origin = new Point(0, 0);

        Point[] test_arr = new Point[7];

        test_arr[0] = a1;
        test_arr[1] = b1;
        test_arr[2] = c1;
        test_arr[3] = d1;
        test_arr[4] = e1;
        test_arr[5] = f1;
        test_arr[6] = g1;
//        test_arr[7] = h1;

        int k = 4;
        Point[] answer = new Point[k];
        answer = findNearest(test_arr, origin, k);

        for (int i = 0; i < test_arr.length; i++) {
            System.out.println(test_arr[i].x + ", " + test_arr[i].y + ",\t\t"
                    + getDistance(test_arr[i], origin));
        }

        System.out.println("Answer");

        for (int i = 0; i < answer.length; i++) {
            System.out.println(answer[i].x + ", " + answer[i].y + ",\t\t"
                    + getDistance(answer[i], origin));
        }
    }
    static Point[] findNearest(Point[] array, Point origin, int k) {
        // corner case
        if (array == null || array.length == 0 || k <=0) {
            return new Point[0];
        }

        // this is a maxheap
        PriorityQueue<Point> pq = new PriorityQueue<>(new Comparator<Point>(){
            @Override
            public int compare(Point one, Point two) {
                double diff = getDistance(two, origin) - getDistance(one, origin);
                if (diff > 0) {
                    return 1;
                } else if (diff < 0){
                    return -1;
                } else {
                    return 0;
                }
            }
        });

        for (int i = 0; i < k; i++) {
            pq.offer(array[i]);
        }
        for (int i = k; i < array.length; i++) {
            Point temp = pq.peek();
            if (getDistance(temp, origin) > getDistance(array[i], origin)) {
                pq.poll();
                pq.offer(array[i]);
            }
        }

        Point[] result = new Point[k];
        for (int i = k - 1; i >= 0; i--) {
            result[i] = pq.poll();
        }
        return result;
    }

    static double getDistance(Point a, Point b) {
        return Math.sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y));
    }

}