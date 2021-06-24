package BOJ;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//BOJ 2638
//시뮬
public class Cheese2 {
    public static int[][] map, visited;
    public static int[] dx = {0, 0, 1, -1};
    public static int[] dy = {1, -1, 0, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int row = Integer.parseInt(st.nextToken());
        int col = Integer.parseInt(st.nextToken());

        map = new int[row][col];
        visited = new int[row][col];
        int cnt = 0;
        for(int i = 0; i < row; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < col; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) cnt++;
            }
        }

        bw.write(MeltingCheese(0, 0, cnt) + "\n");

        bw.flush();
        bw.close();
        br.close();
    }

    public static int MeltingCheese(int x, int y, int cheese) {
        int day = 0;
        int row = map.length;
        int col = map[0].length;

        Queue<Point> q = new LinkedList<>();
        Queue<Point> melting = new LinkedList<>();
        q.add(new Point(x, y));
        visited[x][y] = 1;

        int nextX, nextY;
        while(cheese > 0) {
            while(!q.isEmpty()) {
                Point cur = q.poll();

                for(int i = 0; i < 4; i++) {
                    nextX = cur.x + dx[i];
                    nextY = cur.y + dy[i];

                    if(nextX < 0 || nextX >= row || nextY < 0 || nextY >= col) continue;

                    if(map[nextX][nextY] == 0 && visited[nextX][nextY] == 0) {
                        q.add(new Point(nextX, nextY));
                        visited[nextX][nextY] = 1;
                    }
                    else if(map[nextX][nextY] == 1) {
                        visited[nextX][nextY] += 1;
                        if(visited[nextX][nextY] == 2) {
                            melting.add(new Point(nextX, nextY));
                        }
                    }
                }
            }

            cheese -= melting.size();
            while(!melting.isEmpty()) {
                Point cur = melting.poll();
                map[cur.x][cur.y] = 0;
                q.add(cur);
            }
            day++;
        }

        return day;
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
