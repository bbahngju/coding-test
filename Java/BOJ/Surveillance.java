package BOJ;

import java.io.*;
import java.util.StringTokenizer;

//BOJ 15683
//시뮬, 50분
public class Surveillance {
    public static int[][][] dx = {{{0}, {0}, {1}, {-1}}, {{0, 0}, {1, -1}},
            {{-1, 0}, {1, 0}, {0, 1}, {0, -1}},
            {{0, 0, -1}, {-1, 0, 1}, {0, 1, 0}, {-1, 0, 1}},
            {{-1, 0, 1, 0}}};
    public static int[][][] dy = {{{1}, {-1}, {0}, {0}}, {{1, -1}, {0, 0}},
            {{0, 1}, {0, 1}, {-1, 0}, {-1, 0}},
            {{1, -1, 0}, {0, -1, 0}, {-1, 0, 1}, {0, 1, 0}},
            {{0, -1, 0, 1}}};

    public static int row, col;
    public static int answer = 65;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        int[][] office = new int[row][col];

        for(int i = 0; i < row; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < col; j++) {
                office[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        startSurveillance(0, 0, office);

        bw.write(answer + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    public static void startSurveillance(int x, int y, int[][] office) {
        int j = y;
        for(int i = x; i < row; i++) {
            for(; j < col; j++) {
                if(office[i][j] >= 1 && office[i][j] <= 5) {
                    for(int k = 0; k < dx[office[i][j]-1].length; k++) {
                        int[][] copy = new int[row][col];
                        copyOffice(office, copy);
                        detectSpot(copy, i, j, office[i][j]-1, k);
                        startSurveillance(i, j+1, copy);
                    }
                    return;
                }
            }
            j = 0;
        }
        answer = Math.min(answer, findBlindSpot(office));
    }

    public static void copyOffice(int[][] origin, int[][] copy) {
        for(int i = 0; i < origin.length; i++) {
            System.arraycopy(origin[i], 0, copy[i], 0, origin[0].length);
        }
    }

    public static void detectSpot(int[][] office, int curX, int curY, int num, int idx) {
        int nextX;
        int nextY;
        for(int i = 0; i < dx[num][idx].length; i++) {
            nextX = curX + dx[num][idx][i];
            nextY = curY + dy[num][idx][i];


            while(nextX >= 0 && nextX < row && nextY >= 0 && nextY < col) {
                if(office[nextX][nextY] == 6) break;
                if(office[nextX][nextY] == 0)
                    office[nextX][nextY] = -1;

                nextX += dx[num][idx][i];
                nextY += dy[num][idx][i];
            }

        }
    }
    public static int findBlindSpot(int[][] office) {
        int cnt = 0;
        for(int[] space : office) {
            for(int spot : space) {
                if(spot == 0) cnt++;
            }
        }

        return cnt;
    }
}
