package BOJ;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//BOJ 15949
public class Piet {
    public static class Point {
        int x;
        int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static Queue<Point> saveCodel = new LinkedList<>();
    public static char[][] piet;
    public static int[] dpX = {0, 1, 0, -1};
    public static int[] dpY = {1, 0, -1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int row = Integer.parseInt(st.nextToken());
        int col = Integer.parseInt(st.nextToken());

        piet = new char[row][col];
        for(int i = 0; i < row; i++) {
            piet[i] = br.readLine().toCharArray();
        }

        int x = 0, y = 0, turn;
        int[] result; int[] next;
        int dp = 0, cc = -1;
        while(true) {
            saveCodel.clear();

            bw.write(piet[x][y] + "");
            result = bfs(x, y, row, col);
            int[][] ccPoint = {{-1, 101}, {101, -1}, {101, -1}, {-1, 101}};

            while(!saveCodel.isEmpty()) {
                Point cur = saveCodel.poll();
                if(result[0] == cur.y) {
                    ccPoint[0][0] = Math.max(ccPoint[0][0], cur.x);
                    ccPoint[0][1] = Math.min(ccPoint[0][1], cur.x);
                }
                if(result[1] == cur.x) {
                    ccPoint[1][0] = Math.min(ccPoint[1][0], cur.y);
                    ccPoint[1][1] = Math.max(ccPoint[1][1], cur.y);
                }
                if(result[2] == cur.y) {
                    ccPoint[2][0] = Math.min(ccPoint[2][0], cur.x);
                    ccPoint[2][1] = Math.max(ccPoint[2][1], cur.x);
                }
                if(result[3] == cur.x) {
                    ccPoint[3][0] = Math.max(ccPoint[3][0], cur.y);
                    ccPoint[3][1] = Math.min(ccPoint[3][1], cur.y);
                }
            }

            turn = 0;
            while(turn <= 6) {
                next = getNextPoint(dp, result, cc, ccPoint);

                if(next[0] < 0 || next[0] >= row || next[1] < 0 || next[1] >= col
                        || piet[next[0]][next[1]] == 'X') {
                    if(turn % 2 == 1)
                        dp = (dp + 1) % 4;
                    else
                        cc *= -1;
                    turn++;
                }
                else {
                    x = next[0];
                    y = next[1];
                    break;
                }
            }

            if(turn == 7) break;
        }

        bw.flush();
        bw.close();
        br.close();
    }

    public static int[] bfs(int x, int y, int r, int c) {
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};

        boolean[][] v = new boolean[r][c];
        int maxY = -1, maxX = -1, minY = 101, minX = 101;
        int nextX, nextY;
        char color = piet[x][y];
        Queue<Point> q = new LinkedList<>();

        q.add(new Point(x, y));
        saveCodel.add(new Point(x, y));
        v[x][y] = true;

        while(!q.isEmpty()) {
            Point cur = q.poll();
            maxX = Math.max(maxX, cur.x);
            maxY = Math.max(maxY, cur.y);
            minX = Math.min(minX, cur.x);
            minY = Math.min(minY, cur.y);

            for(int i = 0; i < 4; i++) {
                nextX = cur.x + dx[i];
                nextY = cur.y + dy[i];

                if(nextX < 0 || nextX >= r || nextY < 0 || nextY >= c
                        || color != piet[nextX][nextY] || v[nextX][nextY]) continue;

                q.add(new Point(nextX, nextY));
                saveCodel.add(new Point(nextX, nextY));
                v[nextX][nextY] = true;
            }
        }

        return new int[]{maxY, maxX, minY, minX};
    }

    public static int[] getNextPoint(int dp, int[] result, int cc, int[][] ccPoint) {
        int x, y;
        if(dp == 0 || dp == 2) {
            x = (cc == 1) ? ccPoint[dp][0] : ccPoint[dp][1];
            y = result[dp];
        }
        else {
            x = result[dp];
            y = (cc == 1) ? ccPoint[dp][0] : ccPoint[dp][1];
        }

        return new int[]{x + dpX[dp], y + dpY[dp]};
    }
}
