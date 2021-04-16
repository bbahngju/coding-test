package BOJ;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

//BOJ 2751
public class SortNumber2 {
    public static void main (String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer> inputNumber = new ArrayList<>();
        for(int i=0; i<N; i++) {
            inputNumber.add(Integer.parseInt(br.readLine()));
        }
        Collections.sort(inputNumber);

        StringBuilder sb = new StringBuilder();
        for(int i: inputNumber) {
            sb.append(i).append("\n");
        }
        bw.write(sb.toString() + "\n");

        bw.flush();
        br.close();
        bw.close();
    }
}
