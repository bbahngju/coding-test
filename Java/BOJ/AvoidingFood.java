package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//BOJ 1743
//O(NM)
public class AvoidingFood {
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1 ,0, 0};

    public static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int foodNum = Integer.parseInt(st.nextToken());

        boolean[][] hallway = new boolean[r+1][c+1];
        boolean[][] visited = new boolean[r+1][c+1];
        int x, y, maximum = 0;
        for(int i = 0; i < foodNum; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());

            hallway[x][y] = true;
        }

        for(int i = 1; i < r + 1; i++) {
            for(int j = 1; j < c + 1; j++) {
                if(hallway[i][j] && !visited[i][j]) {
                    visited[i][j] = true;
                    maximum = Math.max(maximum, searchSize(hallway, visited, i, j));
                }
            }
        }

        System.out.println(maximum);
    }

    public static int searchSize(boolean[][] h, boolean[][] v, int x, int y) {
        Queue<Point> q = new LinkedList<>();
        int size = 0;

        q.add(new Point(x, y));

        while(!q.isEmpty()) {
            Point cur = q.poll();
            size++;

            int nextX, nextY;
            for(int i = 0; i < 4; i++) {
                nextX = cur.x + dx[i];
                nextY = cur.y + dy[i];

                if(isRange(h, nextX, nextY) && h[nextX][nextY] && !v[nextX][nextY]) {
                    v[nextX][nextY] = true;
                    q.add(new Point(nextX, nextY));
                }
            }
        }

        return size;
    }

    public static boolean isRange(boolean[][] h, int x, int y) {
        return (x >= 1 && x < h.length && y >= 1 && y < h[0].length);
    }
}
