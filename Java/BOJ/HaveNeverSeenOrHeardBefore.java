package BOJ;

import java.io.*;
import java.util.*;

//BOJ 1764
//O(NlogN)
public class HaveNeverSeenOrHeardBefore {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuilder answer = new StringBuilder();

        Set<String> noListen = new HashSet<>();
        PriorityQueue<String> noListenSee = new PriorityQueue<>((o1, o2) -> o1.compareTo(o2));

        st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        for(int i = 0; i < n; i++) {
            String name = br.readLine();
            noListen.add(name);
        }

        for(int i = 0; i < m; i++) {
            String name = br.readLine();

            if(!noListen.contains(name)) continue;

            noListenSee.add(name);
        }

        answer.append(noListenSee.size()).append("\n");
        while(!noListenSee.isEmpty()) {
            String name = noListenSee.poll();
            answer.append(name).append("\n");
        }

        bw.write(answer + "");
        bw.close();
        br.close();
    }
}
