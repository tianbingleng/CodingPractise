/**
 * Created by tianbingleng on 21/6/2017.
 */
public class LargestSquareOf1s {
    static public void main(String[] args) {
        /*
        Determine the largest square of 1s in a binary matrix (a binary matrix only contains 0 and 1), return the length of the largest square.

        Assumptions
        The given matrix is not null and guaranteed to be of size N * N, N >= 0

        Examples
        { {0, 0, 0, 0},
          {1, 1, 1, 1},
          {0, 1, 1, 1},
          {1, 0, 1, 1}}
          the largest square of 1s has length of 2
        */

//        int[][] array = new int[][]{
//                {0, 0, 0, 0},
//                {1, 1, 1, 1},
//                {0, 1, 1, 1},
//                {1, 0, 1, 1}};
        int[][] array = new int[][]{
                {0, 0, 0, 0, 0},
                {1, 1, 1, 1, 0},
                {0, 1, 1, 1, 0},
                {1, 1, 1, 1, 0},
                {1, 0, 1, 1, 0}};

        printMatrix(array);
        System.out.println(largest(array));

    }
    static public int largest(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int maxNumber = Integer.MIN_VALUE;
        int[][] lagestMatrix = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    lagestMatrix[i][j] = matrix[i][j];
                } else if (matrix[i][j] == 0) {
                    lagestMatrix[i][j] = 0;
                } else {
                    lagestMatrix[i][j] = 1 + Math.min(Math.min(
                                                        lagestMatrix[i - 1][j - 1],
                                                        lagestMatrix[i][j - 1]),
                                                        lagestMatrix[i - 1][j]
                                                    );
                }
                maxNumber = Math.max(maxNumber, lagestMatrix[i][j]);
            }
        }

        printMatrix(lagestMatrix);
        return maxNumber;
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
