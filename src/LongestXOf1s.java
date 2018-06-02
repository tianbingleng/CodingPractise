/**
 * Created by tianbingleng on 21/6/2017.
 */
public class LongestXOf1s {
    static public void main(String[] args) {
        /*
        Given a matrix that contains only 1s and 0s, find the largest X shape which contains only 1s, with the same arm lengths and the four arms joining at the central point.
        Return the arm length of the largest X shape.

        Assumptions
        The given matrix is not null, has size of N * M, N >= 0 and M >= 0.

        Examples

        { {0, 0, 0, 0},
          {1, 1, 1, 1},
          {0, 1, 1, 1},
          {1, 0, 1, 1} }
        the largest X of 1s has arm length 2.

        本题是LongestCrossOf1s变种，这种方法更简洁~
        */

        int[][] array = new int[][]{
                    {0, 0, 0, 0},
                    {1, 1, 1, 1},
                    {0, 1, 1, 1},
                    {1, 0, 1, 1}};

        printMatrix(array);
        System.out.println(largest(array));

    }
    /*
        int[][] centerToLeftRightUp = getCenterToLeftRightUp(matrix);
        CenterToLeftUp          CenterToRightUp
                    X            X
                      ↖        ↗
                        ↖    ↗
                        Center
                        ↙    ↘
                      ↙        ↘
                    X            X
        CenterToLeftDown         CenterToRightDown
        int[][] centerToLeftRightDown = getCenterToLeftRightDown(matrix);


     */
    static public int largest(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int[][] centerToLeftRightDown = getCenterToLeftRightDown(matrix);
        int[][] centerToLeftRightUp = getCenterToLeftRightUp(matrix);

        return merge(centerToLeftRightDown, centerToLeftRightUp);
    }

    static public int merge(int[][] matrix1, int[][] matrix2) {
        int m = matrix1.length;
        int n = matrix1[0].length;
        int[][] overallMatrix = new int[m][n];
        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                overallMatrix[i][j] =
                        Math.min(
                                matrix1[i][j],
                                matrix2[i][j]);
                max = Math.max(overallMatrix[i][j], max);
            }
        }
//        printMatrix(matrix1);
//        printMatrix(matrix2);
        printMatrix(overallMatrix);
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

    static public int[][] getCenterToLeftRightDown(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] centerToLeftDownMatrix = new int[m][n];
        int[][] centerToRightDownMatrix = new int[m][n];
        int[][] centerToLeftRightDown = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    centerToLeftDownMatrix[i][j] = 1 + getNumber(centerToLeftDownMatrix, i - 1, j + 1);
                    centerToRightDownMatrix[i][j] = 1 + getNumber(centerToRightDownMatrix, i - 1, j - 1);
                    centerToLeftRightDown[i][j] = Math.min(centerToLeftDownMatrix[i][j], centerToRightDownMatrix[i][j]);
                }

            }
        }
        return centerToLeftRightDown;
    }

    static public int[][] getCenterToLeftRightUp(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] centerToLeftUpMatrix = new int[m][n];
        int[][] centerToRightUpMatrix = new int[m][n];
        int[][] centerToLeftRightUp = new int[m][n];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (matrix[i][j] == 1) {
                    centerToLeftUpMatrix[i][j] = 1 + getNumber(centerToLeftUpMatrix, i + 1, j + 1);
                    centerToRightUpMatrix[i][j] = 1 + getNumber(centerToRightUpMatrix, i + 1, j - 1);
                    centerToLeftRightUp[i][j] = Math.min(centerToLeftUpMatrix[i][j], centerToRightUpMatrix[i][j]);
                }
            }
        }

        return centerToLeftRightUp;
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
