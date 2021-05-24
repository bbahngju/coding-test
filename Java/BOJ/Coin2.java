package BOJ;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

//BOJ 2294
//O(NM)
public class Coin2 {
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int cnt = Integer.parseInt(st.nextToken());
        int total = Integer.parseInt(st.nextToken());
        int[] coin = new int[cnt];
        int[] dp = new int[total+1];

        for(int i = 0; i < cnt; i++) {
            coin[i] = Integer.parseInt(br.readLine());
        }

        Arrays.fill(dp, 10001);
        dynamicProgramming(coin, dp);

        if(dp[total] == 10001) bw.write("-1\n");
        else bw.write(dp[total] + "\n");

        bw.flush();
        bw.close();
        br.close();
    }

    public static void dynamicProgramming(int[] coin, int[] dp) {
        int coinLen = coin.length;
        int dpLen = dp.length;

        dp[0] = 0;
        for (int k : coin) {
            for (int j = k; j < dpLen; j++) {
                dp[j] = Math.min(dp[j], dp[j - k] + 1);
            }
        }
    }
}
