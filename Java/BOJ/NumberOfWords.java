package BOJ;

import java.io.*;

//BOJ 1152
public class NumberOfWords {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String str = br.readLine().trim();
        if(" ".equals(str) || "".equals(str)) {
            bw.write(0 + "");
        }
        else {
            String[] words = str.split(" ");
            bw.write(words.length + "");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
