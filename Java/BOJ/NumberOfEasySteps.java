package BOJ;

import java.io.*;

//BOJ 10844
//O(N)
public class NumberOfEasySteps {
    private static int modular = 1000000000;

    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int num = Integer.parseInt(br.readLine());
        int[][] dp = new int[num+1][10];

        dynamicProgramming(dp, num);

        int answer = 0;
        for(int i = 0; i <= 9; i++) {
            answer = (answer + dp[num][i]) % modular;
        }

        bw.write(answer + "\n");

        bw.flush();
        bw.close();
        br.close();
    }

    public static void dynamicProgramming(int[][] dp, int num) {
        for(int i = 1; i <= 9; i++) {
            dp[1][i] = 1;
        }

        for(int i = 2; i <= num; i++) {
            for(int j = 0; j <= 9; j++) {
                if(j == 0) {
                    dp[i][j] = dp[i-1][j+1];
                    continue;
                }
                if(j == 9) {
                    dp[i][j] = dp[i-1][j-1];
                    continue;
                }
                dp[i][j] = (dp[i-1][j+1] + dp[i-1][j-1]) % modular;
            }
        }
    }
}
