package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//BOJ 1799
//O(2^((N/2)*(N/2)))
public class Bishop {
    private static boolean[][] cross = new boolean[2][20];
    private static int[][] map;
    private static int maximum;
    private static int n;

    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        int b, w;
        map = new int[n][n];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        maximum = 0;
        dfs(0, 0, 0, 0);
        b = maximum;

        maximum = 0;
        dfs(0, 1, 0, 1);
        w = maximum;
        System.out.println(b+w);
    }

    public static void dfs(int x, int y, int cnt, int color) {
        if(y >= n) {
            if(color == 0) {
                if((x+1) % 2 == 0)
                    dfs(x+1, 0, cnt, color);
                else {
                    dfs(x+1, 1, cnt, color);
                }
            }
            else {
                if((x+1) % 2 != 0)
                    dfs(x+1, 0, cnt, color);
                else {
                    dfs(x+1, 1, cnt, color);
                }
            }
            return;
        }

        if(x >= n) {
            maximum = Math.max(maximum, cnt);
            return;
        }

        if(map[x][y] == 1 && !cross[0][(x-y)+(n-1)] && !cross[1][x+y]) {
            cross[0][(x-y)+(n-1)] = true;
            cross[1][x+y] = true;

            dfs(x, y+2, cnt+1, color);

            cross[0][(x-y)+(n-1)] = false;
            cross[1][x+y] = false;
        }
        dfs(x, y+2, cnt, color);
    }
}
