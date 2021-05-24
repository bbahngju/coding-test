package BOJ;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

//BOJ 2437
//O(N)
public class Scale {
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] num = new int[n];
        int total = 0;

        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < n; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(num);
        for(int i = 0; i < n; i++) {
            if(total + 1 < num[i]) break;
            total += num[i];
        }

        bw.write((total + 1) + "\n");

        bw.flush();
        bw.close();
        br.close();
    }
}
