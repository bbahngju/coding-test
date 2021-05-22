package BOJ;

import java.io.*;
import java.util.StringTokenizer;

//BOJ 2240
//O(NW)
public class PlumTree {
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int plum = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());

        int[][][] dp = new int[plum+1][2+1][w+1];

        int cur;
        cur = Integer.parseInt(br.readLine());
        if(cur == 1) {
            dp[1][1][0] = 1;
        }
        else {
            dp[1][2][1] = 1;
        }

        for(int i = 2; i <= plum; i++) {
            cur = Integer.parseInt(br.readLine());
            for(int k = 0; k <= w; k++) {
                if(cur == 1) {
                    if(k == 0) {
                        dp[i][1][k] = dp[i-1][1][k] + 1;
                        dp[i][2][k] = dp[i-1][2][k];
                        continue;
                    }
                    dp[i][1][k] = Math.max(dp[i-1][1][k], dp[i-1][2][k-1]) + 1;
                    dp[i][2][k] = Math.max(dp[i-1][2][k], dp[i-1][1][k-1]);
                }

                if(cur == 2) {
                    if(k == 0) {
                        dp[i][1][k] = dp[i-1][1][k];
                        dp[i][2][k] = dp[i-1][2][k] + 1;
                        continue;
                    }
                    dp[i][1][k] = Math.max(dp[i-1][1][k], dp[i-1][2][k-1]);
                    dp[i][2][k] = Math.max(dp[i-1][2][k], dp[i-1][1][k-1]) + 1;
                }
            }
        }

        int answer = 0;
        for(int j = 1; j <= 2; j++) {
            for(int k = 0; k <= w; k++) {
                answer = Math.max(answer, dp[plum][j][k]);
            }
        }

        bw.write(answer + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
