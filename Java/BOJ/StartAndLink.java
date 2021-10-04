package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//BOJ 14889
//순열
public class StartAndLink {
    static int N, k;
    static int[][] power;
    static int[] teamA, teamB;
    static boolean[] visited;
    static int answer = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        k = N/2;

        power = new int[N][N];
        visited = new boolean[N];
        teamA = new int[k];
        teamB = new int[k];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            for(int j = 0; j < N; j++) {
                power[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        permutation(0, 0);
        System.out.println(answer);
    }

    static void permutation(int cnt, int idx) {
        if(cnt == k) {
            int aIdx = 0;
            int bIdx = 0;

            for(int i = 0; i < N; i++) {
                if(visited[i]) {
                    teamA[aIdx++] = i;
                }
                else {
                    teamB[bIdx++] = i;
                }
            }

            int pointA = calculation(teamA);
            int pointB = calculation(teamB);

            answer = Math.min(answer, Math.abs(pointA - pointB));
            return;
        }

        for(int i = idx; i < N; i++) {
            if(visited[i]) {
                continue;
            }

            visited[i] = true;
            permutation(cnt + 1, i + 1);
            visited[i] = false;
        }
    }

    static int calculation(int[] team) {
        int len = team.length;
        int result = 0;

        for (int j : team) {
            for (int value : team) {
                result += power[j][value];
            }
        }

        return result;
    }
}
