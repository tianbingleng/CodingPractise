/**
 * Created by tianbingleng on 21/6/2017.
 */
public class LongestCrossOf1s {
    static public void main(String[] args) {
        /*
        Given a matrix that contains only 1s and 0s, find the largest cross which contains only 1s, with the same arm lengths and the four arms joining at the central point.

        Return the arm length of the largest cross.

        Assumptions
        The given matrix is not null, has size of N * M, N >= 0 and M >= 0.

        Examples
        { {0, 0, 0, 0},
          {1, 1, 1, 1},
          {0, 1, 1, 1},
          {1, 0, 1, 1} }
          the largest cross of 1s has arm length 2.
        */

        int[][] array = new int[][]{
                    {0, 0, 0, 0},
                    {1, 1, 1, 1},
                    {0, 1, 1, 1},
                    {1, 0, 1, 1}};

        printMatrix(array);
        System.out.println(largest(array));

    }
    static public int largest(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int[][] fromLeftMatrix = getLeftMatrix(matrix);
        int[][] fromRightMatrix = getRightMatrix(matrix);
        int[][] fromUpMatrix = getUpMatrix(matrix);
        int[][] fromDownMatrix = getDownMatrix(matrix);
//        printMatrix(fromDownMatrix);
        return merge(fromLeftMatrix, fromRightMatrix, fromUpMatrix, fromDownMatrix);
    }

    static public int merge(int[][] matrix1, int[][] matrix2, int[][] matrix3, int[][] matrix4) {
        int m = matrix1.length;
        int n =matrix1[0].length;
        int[][] overallMatrix = new int[m][n];
        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                overallMatrix[i][j] =
                        Math.min(Math.min(Math.min(
                                    matrix1[i][j],
                                    matrix2[i][j]),
                                    matrix3[i][j]),
                                    matrix4[i][j]);
                max = Math.max(overallMatrix[i][j], max);
            }
        }
        printMatrix(overallMatrix);
        return max;
    }

    static public int[][] getLeftMatrix(int[][] matrix) {
        int m = matrix.length;
        int n =matrix[0].length;
        int[][] fromLeftMatrix = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (j == 0) {
                    fromLeftMatrix[i][j] = matrix[i][j];
                } else {
                    if (matrix[i][j] == 1) {
                        fromLeftMatrix[i][j] = fromLeftMatrix[i][j-1] + 1;
                    } else {
                        fromLeftMatrix[i][j] = 0;
                    }
                }
            }
        }
        return fromLeftMatrix;
    }

    static public int[][] getRightMatrix(int[][] matrix) {
        int m = matrix.length;
        int n =matrix[0].length;
        int[][] fromRightMatrix = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = n - 1; j >= 0; j--) {
                if (j == n - 1) {
                    fromRightMatrix[i][j] = matrix[i][j];
                } else {
                    if (matrix[i][j] == 1) {
                        fromRightMatrix[i][j] = fromRightMatrix[i][j+1] + 1;
                    } else {
                        fromRightMatrix[i][j] = 0;
                    }
                }
            }
        }
        return fromRightMatrix;
    }

    static public int[][] getUpMatrix(int[][] matrix) {
        int m = matrix.length;
        int n =matrix[0].length;
        int[][] fromUpMatrix = new int[m][n];
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m; i++) {
                if (i == 0) {
                    fromUpMatrix[i][j] = matrix[i][j];
                } else {
                    if (matrix[i][j] == 1) {
                        fromUpMatrix[i][j] = fromUpMatrix[i-1][j] + 1;
                    } else {
                        fromUpMatrix[i][j] = 0;
                    }
                }
            }
        }
        return fromUpMatrix;
    }

    static public int[][] getDownMatrix(int[][] matrix) {
        int m = matrix.length;
        int n =matrix[0].length;
        int[][] fromDownMatrix = new int[m][n];
        for (int j = n - 1; j >= 0; j--) {
            for (int i = m - 1; i >= 0; i--) {
                if (i == m - 1) {
                    fromDownMatrix[i][j] = matrix[i][j];
                } else {
                    if (matrix[i][j] == 1) {
                        fromDownMatrix[i][j] = fromDownMatrix[i+1][j] + 1;
                    } else {
                        fromDownMatrix[i][j] = 0;
                    }
                }
            }
        }
        return fromDownMatrix;
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
