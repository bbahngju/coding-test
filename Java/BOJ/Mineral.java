package BOJ;

//BOJ 2933

import com.sun.source.doctree.ThrowsTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Mineral {
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static Queue<Point> points = new LinkedList<>();

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

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        char[][] cave = new char[R][C];

        for(int i=0; i<R; i++) {
            cave[i] = br.readLine().toCharArray();
        }

        int cnt = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine(), " ");

        for(int i=0; i<cnt; i++) {
            boolean isThrow = isThrowStick(i, Integer.parseInt(st.nextToken()), cave);
            if(isThrow) searchCluster(cave);

            for (char[] chars : cave) {
                for (int k = 0; k < cave[0].length; k++) {
                    System.out.print(chars[k]);
                }
                System.out.println();
            }
        }


    }

    public static boolean isThrowStick(int turn, int hight, char[][] cave) {
        System.out.println("isThrowStick");
        int x = cave.length - hight;
        int nextX, nextY;
        if(turn % 2 == 0) {
            for(int i=0; i<cave[0].length; i++) {
                if(cave[x][i] == 'x') {
                    cave[x][i] = '.';
                    addQueue(x, i, cave);
                    return true;
                }
            }
        }
        else {
            for(int i=cave.length-1; i>=0; i--) {
                if(cave[x][i] == 'x') {
                    cave[x][i] = '.';
                    addQueue(x, i, cave);
                    return true;
                }
            }
        }

        return false;
    }
    public static void addQueue(int x, int y, char[][] cave) {
        System.out.printf("addQueue: %d, %d\n", x, y);
        points.clear();
        int nextX, nextY;
        for(int i=0; i<4; i++) {
            nextX = x + dx[i];
            nextY = y + dy[i];

            if(nextX >= 0 && nextX < cave.length && nextY >= 0 && nextY < cave[0].length) {
                if(cave[nextX][nextY] == 'x') {
                    points.add(new Point(nextX, nextY));

                }
            }
        }
    }
    public static void searchCluster(char[][] cave) {
        System.out.println("searchCluster");
        boolean[][] visited = new boolean[cave.length][cave[0].length];
        int x = cave.length-1;
        boolean isFallen = false;

        while(!points.isEmpty()) {
            Point p = points.poll();
            if(!visited[p.x][p.y]) {
                visited[p.x][p.y] = true;
                Queue<Point> fallenQ = bfs(p.x, p.y, visited, cave);
                if(!fallenQ.isEmpty()) {
                    int minFall = fallenCluster(visited, cave, fallenQ);
                    reDraw(fallenQ, cave, minFall);
                    break; //하나의 클러스터만 떨어진다 했으므로.
                }
            }
        }
    }
    public static Queue<Point> bfs(int x, int y, boolean[][] v, char[][] cave) {
        System.out.println("bfs");
        int nextX, nextY;
        Queue<Point> save = new LinkedList<>();
        Queue<Point> search = new LinkedList<>();
        search.add(new Point(x, y));
        save.add(new Point(x, y));

        while(!search.isEmpty()) {
            Point p = search.poll();

            if(p.x == cave.length-1) {
                save.clear();
                return save;
            }

            for(int i=0; i<4; i++) {
                nextX = p.x + dx[i];
                nextY = p.y + dy[i];

                if(nextX >= 0 && nextX < cave.length && nextY >= 0 && nextY < cave[0].length) {
                    if(cave[nextX][nextY] == 'x' && !v[nextX][nextY]) {
                        v[nextX][nextY] = true;
                        save.add(new Point(nextX, nextY));
                        search.add(new Point(nextX, nextY));
                    }
                }
            }

        }
        return save;
    }
    public static int fallenCluster(boolean[][] visited, char[][] cave, Queue<Point> fallen) {
        System.out.println("fallenCluster");
        System.out.println(fallen.size());
        int minDist = 101;
        int cnt;
        for(int i=0; i<fallen.size(); i++) {
            Point p = fallen.poll();
            cnt = 0;
            for(int j=p.x; j<cave.length-1; j++) {
                if(cave[j][p.y] == 'x' && visited[j][p.y]) {
                    cnt = 101;
                    break;
                }
                if(cave[j][p.y] == 'x' && !visited[j][p.y]) {
                    break;
                }
                cnt++;
            }
            fallen.add(new Point(p.x, p.y));
            minDist = Math.min(minDist, cnt);
        }
        System.out.println(minDist);
        return minDist;
    }
    public static void reDraw(Queue<Point> q, char[][] cave, int minFall) {
        System.out.println("reDraw");
        while(!q.isEmpty()) {
            Point p = q.poll();
            cave[p.x][p.y] = '.';
            cave[p.x][p.y+minFall] = 'x';
        }
    }
}
