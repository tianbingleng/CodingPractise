import java.util.Arrays;

/**
 * Created by tianbingleng on 21/6/2017.
 */
public class LargestSquareBoundOf1s {
    static public void main(String[] args) {
        /*
        Given a matrix that contains only 1s and 0s, find the largest X shape which contains only 1s, with the same arm lengths and the four arms joining at the central point.
        Return the arm length of the largest X shape.

        Assumptions
        The given matrix is not null, has size of N * M, N >= 0 and M >= 0.

        Examples

        {{1, 0, 1, 1, 1},
         {1, 1, 1, 1, 1},
         {1, 1, 0, 1, 0},
         {1, 1, 1, 1, 1},
         {1, 1, 1, 0, 0}}

        the square of 1s has arm length 3.

        本题是LongestCrossOf1s变种，这种方法更简洁~
        */

        int[][] array = new int[][] {
                {1, 0, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 0, 1, 0},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 0, 0}};
        printMatrix(array);
        System.out.println(largest(array));

        int[][] array2 = new int[][] {
                {1, 0, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 0, 1, 0},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 0}};
        printMatrix(array2);
        System.out.println(largest(array2));

    }

    static public int largest(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        // 从左到右累计1
        // 从上到下累计1
        // 在从右下往回检验
        int[][] LeftToRight = getLeftToRight(matrix);
        int[][] UpToDown = getUpToDown(matrix);

        return merge(LeftToRight, UpToDown);
    }

    static public int merge(int[][] LeftToRight, int[][] UpToDown) {
        int m = LeftToRight.length;
        int n = LeftToRight[0].length;
        int max = 0;
        int[] TopLeftPoint = {-1, -1};
        // pick rightbottom point
        // 当前点为右下点 small = [i][j]
        // 每次检验右上点 LeftToRight[i - small + 1][j] >= small 代表边长满足条件
        // 每次检验左下点 UpToDown[i][j - small + 1] >= small 代表边长满足条件
        for (int i = m - 1; i > 0; i--) {
            for (int j = n - 1; j > 0; j--) {
                int small = Math.min(
                                LeftToRight[i][j],
                                UpToDown[i][j]);
                while (small > max) {
                    if (LeftToRight[i - small + 1][j] >= small && UpToDown[i][j - small + 1] >= small) {
                        // OK we found a valid length
                        max = small;
                        TopLeftPoint = new int[]{i - small + 1, j - small + 1};
                        break;
                    } else {
                        small--;
                    }
                }
            }
        }
        System.out.println("TopLeftPoint is:"+ Arrays.toString(TopLeftPoint));
        return max;
    }

    static public int getNumber(int[][] matrix, int i, int j) {
        int m = matrix.length;
        int n = matrix[0].length;
        if (i < 0 || j < 0 || i == m || j == n) {
            return 0;
        } else {
            return matrix[i][j];
        }
    }


    static public int[][] getLeftToRight(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] LeftToRight = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    LeftToRight[i][j] = 1 + getNumber(LeftToRight, i, j - 1);
                }
            }
        }
        return LeftToRight;
    }

    static public int[][] getUpToDown(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] UpToDown = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    UpToDown[i][j] = 1 + getNumber(UpToDown, i - 1, j);
                }
            }
        }
        return UpToDown;
    }

    static public void printMatrix(int[][] array) {
        int row = array.length;
        int col = array[0].length;
        System.out.println("=======Matrix=======");
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                System.out.print(array[i][j]);
            }
            System.out.println();
        }
        System.out.println("=======Matrix=======");

    }


}
