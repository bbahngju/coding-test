package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//BOJ 3197
public class SwanLake {
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int R, C;
    static boolean isSearch = false;
    static char[][] ocean;
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
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        ocean = new char[R][C];
        boolean[][] visited = new boolean[R][C];
        Queue<Point> water = new LinkedList<>();
        Queue<Point> search = new LinkedList<>();
        Point firstSwan = new Point(0,0), secondSwan = new Point(0,0);
        int meetCnt = 0;
        boolean isFirst = false;

        for(int i=0; i<R; i++) {
            ocean[i] = br.readLine().toCharArray();
        }

        for(int i=0; i<R; i++) {
            for(int j=0; j<C; j++) {
                if(ocean[i][j] == '.') water.add(new Point(i,j));
                else if(ocean[i][j] == 'L') {
                    if(!isFirst) {
                        firstSwan.x = i;
                        firstSwan.y = j;
                        isFirst = true;
                    }
                    else {
                        secondSwan.x = i;
                        secondSwan.y = j;
                    }

                    water.add(new Point(i,j));
                }
            }
        }

        search.add(new Point(firstSwan.x, firstSwan.y));
        visited[firstSwan.x][firstSwan.y] = true;
        while(true) {
            search = Search(search, secondSwan, visited);
            if(isSearch) break;
            water = mealt(water);
            meetCnt++;
        }

        System.out.println(meetCnt);
    }

    public static Queue<Point> Search(Queue<Point> f, Point s, boolean[][] v) {
        int nextX, nextY;
        Queue<Point> nextSearch = new LinkedList<>();
        while(!f.isEmpty()) {
            Point cur = f.poll();

            if(cur.x == s.x && cur.y == s.y) {
                isSearch = true;
                break;
            }

            for(int i=0; i<4; i++) {
                nextX = cur.x + dx[i];
                nextY = cur.y + dy[i];
                if(nextX >= 0 && nextX < R && nextY >= 0 && nextY < C && !v[nextX][nextY]) {
                    if(ocean[nextX][nextY] == 'X') {
                        nextSearch.add(new Point(nextX, nextY));
                        v[nextX][nextY] = true;
                    }
                    else {
                        f.add(new Point(nextX, nextY));
                        v[nextX][nextY] = true;
                    }
                }
            }
        }

        return nextSearch;
    }

    public static Queue<Point> mealt(Queue<Point> w) {
        Queue<Point> nextWater = new LinkedList<>();
        int nextX, nextY;
        while(!w.isEmpty()) {
            Point cur = w.poll();
            for(int i=0; i<4; i++) {
                nextX = cur.x + dx[i];
                nextY = cur.y + dy[i];
                if(nextX >= 0 && nextX < R && nextY >= 0 && nextY < C) {
                    if(ocean[nextX][nextY] == 'X') {
                        nextWater.add(new Point(nextX, nextY));
                        ocean[nextX][nextY] = '.';
                    }
                }
            }
        }
        return nextWater;
    }
}
