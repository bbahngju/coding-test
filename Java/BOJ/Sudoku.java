package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//BOJ 2239
//
public class Sudoku {
    static int size = 9;
    static int[][] sudoku = new int[size][size];

    public static void main(String[] arg) throws IOException {
        boolean[][] row = new boolean[size][size+1];
        boolean[][] col = new boolean[size][size+1];
        boolean[][] box = new boolean[size][size+1];

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input;
        for(int i = 0; i < size; i++) {
            input = br.readLine().split("");
            for(int j = 0; j < size; j++) {
                sudoku[i][j] = Integer.parseInt(input[j]);

                if(sudoku[i][j] != 0) {
                    row[i][sudoku[i][j]] = true; //해당 행에 이 수가 있다.
                    col[j][sudoku[i][j]] = true; //해당 열에 이 수가 있다.
                    box[(i/3)*3+(j/3)][sudoku[i][j]] = true; //해당 박스칸에 이 수가 있다.

                }
            }
        }

        fillSudoku(0, 0, row, col, box);
    }
    public static void fillSudoku(int x, int y, boolean[][] row, boolean[][] col, boolean[][] box) {
        if(y == 9) {
            fillSudoku(x+1, 0, row, col, box);
            return;
        }

        if(x == 9) {
            for(int[] r : sudoku) {
                for(int c : r) {
                    System.out.printf("%d", c);
                }
                System.out.println();
            }

            System.exit(0);
        }

        if(sudoku[x][y] == 0) {
            int k;
            for(k = 1; k <= 9; k++) {
                if(checkNum(x, y, k, row, col, box)) {
                    sudoku[x][y] = k;
                    row[x][k] = true;
                    col[y][k] = true;
                    box[(x/3)*3 + (y/3)][k] = true;

                    fillSudoku(x, y+1, row, col, box);

                    sudoku[x][y] = 0;
                    row[x][k] = false;
                    col[y][k] = false;
                    box[(x/3)*3 + (y/3)][k] = false;
                }
            }
            return;
        }

        fillSudoku(x, y+1, row, col, box);
    }

    public static boolean checkNum(int x, int y, int number, boolean[][] row, boolean[][] col, boolean[][] box) {
        return (!row[x][number] && !col[y][number] && !box[(x/3)*3 + (y/3)][number]);
    }
}
