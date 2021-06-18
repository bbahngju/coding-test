package BOJ;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//BOJ 2636
//시뮬, 32분
//O(2000000)
public class Cheese {
    public static int[] dx = {0, 0, -1, 1};
    public static int[] dy = {-1, 1, 0, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int row = Integer.parseInt(st.nextToken());
        int col = Integer.parseInt(st.nextToken());

        int[][] board = new int[row][col];
        for(int i = 0; i < row; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < col; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int time = 0;
        int lastRemain = 0;
        while(true) {
            Queue<Point> save = searchMeltingCheese(0,0, board);
            if(save.isEmpty())
                break;
            lastRemain = save.size();
            time++;

            while(!save.isEmpty()) {
                Point cur = save.poll();
                board[cur.x][cur.y] = 0;
            }
        }

        bw.write(time + "\n" + lastRemain + "\n");

        bw.flush();
        bw.close();
        br.close();
    }

    public static Queue<Point> searchMeltingCheese(int x, int y, int[][] board) {
        Queue<Point> result = new LinkedList<>();
        int r = board.length;
        int c = board[0].length;
        boolean[][] visited = new boolean[r][c];
        Queue<Point> q = new LinkedList<>();
        int nextX, nextY;

        q.add(new Point(x, y));
        visited[x][y] = true;

        while(!q.isEmpty()) {
            Point cur = q.poll();

            for(int i = 0; i < 4; i++) {
                nextX = cur.x + dx[i];
                nextY = cur.y + dy[i];

                if(nextX < 0 || nextX >= r || nextY < 0 || nextY >= c) continue;
                if(board[nextX][nextY] == 1 && !visited[nextX][nextY]) {
                    result.add(new Point(nextX, nextY));
                    visited[nextX][nextY] = true;
                }
                else if(board[nextX][nextY] == 0 && !visited[nextX][nextY]) {
                    q.add(new Point(nextX, nextY));
                    visited[nextX][nextY] = true;
                }
            }
        }

        return result;
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
