package BOJ;

import java.io.*;

//BOJ 15991
//O(N)
public class OneAndTwoAndThreePlus6 {
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] dp = new int[100001];
        dynamicProgramming(dp, 100000);
        int tc = Integer.parseInt(br.readLine());
        int num;

        while(tc > 0) {
            num = Integer.parseInt(br.readLine());
            bw.write(dp[num] + "\n");
            tc--;
        }

        bw.flush();
        bw.close();
        br.close();
    }

    public static void dynamicProgramming(int[] dp, int num) {
        int modular = 1000000009;
        dp[1] = 1; dp[2] = 2; dp[3] = 2;

        for(int i = 4; i <= num; i++) {
            dp[i] = (dp[i-2] + dp[i-4]) % modular;
            if(i % 2 == 0 && i/2 <= 3) {
                dp[i] += 1;
            }
            if(i-6 > 0) {
                dp[i] = (dp[i] + dp[i-6]) % modular;
            }
        }
    }
}
