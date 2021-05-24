package BOJ;

import java.io.*;

//BOJ 11726
//O(N)
public class TwoxNTiling {
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n;
        int[] dp = new int[1001];
        dp[1] = 1;
        dp[2] = 2;

        for(int i = 3; i < dp.length; i++) {
            dp[i] = (dp[i-1] + dp[i-2]) % 10007;
        }

        n = Integer.parseInt(br.readLine());
        bw.write(dp[n] + "\n");

        bw.flush();
        bw.close();
        br.close();
    }
}
