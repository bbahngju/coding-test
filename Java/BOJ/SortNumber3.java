package BOJ;

import java.io.*;
import java.util.Arrays;

//BOJ 10989
public class SortNumber3 {
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int count = Integer.parseInt(br.readLine());
        int[] numberCount = new int[10001];
        Arrays.fill(numberCount, 0);
        int input;
        for(int i=0; i<count; i++) {
            input = Integer.parseInt(br.readLine());
            numberCount[input]++;
        }
        for(int i=1; i<numberCount.length; i++) {
            if(numberCount[i] > 0) {
                for(int j=0; j<numberCount[i]; j++) bw.write(i+"\n");
            }
        }

        bw.flush();
        br.close();
        bw.close();
    }
}
