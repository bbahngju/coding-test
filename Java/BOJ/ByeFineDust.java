package BOJ;

import java.io.*;
import java.util.StringTokenizer;

//BOJ 17144
//O((50^2 + 250 + 250) * 1000 ) + 50) = O(3000050)
//시뮬, 80분
public class ByeFineDust {
    public static int[] dx = {0, 0, 1, -1};
    public static int[] dy = {1, -1, 0, 0};
    public static int[] upDx = {-1, 0, 1, 0};
    public static int[] upDy = {0, 1, 0, -1};
    public static int[] downDx = {1, 0, -1, 0};
    public static int[] downDy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int row = Integer.parseInt(st.nextToken());
        int col = Integer.parseInt(st.nextToken());
        int time = Integer.parseInt(st.nextToken());

        int[][] space = new int[row][col];
        Point up = new Point(0, 0), down = new Point(0, 0);
        int value;
        boolean isUp = true;
        for(int i = 0; i < row; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < col; j++) {
                value = Integer.parseInt(st.nextToken());
                space[i][j] = value;

                if(value == -1) {
                    if(isUp) {
                        up = new Point(i, j);
                        isUp = false;
                    }
                    else {
                        down = new Point(i, j);
                    }
                }
            }
        }

        while(time > 0) {
            space = spreadFineDust(space);
            upClean(space, up);
            downClean(space, down);
            time--;
        }
        bw.write(calculateFineDust(space) + "\n");

        bw.flush();
        bw.close();
        br.close();
    }

    public static int[][] spreadFineDust(int[][] space) {
        int r = space.length;
        int c = space[0].length;

        int[][] copySpace = new int[r][c];
        for(int i = 0; i < r; i++) {
            System.arraycopy(space[i], 0, copySpace[i], 0, c);
        }

        int value, pass, cnt;
        int nextX, nextY;
        for(int i = 0; i < r; i++) {
            for(int j = 0; j < c; j++) {
                if(space[i][j] == -1 || space[i][j] == 0) continue;

                value = space[i][j];
                pass = value/5;
                cnt = 0;

                for(int k = 0; k < 4; k++) {
                    nextX = i + dx[k];
                    nextY = j + dy[k];

                    if(nextX < 0 || nextX >= r || nextY < 0 || nextY >= c) continue;
                    if(space[nextX][nextY] == -1) continue;

                    copySpace[nextX][nextY] += pass;
                    cnt++;
                }

                copySpace[i][j] = copySpace[i][j] - (pass * cnt);
            }
        }

        return copySpace;
    }

    public static void upClean(int[][] space, Point up) {
        int tmp;
        int r = space.length;
        int c = space[0].length;
        int x = up.x; int y = up.y;

        int nextX, nextY;
        for(int i = 0; i < 4; i++) {
            nextX = x + upDx[i];
            nextY = y + upDy[i];

            while(nextX >= 0 && nextX <= up.x && nextY >= 0 && nextY < c) {
                tmp = space[nextX][nextY];
                space[nextX][nextY] = 0;

                if(space[x][y] != -1)
                    space[x][y] = tmp;
                x = nextX;
                y = nextY;
                nextX = x + upDx[i];
                nextY = y + upDy[i];

                if(nextX == up.x && nextY == up.y) break;
            }
        }
    }

    public static void downClean(int[][] space, Point down) {
        int tmp;
        int r = space.length;
        int c = space[0].length;
        int x = down.x; int y = down.y;

        int nextX, nextY;
        for(int i = 0; i < 4; i++) {
            nextX = x + downDx[i];
            nextY = y + downDy[i];
            while(nextX >= down.x && nextX < r && nextY >= 0 && nextY < c) {
                tmp = space[nextX][nextY];
                space[nextX][nextY] = 0;

                if(space[x][y] != -1)
                    space[x][y] = tmp;
                x = nextX;
                y = nextY;
                nextX = x + downDx[i];
                nextY = y + downDy[i];

                if(nextX == down.x && nextY == down.y) break;
            }
        }
    }

    public static int calculateFineDust(int[][] space) {
        int answer = 0;

        for(int[] values : space) {
            for(int value : values) {
                if(value == 0 || value == -1) continue;
                answer += value;
            }
        }

        return answer;
    }
    public static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
