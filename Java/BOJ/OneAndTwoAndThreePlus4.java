package BOJ;

import java.io.*;

//BOJ 15989
//O(N)
public class OneAndTwoAndThreePlus4 {
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[][] dp = new int[10001][4];
        dp[1][1] = 1; dp[1][2] = 0; dp[1][3] = 0;
        dp[2][1] = 1; dp[2][2] = 1; dp[2][3] = 0;
        dp[3][1] = 1; dp[3][2] = 1; dp[3][3] = 1;

        int tc = Integer.parseInt(br.readLine());
        int num;
        int maximum = 3;

        while(tc > 0) {
            num = Integer.parseInt(br.readLine());
            if(maximum < num) {
                for(int i = maximum+1; i <= num; i++) {
                    dp[i][1] = dp[i-1][1];
                    dp[i][2] = dp[i-2][1] + dp[i-2][2];
                    dp[i][3] = dp[i-3][1] + dp[i-3][2] + dp[i-3][3];
                }
            }
            bw.write(dp[num][1]+dp[num][2]+dp[num][3] + "\n");
            tc--;
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
