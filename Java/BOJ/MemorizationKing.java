package BOJ;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;


//BOJ 2776
//O(N+M)
public class MemorizationKing {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        Set<Integer> memo1 = new HashSet<>();
        StringBuilder answer = new StringBuilder();

        int tc = Integer.parseInt(br.readLine());

        while(tc-- > 0) {
            int n = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine(), " ");
            for(int i = 0; i < n; i++) {
                memo1.add(Integer.parseInt(st.nextToken()));
            }

            int m = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine(), " ");
            for(int i = 0; i < m; i++) {
                int num = Integer.parseInt(st.nextToken());
                if(memo1.contains(num)) {
                    answer.append(1).append("\n");
                }
                else {
                    answer.append(0).append("\n");
                }
            }
            memo1.clear();
        }

        bw.write(answer.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
