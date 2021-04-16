package BOJ;

import java.io.*;

//BOJ 15829
public class Hashing {
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int L = Integer.parseInt(br.readLine());
        String s = br.readLine();

        long hash = 0L;
        long r = 1;
        long M = 1234567891;

        for(int i=0; i<L; i++) {
            hash += (s.charAt(i)-'a'+1) * r;
            hash %= M;
            r = (r*31) % M;
        }

        bw.write(hash + "\n");

        bw.flush();
        br.close();
        bw.close();
    }
}
