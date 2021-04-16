package BOJ;

import java.io.*;
import java.util.Arrays;

//BOJ 2750
public class SortNumber {
    public static void main (String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] inputNumber = new int[N];

        for(int i=0; i<N; i++) {
            inputNumber[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(inputNumber);
        for(int i=0; i<N; i++) {
            bw.write(inputNumber[i] + "\n");
        }

        bw.flush();
        br.close();
        bw.close();
    }
}
