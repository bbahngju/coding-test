package BOJ;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

//BOJ 17219
//O(N)
public class FindPassword {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        int saveSite = Integer.parseInt(st.nextToken());
        int findSite = Integer.parseInt(st.nextToken());

        Map<String, String> sites = new HashMap<>();
        for(int i = 0; i < saveSite; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            String site = st.nextToken();
            String password = st.nextToken();

            sites.put(site, password);
        }

        for(int i = 0; i < findSite; i++) {
            String site = br.readLine();
            sb.append(sites.get(site)).append("\n");
        }

        bw.write(sb.toString());

        bw.flush();
        bw.close();
        br.close();
    }
}
