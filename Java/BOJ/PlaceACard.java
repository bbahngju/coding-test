package BOJ;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

//BOJ 5568
public class PlaceACard {
    static Set<String> per = new HashSet<>();
    static String[] numbers;
    static boolean[] visited;
    static int n, k;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());

        numbers = new String[n];
        for(int i = 0; i < n; i++) {
            String num = br.readLine();
            numbers[i] = num;
        }

        visited = new boolean[n];
        dfs("", 0);

        bw.write(per.size() + "");
        bw.flush();
        bw.close();
        br.close();
    }

    public static void dfs(String result, int len) {
        if(len == k) {
            per.add(result);
            return;
        }

        for(int i = 0; i < n; i++) {
            if(visited[i])
                continue;
            visited[i] = true;
            dfs(result + numbers[i], len + 1);
            visited[i] = false;
        }
    }
}
