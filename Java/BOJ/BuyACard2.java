package BOJ;

import java.io.*;
import java.util.StringTokenizer;

//BOJ 16194
//O(N^2)
public class BuyACard2 {
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] price = new int[n+1];
        int[] dp = new int[n+1];

        st = new StringTokenizer(br.readLine(), " ");
        int p;
        for(int i = 1; i <= n; i++) {
            p = Integer.parseInt(st.nextToken());
            price[i] = p;
            dp[i] = p;
        }

        dynamicProgramming(n, dp, price);

        bw.write(dp[n] + "\n");

        bw.flush();
        bw.close();
        br.close();
    }

    public static void dynamicProgramming(int n, int[] dp, int[] price) {
        for(int i = 1; i <= n; i++) {
            for(int j = i+1; j <= n; j++) {
                dp[j] = Math.min(dp[j], dp[j-i] + price[i]);
            }
        }
    }
}
