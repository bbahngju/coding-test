package BOJ;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//BOJ 16234
//O(N^2 * 2000)
//시뮬, 53분
public class PopulationMovement {
    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};
    public static int N;
    public static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        int minimum = Integer.parseInt(st.nextToken());
        int maximum = Integer.parseInt(st.nextToken());

        int[][] country = new int[N][N];
        visited = new boolean[N][N];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < N; j++) {
                country[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean isRepeat = true;
        int answer = -1;

        while(isRepeat) {
            answer++;
            isRepeat = false;

            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(!visited[i][j]) {
                        if(isBfs(i, j, country, minimum, maximum)) {
                            isRepeat = true;
                            bfs(i, j, country, minimum, maximum);
                        }
                    }
                }
            }

            for(boolean[] visit : visited) {
                Arrays.fill(visit, false);
            }
        }

        bw.write(answer + "\n");
        //30~44분
        //22~01
        bw.flush();
        bw.close();
        br.close();
    }

    public static Boolean isBfs(int x, int y, int[][] country, int mini, int maxi) {
        int nextX, nextY;
        int dif;
        for(int i = 0; i < 4; i++) {
            nextX = x + dx[i];
            nextY = y + dy[i];
            if(nextX < 0 || nextX >= N || nextY < 0 || nextY >= N) continue;
            if(visited[nextX][nextY]) continue;

            dif = Math.abs(country[x][y] - country[nextX][nextY]);
            if(dif >= mini && dif <= maxi) return true;
        }

        return false;
    }

    public static void bfs(int x, int y, int[][] country, int mini, int maxi) {
        Queue<Point> q = new LinkedList<>();
        Queue<Point> save = new LinkedList<>();
        q.add(new Point(x, y));
        save.add(new Point(x, y));
        visited[x][y] = true;

        int nextX, nextY;
        int dif;
        int total = country[x][y];
        while(!q.isEmpty()) {
            Point cur = q.poll();
            for(int i = 0; i < 4; i++) {
                nextX = cur.x + dx[i];
                nextY = cur.y + dy[i];
                if(nextX < 0 || nextX >= N || nextY < 0 || nextY >= N) continue;
                if(visited[nextX][nextY]) continue;

                dif = Math.abs(country[cur.x][cur.y] - country[nextX][nextY]);
                if(dif >= mini && dif <= maxi) {
                    total += country[nextX][nextY];

                    q.add(new Point(nextX, nextY));
                    save.add(new Point(nextX, nextY));
                    visited[nextX][nextY] = true;
                }
            }
        }

        populationModify(country, total, save);
    }

    public static void populationModify(int[][] country, int total, Queue<Point> save) {
        int value = total / save.size();
        while(!save.isEmpty()) {
            Point cur = save.poll();
            country[cur.x][cur.y] = value;
        }
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
