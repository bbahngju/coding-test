package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//BOJ 15685
//
public class DragonCurve {
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] arg) throws IOException {
        boolean[][] graph = new boolean[101][101];
        int[][] dragon;
        int x, y, d, g;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        dragon = new int[n][1024];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            y = Integer.parseInt(st.nextToken());
            x = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            g = Integer.parseInt(st.nextToken());

            graph[x][y] = true;
            dragon[i][0] = d;
            graph[x+dx[d]][y+dy[d]] = true;

            if(g == 0) continue;

            playCurve(x+dx[d], y+dy[d], graph, dragon, g, i);
        }

        int answer = countSquare(graph);
        System.out.println(answer);
    }

    public static void playCurve(int x, int y, boolean[][] graph, int[][] dragon, int g, int curDragon) {
        int preX = x;
        int preY = y;
        int idx;
        int curG, curX, curY;

        for(int i = 1; i <= g; i++) {
            idx = (int)Math.pow(2,i-1);
            int j = idx-1;
            for(; j >= 0; j--) {
                curG = (dragon[curDragon][j]+1)%4;
                dragon[curDragon][idx] = curG;

                curX = preX + dx[curG];
                curY = preY + dy[curG];
                graph[curX][curY] = true;

                preX = curX;
                preY = curY;

                idx++;
            }
        }
    }

    public static int countSquare(boolean[][] graph) {
        int cnt = 0;
        for(int i = 0; i < graph.length-1; i++) {
            for(int j = 0; j < graph[0].length-1; j++) {
                if(graph[i][j]) {
                    if(graph[i+1][j] && graph[i][j+1] && graph[i+1][j+1])
                        cnt++;
                }
            }
        }
        return cnt;
    }
}
