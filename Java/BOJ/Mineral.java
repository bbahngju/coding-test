package BOJ;

//BOJ 2933
//시뮬, 80분
import java.io.*;
import java.util.*;

public class Mineral {
    public static class Point {
        int x;
        int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int[] dx = {0, 0, 1, -1};
    public static int[] dy = {1, -1, 0, 0};

    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int row = Integer.parseInt(st.nextToken());
        int col = Integer.parseInt(st.nextToken());

        char[][] cave = new char[row][col];

        for(int i = 0; i < row; i++) {
            cave[i] = br.readLine().toCharArray();
        }

        int cnt = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine(), " ");

        int high;
        boolean success;
        boolean[][] visited;
        for(int i = 0; i < cnt; i++) {
            high = Integer.parseInt(st.nextToken());
            success = attack(cave, i, high);
            if(success) {
                visited = new boolean[row][col]; //매 횟수마다 초기화하는 것.
                for(int j = 0; j < col; j++) {
                    if(cave[row-1][j] == 'x' && !visited[row-1][j]) {
                        bfs(row-1, j, cave, visited);
                    }
                }

                for(int j = row-1; j >= 0; j--) {
                    for(int k = 0; k < col; k++) {
                        if(cave[j][k] == 'x' && !visited[j][k]) {
                            fallMineral(row, col, j, k, cave, visited);
                        }
                    }
                }
            }
        }

        for(char[] c : cave) {
            for(char answer : c) {
                bw.write(answer+"");
            }
            bw.write("\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    public static boolean attack(char[][] cave, int turn, int hight) {
        int r = cave.length - hight;
        int c = cave[0].length;

        boolean shot = false;
        if(turn % 2 == 0) {
            for(int i = 0; i < c; i++) {
                if(cave[r][i] == 'x') {
                    cave[r][i] = '.';
                    shot = true;
                    break;
                }
            }
        }

        else {
            for(int i = c-1; i >= 0; i--) {
                if(cave[r][i] == 'x') {
                    cave[r][i] = '.';
                    shot = true;
                    break;
                }
            }
        }

        return shot;
    }

    public static void bfs(int x, int y, char[][] cave, boolean[][] v) {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(x, y));
        v[x][y] = true;

        int r = cave.length;
        int c = cave[0].length;
        int nextX, nextY;
        while(!q.isEmpty()) {
            Point cur = q.poll();
            for(int i = 0; i < 4; i++) {
                nextX = cur.x + dx[i];
                nextY = cur.y + dy[i];
                if(isAble(r, c, nextX, nextY) && cave[nextX][nextY] == 'x' && !v[nextX][nextY]) {
                    q.add(new Point(nextX, nextY));
                    v[nextX][nextY] = true;
                }
            }
        }
    }

    public static boolean isAble(int r, int c, int x, int y) {
        return (x >= 0 && x < r && y >= 0 && y < c);
    }

    public static void fallMineral(int r, int c, int x, int y, char[][] cave, boolean[][] v) {
        Queue<Point> q = new LinkedList<>();
        PriorityQueue<Point> save = new PriorityQueue<>((o1, o2) -> o2.x - o1.x);

        q.add(new Point(x, y));
        save.add(new Point(x, y));

        boolean[][] tmpV = new boolean[r][c];
        tmpV[x][y] = true;

        int minDist = 101, cntDist;
        int nextX, nextY;
        boolean isCount;
        while(!q.isEmpty()) {
            Point cur = q.poll();
            cntDist = 0;
            isCount = true;
            for(int i = cur.x+1; i < r; i++) {
                if(cave[i][cur.y] == 'x' && !v[i][cur.y]) {
                    isCount = false;
                    break;
                }
                if(cave[i][cur.y] == 'x' && v[i][cur.y]) {
                    break;
                }

                cntDist++;
            }

            if(isCount) minDist = Math.min(minDist, cntDist);

            for(int i = 0; i < 4; i++) {
                nextX = cur.x + dx[i];
                nextY = cur.y + dy[i];
                if(isAble(r, c, nextX, nextY) && cave[nextX][nextY] == 'x' && !tmpV[nextX][nextY]) {
                    q.add(new Point(nextX, nextY));
                    save.add(new Point(nextX, nextY));
                    tmpV[nextX][nextY] = true;
                }
            }
        }

        while(!save.isEmpty()) {
            Point s = save.poll();
            cave[s.x][s.y] = '.';
            cave[s.x + minDist][s.y] = 'x';
            v[s.x + minDist][s.y] = true;
        }
    }
}
