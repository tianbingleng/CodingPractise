import java.util.*;

/**
 * Created by tianbingleng on 21/6/2017.
 */
public class SpiralOrder {
    static public void main(String[] args) {
        /*
        Question 1.

        Traverse an N * N 2D array in spiral order clock-wise starting from the top left corner.
        Return the list of traversal sequence.

        Assumptions
        The 2D array is not null and has size of N * N where N >= 0

        Examples
        { {1,  2,  3},
          {4,  5,  6},
          {7,  8,  9} }

        the traversal sequence is [1, 2, 3, 6, 9, 8, 7, 4, 5]

        Follow-up, how about a M*N matrix?
        */

        int[][] matrix = new int[][] {
                { 1,  2,  3,  4, 5},
                {16, 17, 18, 19, 6},
                {15, 24, 25, 20, 7},
                {14, 23, 22, 21, 8},
                {13, 12, 11, 10, 9}};

        int[][] matrix1 = new int[][] {
                { 1,  2,  3,  4},
                {12, 13, 14,  5},
                {11, 16, 15,  6},
                {10,  9,  8,  7}};


        int[][] matrix2 = new int[][] {
                { 1,  2,  3,  4},
                {10, 11, 12,  5},
                { 9,  8,  7,  6}};

        int[][] matrix3 = {
                {1,  2,  3},
                {4,  5,  6},
                {7,  8,  9}};

//        List<Integer> result = new ArrayList<Integer>();
//        printSpiralOrder(matrix3, 0, matrix3.length, result);
//        System.out.println(result);

        List<Integer> result = new ArrayList<Integer>();
        printSpiralOrderMN(matrix2, result);
        System.out.println(result);

        /*
        Question 2.

        Rotate an N * N matrix clockwise 90 degrees.

        Assumptions
        The matrix is not null and N >= 0

        Examples
        { {1,  2,  3}
        {8,  9,  4},
        {7,  6,  5} }

        after rotation is
        { {7,  8,  1}
        {6,  9,  2},
        {5,  4,  3} }
        */

        int[][] matrix5 = new int[][] {
                { 1,  2,  3,  4, 5},
                {16, 17, 18, 19, 6},
                {15, 24, 25, 20, 7},
                {14, 23, 22, 21, 8},
                {13, 12, 11, 10, 9}};

//        rotate90(matrix5);
//        printMatrix(matrix5);

    }
    static public void printSpiralOrderMN(int[][] matrix, List<Integer> result) {
        if (matrix.length == 0) { // the matrix is empty, just return and do nothing.
            return;
        }
        int left = 0;
        int right = matrix[0].length - 1;
        int up = 0;
        int down = matrix.length - 1;
        printSpiralOrderMNHelper(matrix, left, right, up, down, result);
    }

    static public void printSpiralOrderMNHelper(int[][] matrix, int left, int right, int up, int down, List<Integer> result) {
        // when this case, left,right and up,down are close to each other, will fail in next round
        // [[1, 2], [3, 4]]
        if (left > right || up > down) {
            return;
        }

        if (left == right) {
            for (int i = up; i <= down; i++) {
                result.add(matrix[i][left]);
            }
            return;
        }
        if (up == down) {
            for (int i = left; i <= right; i++) {
                result.add(matrix[up][i]);
            }
            return;
        }

        // 上边，包括两边端点
        for (int i = left; i <= right; i++) {
            result.add(matrix[up][i]);
        }
        // 右边，不包括两边端点
        for (int i = up + 1; i <= down - 1; i++) {
            result.add(matrix[i][right]);
        }
        // 下边，包括两边端点
        for (int i = right; i >= left; i--) {
            result.add(matrix[down][i]);
        }
        // 左边，不包括两边端点
        for (int i = down - 1; i >= up + 1; i--) {
            result.add(matrix[i][left]);
        }
        printSpiralOrderMNHelper(matrix, left + 1, right - 1, up + 1, down - 1,result);
    }


    static public void printSpiralOrder(int[][] matrix, int offset, int size, List<Integer> result) {
        if (size == 0) {
            return;
        }
        if (size == 1) {
            result.add(matrix[offset][offset]);
            return;
        }
        for (int i = 0; i < size - 1; i++) {
            result.add(matrix[offset][offset + i]);
        }
        for (int i = 0; i < size - 1; i++) {
            result.add(matrix[offset + i][offset + size - 1]);
        }
        for (int i = size - 1; i > 0; i--) {
            result.add(matrix[offset + size - 1][offset + i]);
        }
        for (int i = size - 1; i > 0; i--) {
            result.add(matrix[offset + i][offset]);
        }
        printSpiralOrder(matrix, offset + 1, size - 2, result);
    }

    static public void rotate90(int[][] matrix) {
        // ↖ 点 被 ↙点 取代
        // ↙ 点 被 ↘点 取代
        // ↘ 点 被 ↗点 取代
        // ↗ 点 被 ↖点 取代
        // 要自己画图，多花几个数才可以。。这个太囧了题
        int n = matrix.length;
        int round = n / 2;

        for (int level = 0; level < round; level++) {
            int left = level;
            int right = n - 2 - level;
            for (int i = left; i <= right; i++) {
                int temp = matrix[left][i];
                matrix[left][i] = matrix[n - 1 - i][left];
                matrix[n - 1 - i][left] = matrix[n - 1 - left][n - 1 - i];
                matrix[n - 1 - left][n - 1 - i] = matrix[i][n - 1 - left];
                matrix[i][n - 1 - left] = temp;
            }
        }
    }

    static public void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j]+",");
            }
            System.out.println();
        }
    }

}
