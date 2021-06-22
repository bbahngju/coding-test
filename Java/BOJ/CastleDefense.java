package BOJ;

import java.io.*;
import java.util.StringTokenizer;

//BOJ 17135
//시뮬, 80분
//O(307243125)
public class CastleDefense {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        int row = Integer.parseInt(st.nextToken());
        int col = Integer.parseInt(st.nextToken());
        int disLimit = Integer.parseInt(st.nextToken());

        int[][] grid = new int[row][col];
        int enemy = 0;

        for(int i = 0; i < row; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < col; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
                if(grid[i][j] == 1) enemy++;
            }
        }

        bw.write(placeArcher(grid, row, col, disLimit, enemy) + "\n");

        bw.flush();
        bw.close();
        br.close();
    }

    public static int placeArcher(int[][] grid, int row, int col, int disLimit, int enemy) {
        int answer = 0;
        int result;
        int[][] copy = new int[row][col];
        int[][] archer = new int[3][2];
        archer[0][0] = row; archer[1][0] = row; archer[2][0] = row;
        for(int i = 0; i < col; i++) {
            for(int j = i + 1; j < col; j++) {
                for(int k = j + 1; k < col; k++) {
                    archer[0][1] = i; archer[1][1] = j; archer[2][1] = k;

                    for(int c = 0; c < grid.length; c++) {
                        System.arraycopy(grid[c], 0, copy[c], 0, grid[0].length);
                    }

                    result = playDefense(copy, archer, row, col, disLimit, enemy);
                    answer = Math.max(answer, result);
                }
            }
        }

        return answer;
    }

    public static int playDefense(int[][] grid, int[][] archer, int row, int col, int disLimit, int enemy) {
        int moveX = 1; int moveY = 0;
        int kill = 0;
        int[][] shotEnemy = new int[3][2];

        while(enemy != 0) {
            int x, y, mini, dis;
            for(int i = 0; i < archer.length; i++) {
                x = -1; y = -1; mini = 987654321;
                for(int j = 0; j < row; j++) {
                    for(int k = 0; k < col; k++) {
                        if (grid[j][k] != 1) continue;

                        dis = Math.abs(archer[i][0] - j) + Math.abs(archer[i][1] - k);
                        if (dis > disLimit || dis > mini) continue;
                        if (dis == mini && y < k) continue;

                        mini = dis;
                        x = j; y = k;
                    }
                }
                shotEnemy[i][0] = x; shotEnemy[i][1] = y;
            }

            int killX, killY;
            for(int i = 0; i < archer.length; i++) {
                killX = shotEnemy[i][0]; killY = shotEnemy[i][1];
                if(killX == -1) continue;
                if(grid[killX][killY] != 1) continue;
                grid[killX][killY] = 0;
                kill++;
                enemy--;
            }

            int nextX, nextY;
            for(int i = row - 1; i >= 0; i--) {
                for(int j = 0; j < col; j++) {
                    if(grid[i][j] != 1) continue;

                    nextX = i + moveX;
                    nextY = j + moveY;

                    grid[i][j] = 0;
                    if(nextX == row) {
                        enemy--;
                        continue;
                    }

                    grid[nextX][nextY] = 1;
                }
            }
        }

        return kill;
    }
}
