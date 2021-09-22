package BOJ;

import java.io.*;
import java.util.Arrays;

//BOJ 10809
//O(N)
public class FindAlphabet {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        char[] input = br.readLine().toCharArray();
        int[] alphabet = new int[26];
        Arrays.fill(alphabet, -1);

        for(int i = 0; i < input.length; i++) {
            int a = input[i] - 'a';

            if(alphabet[a] == -1) {
                alphabet[a] = i;
            }
        }

        for(int a : alphabet) {
            sb.append(a).append(" ");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
