import java.util.Arrays;

/**
 * Created by tianbingleng on 21/6/2017.
 */
public class LargestSubMatrixSum {
    static public void main(String[] args) {
        /*
        Given a matrix that contains integers, find the submatrix with the largest sum.
        Return the sum of the submatrix.

        Assumptions
        The given matrix is not null and has size of M * N, where M >= 1 and N >= 1

        Examples
        { {1, -2, -1, 4},
          {1, -1,  1, 1},
          {0, -1, -1, 1},
          {0,  0,  1, 1} }

        the largest submatrix sum is (-1) + 4 + 1 + 1 + (-1) + 1 + 1 + 1 = 7.

        要去看讲义里的另一种解法！！
        */

        int[][] array = new int[][] {
                    {1, -2, -1, 4},
                    {1, -1,  1, 1},
                    {0, -1, -1, 1},
                    {0,  0,  1, 1}};
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

        int[][] preSumMatrix = getPreSumMatrix(matrix);

        return getMax(preSumMatrix);
    }

    static public int[][] getPreSumMatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] preSumPerRowMatrix = new int[m][n];
        int[][] preSumMatrix = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (j == 0) {
                    preSumPerRowMatrix[i][j] = matrix[i][j];
                } else {
                    preSumPerRowMatrix[i][j] = matrix[i][j] + preSumPerRowMatrix[i][j - 1];
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                    if (i == 0) {
                        preSumMatrix[i][j] =  preSumPerRowMatrix[i][j];
                    } else {
                        preSumMatrix[i][j] = preSumMatrix[i - 1][j] + preSumPerRowMatrix[i][j];
                    }
            }
        }
        printMatrix(preSumMatrix);
        return preSumMatrix;
    }

    /*

        x   x   x   x   x   x    x   x

        x   x   x   x   x   x    x   x
               (i,j)
        x   x   P1   x   x   X1   x   x
                           (k,t)
        x   x   X2   x   x   P2   x   x

        x   x   x   x   x   x    x   x

        Sum (i,j ~ k,t) = PreSum(k, t) - PreSum(i-1, t) - PreSum(k, j-1) + PreSum(i-1, j-1)
                        = PreSum(P2) - PreSum(X1) - PreSum(X2) + PreSum(P1)

     */

    static public int getMax(int[][] preSumMatrix) {
        int m = preSumMatrix.length;
        int n = preSumMatrix[0].length;
        int max = preSumMatrix[0][0];
        int[] TopLeftPoint = {0,0};
        int[] BtmRightPoint = {0,0};
        // i,j 左上角
        // k,t 右下角
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = i; k < m; k++) {
                    for (int t = j; t < n; t++) {
                        int currSum = getNumber(preSumMatrix, k, t) - getNumber(preSumMatrix, i - 1, t) - getNumber(preSumMatrix, k, j - 1) + getNumber(preSumMatrix, i - 1, j - 1);
                        if (currSum > max) {
                            max = currSum;
                            TopLeftPoint = new int[]{i, j};
                            BtmRightPoint = new int[]{k, t};
                        }
                    }
                }

            }
        }
        System.out.println("TopLeftPoint is:"+ Arrays.toString(TopLeftPoint));
        System.out.println("BtmRightPoint is:"+ Arrays.toString(BtmRightPoint));
        return max;
    }



    static public int getNumber(int[][] matrix, int i, int j) {
        if (i < 0 || j < 0) {
            return 0;
        } else {
            return matrix[i][j];
        }
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
