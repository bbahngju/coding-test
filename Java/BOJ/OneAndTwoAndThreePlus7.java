package BOJ;

import java.io.*;
import java.util.StringTokenizer;

//BOJ 15992
//O(N^2)
public class OneAndTwoAndThreePlus7 {
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int[][] dp = new int[1001][1001];
        dp[1][1] = 1;
        dp[2][1] = 1; dp[2][2] = 1;
        dp[3][1] = 1; dp[3][2] = 2; dp[3][3] = 1;

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

    public static void dynamicProgramming(int[][] dp, int num) {
        int modular = 1000000009;
        for(int i = 4; i <= num; i++) {
            for(int j = 2; j <= i; j++) {
                dp[i][j]  = (((dp[i-3][j-1] + dp[i-2][j-1]) % modular) + dp[i-1][j-1]) % modular;
                System.out.printf("dp[%d][%d] = %d\n", i, j, dp[i][j]);
            }
        }
    }

}
