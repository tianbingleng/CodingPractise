package PocketGem;

/**
 * Created by tianbingleng on 10/10/2017.
 */
public class MatrixFindMostOne {
    public static void main (String[] args) {
        // left most 1 (the row contain most 1)
        // return that col number
        int[][] matrix1 = new int[][] {
                {0,0,0,0,0,0,0,1},
                {0,0,0,1,1,1,1,1},
                {0,0,0,1,1,1,1,1},
                {0,1,1,1,1,1,1,1},
                {0,0,0,0,1,1,1,1}
        };

        int[][] matrix2 = new int[][] {
                {0,0,0,0,0,0,0,1},
                {0,0,0,1,1,1,1,1},
                {1,1,1,1,1,1,1,1},
                {0,1,1,1,1,1,1,1},
                {0,0,0,0,1,1,1,1}
        };

        int[][] matrix3 = new int[][] {
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0}
        };

        System.out.println(mnWay(matrix1));
        System.out.println(bsWay(matrix1));
        System.out.println("=============");
        System.out.println(mnWay(matrix2));
        System.out.println(bsWay(matrix2));
        System.out.println("=============");
        System.out.println(mnWay(matrix3));
        System.out.println(bsWay(matrix3));

    }
    // O(m + n) solution
    // go form top right, moving down, moving left
    // if it is a 1, moving left (update result if any)
    // if it is a 0, moving down
    // if col < 0, return 0 since we have reach the solution
    static public int mnWay(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return -1;
        }
        int row = 0;
        int col = matrix[0].length - 1;
        // initial is -1, will update if found later
        int result = -1;
        while (col >= 0 && row < matrix.length) {
            if (matrix[row][col] == 1) {
                result = col;
                col--; //if it is less 0, result = 0, correct
            } else {
                row++;
            }
        }

        return result;
    }


    // binary search solution row * log(col)
    static public int bsWay(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return -1;
        }
        int result = matrix[1].length - 1;
        boolean findOne = false;
        for (int row = 0; row < matrix.length; row++) {
            int colResult = findLeftMostOne(matrix[row]);
            if (colResult != -1) {
                findOne = true;
                result = Math.min(result, colResult);
            }
        }
        if (findOne) {
            return result;
        } else {
            return -1;
        }
    }

    static public int findLeftMostOne(int[] array) {
        int left = 0;
        int right = array.length - 1;
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (array[mid] == 0) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        if (array[left] == 1) {
            return left;
        } else if (array[right] == 1){
            return right;
        } else {
            return -1;
        }
    }


}
