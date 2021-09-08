package BOJ;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

//BOJ 1269
//O(N+M)
public class SymmetricDifference {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        Set<Integer> set = new HashSet<>();

        st = new StringTokenizer(br.readLine(), " ");
        int aCnt = Integer.parseInt(st.nextToken());
        int bCnt = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < aCnt; i++) {
            int num = Integer.parseInt(st.nextToken());
            set.add(num);
        }

        int intersection = 0;

        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < bCnt; i++) {
            int num = Integer.parseInt(st.nextToken());
            if(set.contains(num)) {
                intersection += 2;
            }
        }

        bw.write(aCnt + bCnt - intersection + "");
        bw.flush();
        bw.close();
        br.close();
    }
}
