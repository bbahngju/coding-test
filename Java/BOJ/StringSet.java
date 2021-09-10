package BOJ;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

//BOJ 14425
//O(N+M)
public class StringSet {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        Set<String> set = new HashSet<>();
        int answer = 0;

        st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        for(int i = 0; i < n; i++) {
            String s = br.readLine();
            set.add(s);
        }

        for(int i = 0; i < m; i++) {
            String s = br.readLine();
            if(set.contains(s)) {
                answer++;
            }
        }

        bw.write(answer + "");
        bw.flush();
        bw.close();
        br.close();
    }
}
