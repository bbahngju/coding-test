package BOJ;

import java.io.*;
import java.util.StringTokenizer;

//BOJ 10815
public class NumberCards {
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine(), " ");
        boolean[] haved = new boolean[20000001];
        for(int i=0; i<N; i++) {
            int input = Integer.parseInt(st.nextToken());
            haved[input+10000000] = true;
        }

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<M; i++) {
            int check = Integer.parseInt(st.nextToken());
            if(haved[check+10000000]) bw.write(1 + "\n");
            else bw.write(0 + "\n");
        }

        bw.flush();
        br.close();
        bw.close();
    }
}
