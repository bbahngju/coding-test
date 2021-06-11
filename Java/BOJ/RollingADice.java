package BOJ;

import java.io.*;
import java.util.StringTokenizer;

//BOJ 14499
//O(1000)
//시뮬 64분, 주사위
public class RollingADice {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int[] dice = {0, 0, 0, 0, 0, 0}; //윗면, 동, 서, 남, 북, 밑면
        int[] dx = {0, 0, 0, -1, 1};
        int[] dy = {0, 1, -1, 0, 0};
        int row = Integer.parseInt(st.nextToken());
        int col = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int opNum = Integer.parseInt(st.nextToken());

        int[][] map = new int[row][col];
        int value;
        for(int i = 0; i < row; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < col; j++) {
                value = Integer.parseInt(st.nextToken());
                map[i][j] = value;
            }
        }

        int op;
        int nextX, nextY;
        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < opNum; i++) {
            op = Integer.parseInt(st.nextToken());

            nextX = x + dx[op];
            nextY = y + dy[op];

            if(nextX < 0 || nextX >= row || nextY < 0 || nextY >= col) continue;

            x = nextX; y = nextY;

            moveDice(dice, op);
            bw.write(dice[0] + "\n");

            if(map[x][y] == 0) {
                map[x][y] = dice[5];
            }
            else {
                dice[5] = map[x][y];
                map[x][y] = 0;
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

    public static void moveDice(int[] dice, int op) {
        int up, down;

        switch (op) {
            case 1:
                up = dice[2];
                down = dice[1];

                dice[1] = dice[0]; dice[2] = dice[5];
                dice[0] = up; dice[5] = down;
                break;

            case 2:
                up = dice[1];
                down = dice[2];

                dice[1] = dice[5]; dice[2] = dice[0];
                dice[0] = up; dice[5] = down;
                break;

            case 3:
                up = dice[3];
                down = dice[4];

                dice[3] = dice[5]; dice[4] = dice[0];
                dice[0] = up; dice[5] = down;
                break;

            case 4:
                up = dice[4];
                down = dice[3];

                dice[3] = dice[0]; dice[4] = dice[5];
                dice[0] = up; dice[5] = down;
                break;
        }
    }
}
