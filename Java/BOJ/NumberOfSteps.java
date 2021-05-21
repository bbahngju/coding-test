package BOJ;

import java.io.*;

//BOJ 1562
//O(N*10*2^10)
public class NumberOfSteps {
    private static int modular = 1000000000;
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[][][] dp = new int[101][10][1024];

        dynamicProgramming(dp);

        int num = Integer.parseInt(br.readLine());
        int answer = 0;

        for(int i = 0; i <= 9; i++) {
            answer = (answer + dp[num][i][1023]) % modular;
        }

        bw.write(answer + "\n");

        bw.flush();
        bw.close();
        br.close();
    }

    public static void dynamicProgramming(int[][][] dp) {
        for(int i = 1; i <= 9; i++) {
            dp[1][i][1<<i] = 1;
        }

        for(int i = 2; i <= 100; i++) {
            for(int j = 0; j <= 9; j++) {
                for(int k = 0; k < 1024; k++) {
                    if(j == 0) {
                        dp[i][j][k|(1<<j)] = (dp[i][j][k|(1<<j)] + dp[i-1][j+1][k]) % modular;
                        continue;
                    }
                    if(j == 9) {
                        dp[i][j][k|(1<<j)] = (dp[i][j][k|(1<<j)] + dp[i-1][j-1][k]) % modular;
                        continue;
                    }

                    dp[i][j][k|(1<<j)] = (((dp[i][j][k|(1<<j)] + dp[i-1][j+1][k]) % modular) + dp[i-1][j-1][k]) % modular;
                }
            }
        }
    }
}
