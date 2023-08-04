import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);

        final int rows = scanner.nextInt();
        final int cols = scanner.nextInt();

        int[][] mat = new int[rows][cols];

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                mat[i][j] = scanner.nextInt();
            }
        }

        int[][] newMat = new int[cols][rows];

        for (int j = 0; j < cols; j++) {
            for (int i = rows - 1; i >= 0; i--) {
                // System.out.printf("%d %d %d %d\n", i, j, rows - i - 1, mat[i][j]);
                newMat[j][rows - i - 1] = mat[i][j];
            }
        }

        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                System.out.printf("%d ", newMat[i][j]);
            }
            System.out.println();
        }

    }
}