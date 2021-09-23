package BOJ;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//BOJ 13460
//O(4^10)
//완탐, BFS
public class MarbleEscape2 {
    static int n, m;
    static char[][] board;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int answer = -1;

    public static class Info implements Comparable<Info>{
        int red_x;
        int red_y;
        int blue_x;
        int blue_y;
        int time;

        public Info(int red_x, int red_y, int blue_x, int blue_y, int time) {
            this.red_x = red_x;
            this.red_y = red_y;
            this.blue_x = blue_x;
            this.blue_y = blue_y;
            this.time = time;
        }

        @Override
        public int compareTo(Info o) {
            return this.time - o.time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int r_x = 0, r_y = 0, b_x = 0, b_y = 0;
        board = new char[n][m];

        for(int i = 0; i < n; i++) {
            String line = br.readLine();
            int idx = 0;

            for(char c : line.toCharArray()) {
                if(c == 'R') {
                    r_x = i;
                    r_y = idx;
                }
                else if(c == 'B') {
                    b_x = i;
                    b_y = idx;
               }

                board[i][idx] = c;
                idx++;
            }
        }

        bfs(new Info(r_x, r_y, b_x, b_y, 0));
        System.out.println(answer);
    }

    public static void bfs(Info start) {
        boolean[][][][] visited = new boolean[10][10][10][10];
        Queue<Info> q = new LinkedList<>();
        visited[start.red_x][start.red_y][start.blue_x][start.blue_y] = true;
        q.add(start);

        while(!q.isEmpty()) {
            Info cur = q.poll();

            if(cur.time > 10) {
                break;
            }
            if(board[cur.red_x][cur.red_y] == 'O') {
                answer = cur.time;
                break;
            }

            for(int i = 0; i < 4; i++) {
                int[] next_r = move(cur.red_x, cur.red_y, i);
                int[] next_b = move(cur.blue_x, cur.blue_y, i);

                if(board[next_b[0]][next_b[1]] == 'O') {
                    continue;
                }

                if(next_r[0] == next_b[0] && next_r[1] == next_b[1]) {
                    if(i == 0) {
                        if(cur.red_x < cur.blue_x) {
                            next_r[0]--;
                        }
                        else {
                            next_b[0]--;
                        }
                    }
                    else if(i == 1) {
                        if(cur.red_x > cur.blue_x) {
                            next_r[0]++;
                        }
                        else {
                            next_b[0]++;
                        }
                    }
                    else if(i == 2) {
                        if(cur.red_y < cur.blue_y) {
                            next_r[1]--;
                        }
                        else {
                            next_b[1]--;
                        }
                    }
                    else {
                        if(cur.red_y > cur.blue_y) {
                            next_r[1]++;
                        }
                        else {
                            next_b[1]++;
                        }
                    }
                }

                if(!visited[next_r[0]][next_r[1]][next_b[0]][next_b[1]]) {
                    q.add(new Info(next_r[0], next_r[1], next_b[0], next_b[1], cur.time+1));
                    visited[next_r[0]][next_r[1]][next_b[0]][next_b[1]] = true;
                }
            }

        }
    }
    public static int[] move(int x, int y, int dir) {
        int cur_x = x;
        int cur_y = y;

        while(true) {
            int next_x = cur_x + dx[dir];
            int next_y = cur_y + dy[dir];

            if(!isAble(next_x, next_y)) {
                break;
            }

            if(board[cur_x][cur_y] == 'O') {
                break;
            }

            cur_x = next_x;
            cur_y = next_y;
        }

        return new int[]{cur_x, cur_y};
    }

    public static boolean isAble(int x, int y) {
        return board[x][y] != '#';
    }
}
