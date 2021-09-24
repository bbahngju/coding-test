package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//BOJ 14500
//O(N^2)
//백트래킹 DFS or 모양 경우의 수 시뮬레이션
//다음엔 모양 다 찾아서 해보기
public class Tetromino {
    static int n, m;
    static int[][] paper;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static boolean[][] visited;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        paper = new int[n][m];
        visited = new boolean[n][m];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < m; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                visited[i][j] = true;
                dfs(i, j, paper[i][j], 0);
                visited[i][j] = false;
            }
        }

        for(int i = 0; i < n-1; i++) {
            for(int j = 0; j < m-1; j++) {
                if(j != m-2) {
                    answer = Math.max(answer, paper[i][j] + paper[i][j+1] + paper[i][j+2] + paper[i+1][j+1]);
                    answer = Math.max(answer, paper[i][j+1] + paper[i+1][j] + paper[i+1][j+1] + paper[i+1][j+2]);
                }
                if(i != n-2) {
                    answer = Math.max(answer, paper[i+1][j] + paper[i][j+1] + paper[i+1][j+1] + paper[i+2][j+1]);
                    answer = Math.max(answer, paper[i][j] + paper[i+1][j] + paper[i+2][j] + paper[i+1][j+1]);
                }
            }
        }

        System.out.println(answer);
    }

    public static void dfs(int x, int y, int total, int depth) {
        if(depth == 3) {
            answer = Math.max(answer, total);
            return;
        }

        for(int i = 0; i < 4; i++) {
            int next_x = x + dx[i];
            int next_y = y + dy[i];

            if(!isAble(next_x, next_y) || visited[next_x][next_y])
                continue;

            visited[next_x][next_y] = true;
            dfs(next_x, next_y, total + paper[next_x][next_y], depth + 1);
            visited[next_x][next_y] = false;
        }
    }

    public static boolean isAble(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }
}
