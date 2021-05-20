package BOJ;

import java.io.*;

//BOJ 15993
//O(N)
public class OneAndTwoAndThreePlus8 {
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[][] dp = new int[100001][2]; //[0]: 홀, [1]: 짝
        dp[1][0] = 1;
        dp[2][0] = 1; dp[2][1] = 1;
        dp[3][0] = 2; dp[3][1] = 2;

        dynamicProgramming(dp, 100000);

        int tc = Integer.parseInt(br.readLine());
        int num;
        while(tc > 0) {
            num = Integer.parseInt(br.readLine());
            bw.write(dp[num][0] + " " + dp[num][1] + "\n");
            tc--;
        }

        bw.flush();
        bw.close();
        br.close();
    }

    public static void dynamicProgramming(int[][] dp, int num) {
        int modular = 1000000009;
        for(int i = 4; i <= num; i++) {
            dp[i][0] = (((dp[i-3][1] + dp[i-2][1]) % modular) + dp[i-1][1]) % modular;
            dp[i][1] = (((dp[i-3][0] + dp[i-2][0]) % modular) + dp[i-1][0]) % modular;
        }
    }
}
