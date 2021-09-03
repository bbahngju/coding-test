package BOJ;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

//BOJ 1620
//O(N+M)
public class IAmPocketmonMasterLeeDasom {
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        StringBuilder answer = new StringBuilder();

        Map<String, String> pocketmons = new HashMap<>();
        for(int i = 1; i <= n; i++) {
            String pocketmon = br.readLine();
            pocketmons.put(pocketmon, Integer.toString(i));
            pocketmons.put(Integer.toString(i), pocketmon);
        }

        for(int i = 0; i < m; i++) {
            String poketmon = br.readLine();
            answer.append(pocketmons.get(poketmon)).append("\n");
        }

        bw.write(answer + "");
        bw.close();
        br.close();
    }
}
