package BOJ;

import java.io.*;

//BOJ 11720
//O(N)
public class SumOfNumber {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        String numbers = br.readLine();

        int answer = 0;
        for(int i = 0; i < N; i++) {
            int num = numbers.charAt(i) - '0';
            answer += num;
        }

        bw.write(answer + "");
        bw.flush();
        bw.close();
        br.close();
    }
}
