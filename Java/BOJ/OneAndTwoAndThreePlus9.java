package BOJ;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

//BOJ 16195
//O(N^2)
public class OneAndTwoAndThreePlus9 {
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int[][] dp = new int[1001][1001];
        Arrays.fill(dp[1], 1);
        dp[2][1] = 1; dp[2][2] = 2;
        dp[3][1] = 1; dp[3][2] = 3; dp[3][3] = 4;
        fillArray(dp, 2, 3);
        fillArray(dp, 3, 4);

        dynamicProgramming(dp, 1000);

        int tc = Integer.parseInt(br.readLine());
        int n, m;
        while(tc > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            bw.write(dp[n][m] + "\n");

            tc--;
        }

        bw.flush();
        bw.close();
        br.close();
    }

    public static void fillArray(int[][] dp, int firstIdx, int secondIdx) {
        for(int i = secondIdx; i <= 1000; i++) {
            dp[firstIdx][i] = dp[firstIdx][i-1];
        }
    }

    public static void dynamicProgramming(int[][] dp, int num) {
        int modular = 1000000009;

        for(int i = 4; i <= 1000; i++) {
            for(int j = 2; j <= 1000; j++) {
                dp[i][j] = (((dp[i-3][j-1] + dp[i-2][j-1]) % modular) + dp[i-1][j-1]) % modular;
            }
        }
    }
}
