package BOJ;

import java.io.*;
import java.util.StringTokenizer;

//BOJ 11053
//O((N*(N+1)/2 + N)) => O(N^2)
public class LongestIncreasingPartialSequence {
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] input = new int[n];
        int[] dp = new int[n];
        dp[0] = 1;
        int answer = 0;

        st = new StringTokenizer(br.readLine(), " ");

        for(int i = 0; i < n; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        dynamicProgramming(input, dp, n);

        for(int ans : dp) {
            answer = Math.max(ans, answer);
        }

        bw.write(answer + "\n");

        bw.flush();
        bw.close();
        br.close();
    }

    public static void dynamicProgramming(int[] input, int[] dp, int n) {
        for(int i = 1; i < n; i++) {
            int maximum = 0;
            for(int j = 0; j < i; j++) {
                if(input[j] < input[i]) {
                    maximum = Math.max(dp[j], maximum);
                }
            }
            dp[i] = maximum + 1;
        }
    }
}
