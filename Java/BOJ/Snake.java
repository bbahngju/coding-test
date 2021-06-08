package BOJ;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//BOJ 3190
//O(10000)
//시뮬, 40분
public class Snake {
    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {1, 0, -1, 0};
    private static int[][] map;

    public static class Direct {
        int time;
        char dir;

        public Direct(int t, char d) {
            this.time = t;
            this.dir = d;
        }
    }

    public static class Tail {
        int x;
        int y;

        public Tail(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        map = new int[N][N];
        for(int[] m : map) {
            Arrays.fill(m, 0);
        }

        int appleX, appleY;
        for(int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            appleX = Integer.parseInt(st.nextToken())-1;
            appleY = Integer.parseInt(st.nextToken())-1;

            map[appleX][appleY] = 2;
        }

        int L = Integer.parseInt(br.readLine());
        Queue<Direct> d = new LinkedList<>();
        int time; char dir;
        for(int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            time = Integer.parseInt(st.nextToken());
            dir = st.nextToken().charAt(0);

            d.add(new Direct(time, dir));
        }

        int answer = Dummy(d, N);
        bw.write(answer + "\n");

        bw.flush();
        bw.close();
        br.close();
    }

    public static int Dummy(Queue<Direct> d, int N) {
        int move = 0;
        int dir = 0; //오른쪽

        Direct after = d.poll();
        Queue<Tail> tails = new LinkedList<>();

        int x = 0; int y = 0;
        while(true) {
            tails.add(new Tail(x, y));
            map[x][y] = 1;

            if(after.time == move) {
                if(after.dir == 'L') {
                    dir = (dir - 1 < 0) ? 3 : dir - 1;
                }
                else {
                    dir = (dir + 1) % 4;
                }

                if(!d.isEmpty())
                    after = d.poll();
            }

            x = x + dx[dir];
            y = y + dy[dir];

            if(x < 0 || x >= N || y < 0 || y >= N) break;
            if(map[x][y] == 1) break;

            if(map[x][y] != 2) {
                Tail delete = tails.poll();
                map[delete.x][delete.y] = 0;
            }

            move++;
        }

        return move+1;
    }
}
