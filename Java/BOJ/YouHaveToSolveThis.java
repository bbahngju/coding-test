package BOJ;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

//BOJ 17390
public class YouHaveToSolveThis {
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        int numberLength = Integer.parseInt(st.nextToken());
        int questionLength = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        int[] number = new int[numberLength+1];
        int[] dp = new int[numberLength+1];

        number[0] = 0;
        for(int i=1; i<number.length; i++) {
            number[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(number);

        dp[0] = 0;
        for(int i=1; i<dp.length; i++) {
            dp[i] = dp[i-1] + number[i];
        }

        int start, end;
        for(int i=0; i<questionLength; i++){
            st = new StringTokenizer(br.readLine(), " ");
            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());

            bw.write(dp[end] - dp[start-1] + "\n");
        }

        bw.flush();
        br.close();
        bw.close();
    }
}
