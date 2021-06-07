package BOJ;

import java.io.*;
import java.util.StringTokenizer;

//BOJ 14503
//O(NM)
//시뮬, 42분
public class RoboticVacuum {
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};

    private static int[][] area;
    private static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int dir = Integer.parseInt(st.nextToken());

        area = new int[r][c];
        visited = new boolean[r][c];

        for(int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < c; j++) {
                area[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        int nextDir, nextX, nextY;
        boolean isNext;

        while(true) {
            isNext = false;
            if(!visited[x][y]) {
                visited[x][y] = true;
                answer++;
            }

            for(int i = 0; i < 4; i++) {
                nextDir = (dir - 1 < 0) ? 3 : dir - 1;
                nextX = x + dx[nextDir]; nextY = y + dy[nextDir];

                if(isNotVisited(nextX, nextY) && isNotWall(nextX, nextY)) {
                    x = nextX;
                    y = nextY;
                    dir = nextDir;
                    isNext = true;
                    break;
                }
                dir = nextDir;
            }

            if(isNext) continue;

            x = x - dx[dir];
            y = y - dy[dir];
            if(isNotWall(x, y))
                continue;
            break;
        }

        bw.write(answer + "\n");

        bw.flush();
        bw.close();
        br.close();
    }

    public static boolean isNotVisited(int x, int y) {
        return !visited[x][y];
    }
    public static boolean isNotWall(int x, int y) {
        return area[x][y] != 1;
    }
}
