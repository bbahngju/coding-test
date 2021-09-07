package BOJ;

import java.io.*;

//BOJ 10775
//O(g + logp)
public class Airport {
    public static int[] gates;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int g = Integer.parseInt(br.readLine());
        int p = Integer.parseInt(br.readLine());

        gates = new int[g + 1];

        for(int i = 1; i <= g; i++) {
            gates[i] = i;
        }

        int answer = 0;
        for(int i = 1; i <= p; i++) {
            int gate = Integer.parseInt(br.readLine());

            int docking = find(gate);
            if(docking == 0) break;

            union(docking, docking-1);
            answer++;
        }

        bw.write(answer + "");
        bw.close();
        br.close();
    }

    public static int find(int cur) {
        if(gates[cur] == cur) {
            return cur;
        }

        return gates[cur] = find(gates[cur]);
    }

    public static void union(int cur, int prev) {
        int c = find(cur);
        int p = find(prev);

        if(c == p) return;

        gates[c] = p;
    }
}
