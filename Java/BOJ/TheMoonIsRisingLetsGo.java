package BOJ;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//BOJ 1194
//풀이를 생각하면서 현재 열쇠 정보를 큐에 집어넣고 싶다는 생각 => 비트마스크 활용
public class TheMoonIsRisingLetsGo {
    public static class Point {
        int x;
        int y;
        int cnt;
        int key;

        public Point(int x, int y, int cnt, int key) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.key = key;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int row = Integer.parseInt(st.nextToken());
        int col = Integer.parseInt(st.nextToken());

        boolean[][][] visited = new boolean[row][col][64]; //a~f 111111
        char[][] maze = new char[row][col];

        for(int i = 0; i < row; i++) {
            maze[i] = br.readLine().toCharArray();
        }

        int curX = 0, curY = 0;
        boolean isFound = false;
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(maze[i][j] == '0') {
                    curX = i;
                    curY = j;
                    isFound = true;
                    break;
                }
            }

            if(isFound) break;
        }

        bfs(curX, curY, row, col, maze, visited);
    }

    public static void bfs(int x, int y, int row, int col, char[][] maze, boolean[][][] visited) {
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};

        Queue<Point> q = new LinkedList<>();
        q.add(new Point(x, y, 0, 0));
        visited[x][y][0] = true;

        while(!q.isEmpty()) {
            Point cur = q.poll();

            if(maze[cur.x][cur.y] == '1') {
                System.out.println(cur.cnt);
                return;
            }

            int nextX, nextY;
            for(int i = 0; i < 4; i++) {
                nextX = cur.x + dx[i];
                nextY = cur.y + dy[i];

                if(isAvailable(nextX, nextY, row, col) && maze[nextX][nextY] != '#' && !visited[nextX][nextY][cur.key]) {
                    char character = maze[nextX][nextY];

                    if(character >= 'a' && character <= 'f') {
                        int nextKey = cur.key | (1 << character-'a');

                        visited[nextX][nextY][nextKey] = true;
                        q.add(new Point(nextX, nextY, cur.cnt+1, nextKey));
                    }
                    else if(character >= 'A' && character <= 'F') {
                        if((cur.key & (1 << character-'A')) > 0) {
                            visited[nextX][nextY][cur.key] = true;
                            q.add(new Point(nextX, nextY, cur.cnt+1, cur.key));
                        }
                    }
                    else {
                        visited[nextX][nextY][cur.key] = true;
                        q.add(new Point(nextX, nextY, cur.cnt+1, cur.key));
                    }
                }
            }
        }

        System.out.println(-1);
    }

    public static boolean isAvailable(int x, int y, int r, int c) {
        return x >= 0 && x < r && y >= 0 && y < c;
    }
}
