package BOJ;

import java.io.*;

//BOJ 15990
//O(N)
public class OneAndTwoAndThreePlus5 {
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int tc = Integer.parseInt(br.readLine());
        int[][] dp = new int[100001][4];

        dp[1][1] = 1; dp[1][2] = 0; dp[1][3] = 0;
        dp[2][1] = 0; dp[2][2] = 1; dp[2][3] = 0;
        dp[3][1] = 1; dp[3][2] = 1; dp[3][3] = 1;

        int maximum = 3;
        int modular = 1000000009;
        int num;
        while(tc > 0) {
            num = Integer.parseInt(br.readLine());
            if(num > maximum) {
                for(int i = maximum+1; i <= num; i++) {
                    dp[i][1] = (dp[i-1][2] + dp[i-1][3]) % modular;
                    dp[i][2] = (dp[i-2][1] + dp[i-2][3]) % modular;
                    dp[i][3] = (dp[i-3][1] + dp[i-3][2]) % modular;
                }
                maximum = num;
            }

            bw.write(((((dp[num][1] + dp[num][2]) % modular) + dp[num][3]) % modular) + "\n");
            tc--;
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
