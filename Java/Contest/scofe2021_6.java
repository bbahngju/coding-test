package Contest;

import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
//팝업스토어
class scofe2021_6 {
    public static class Point implements Comparable<Point>{
        int x;
        int y;

        public Point(int curX, int curY) {
            this.x = curX;
            this.y = curY;
        }

        @Override
        public int compareTo(Point o) {
            return Integer.compare(this.x, o.x);
        }
    }

    static PriorityQueue<Point> points = new PriorityQueue<>();
    static int[][] road;
    static int[][] buy;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        road = new int[M][N];
        buy = new int[M][N];

        for(int[] b : buy)
            Arrays.fill(b, 0);

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<N; j++) {
                road[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        buy[0][0] = road[0][0];
        points.add(new Point(0, 0));
        Search(M, N);

        System.out.println(buy[M-1][N-1]);
    }

    public static void Search(int M, int N) {
        while(!points.isEmpty()) {
            Point p = points.poll();

            if(p.x+1 > 0 && p.x+1 < M) {
                int total = buy[p.x][p.y] + road[p.x+1][p.y];
                if(total > buy[p.x+1][p.y]) {
                    buy[p.x+1][p.y] = total;
                    points.add(new Point(p.x+1, p.y));
                }
            }

            if(p.y+1 > 0 && p.y+1 < N) {
                int total = buy[p.x][p.y] + road[p.x][p.y+1];
                if(total > buy[p.x][p.y+1]) {
                    buy[p.x][p.y+1] = total;
                    points.add(new Point(p.x, p.y+1));
                }
            }
        }

    }
}
