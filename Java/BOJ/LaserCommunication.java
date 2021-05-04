package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//BOJ 6087
public class LaserCommunication {
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static int[] avoid = {1, 0, 3, 2};

    public static class Points {
        int curX;
        int curY;
        int direct;
        int glass;

        public Points(int cx, int cy, int d, int g) {
            this.curX = cx;
            this.curY = cy;
            this.direct = d;
            this.glass = g;
        }
    }

    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int c = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        PriorityQueue<Points> points = new PriorityQueue<>((o1, o2) -> o1.glass - o2.glass);

        char[][] map = new char[r][c];
        boolean[][][] visited = new boolean[r][c][4];

        for(int i=0; i<r; i++) {
            map[i] = br.readLine().toCharArray();
        }

        boolean isFound = false;
        int startx = 0, starty = 0;
        for(int i=0; i<r; i++) {
            for(int j=0; j<c; j++) {
                if(map[i][j] == 'C') {
                    for(int k=0; k<4; k++) {
                        visited[i][j][k] = true;
                    }
                    map[i][j] = '*';
                    startx = i;
                    starty = j;
                    isFound = true;
                    break;
                }
            }
            if(isFound) break;
        }
        bfs(map, visited, points, startx, starty, r, c);
    }

    public static void bfs(char[][] map, boolean[][][] visited, PriorityQueue<Points> points, int sx, int sy, int r, int c) {
        int nextX, nextY;

        for(int i=0; i<4; i++) {
            nextX = sx + dx[i];
            nextY = sy + dy[i];

            if(!isRange(nextX, nextY, r, c) || map[nextX][nextY] == '*') continue;
            points.add(new Points(nextX, nextY, i, 0));
        }

        while(!points.isEmpty()) {
            Points p = points.poll();

            if(map[p.curX][p.curY] == 'C') {
                System.out.println(p.glass);
                break;
            }
            if(visited[p.curX][p.curY][p.direct]) continue;
            visited[p.curX][p.curY][p.direct] = true;

            for(int i=0; i<4; i++) {
                nextX = p.curX + dx[i];
                nextY = p.curY + dy[i];

                if(!isRange(nextX, nextY, r, c) || visited[nextX][nextY][i] || map[nextX][nextY] == '*') continue;

                if(p.direct == i) points.add(new Points(nextX, nextY, i, p.glass));
                else if(i == avoid[p.direct]) continue;
                else points.add(new Points(nextX, nextY, i, p.glass+1));
            }
        }
    }

    public static boolean isRange(int x, int y, int r, int c) {
        if(x >= 0 && x < r && y >= 0 && y < c) return true;
        return false;
    }
}
