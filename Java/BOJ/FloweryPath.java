package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//BOJ 14620
//
public class FloweryPath {
    static int[][] path;
    static int minimum = 20000;
    static int[] dx = new int[3];
    static int[] dy = new int[3];
    static int N;
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        path = new int[N][N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < N; j++) {
                path[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(1, 1, 0, 0);
        System.out.println(minimum);
    }

    public static void dfs(int x, int y, int cnt, int tot) {
        if(cnt == 3) {
            minimum = Math.min(minimum, tot);
            return;
        }
        for(int i = x; i < N-1; i++) {
            for(int j = 1; j < N-1; j++) {
                if(cnt == 0 || isAvail(i, j, cnt)) {
                    dx[cnt] = i;
                    dy[cnt] = j;
                    int plus = path[i][j] + path[i][j-1] + path[i][j+1] + path[i-1][j] + path[i+1][j];
                    dfs(i, j+1, cnt+1, tot+plus);
                }
            }
        }
    }

    public static boolean isAvail(int x, int y, int cnt) {
        int dist;
        for(int i = 0; i < cnt; i++) {
            dist = Math.abs(dx[i]-x) + Math.abs(dy[i]-y);
            if(dist <= 2) return false;
        }

        return true;
    }
}
