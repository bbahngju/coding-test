package BOJ;

import java.io.*;

//BOJ 15988
//O(N)
public class OneAndTwoAndThreePlus3 {
    private static int modular = 1000000009;

    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int tc = Integer.parseInt(br.readLine());
        int[] dp = new int[1000001];
        int num;
        int maximum = 3;

        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;
        while(tc > 0) {
            num = Integer.parseInt(br.readLine());
            if(maximum < num) {
                for(int i = maximum+1; i <= num; i++) {
                    dp[i] = ((dp[i-3] + dp[i-2]) % modular + dp[i-1]) % modular;
                }
            }
            bw.write(dp[num] + "\n");
            maximum = Math.max(maximum, num);
            tc--;
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
