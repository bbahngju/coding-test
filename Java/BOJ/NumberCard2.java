package BOJ;

import java.io.*;
import java.util.StringTokenizer;

//O(N)
//10816
public class NumberCard2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        int[] positive = new int[10000001];
        int[] negative = new int[10000001];

        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());
            if(num < 0) {
                negative[-1 * num]++;
                continue;
            }

            positive[num]++;
        }

        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine(), " ");

        StringBuilder answer = new StringBuilder();
        for(int i = 0; i < m; i++) {
            int num = Integer.parseInt(st.nextToken());
            if(num < 0) {
                answer.append(negative[-1 * num]);
                answer.append(" ");
                continue;
            }

            answer.append(positive[num]);
            answer.append(" ");
        }

        bw.write(answer.toString());

        bw.flush();
        bw.close();
        br.close();
    }
}
