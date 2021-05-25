package BOJ;

import java.io.*;
import java.util.*;

//BOJ 10217
//
//DP + Dijkstra
public class KCMTravel {
    public static class Node implements Comparable<Node> {
        int no;
        int fee;
        int time;

        public Node(int no, int fee, int time) {
            this.no = no;
            this.fee = fee;
            this.time = time;
        }

        @Override
        public int compareTo(Node o) {
            if(this.time < o.time) return -1;
            else if(this.time == o.time) {
                return this.fee - o.fee;
            }
            return 1;
        }
    }

    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int tc = Integer.parseInt(br.readLine());
        int INF = Integer.MAX_VALUE;
        while(tc > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            int totalAirport = Integer.parseInt(st.nextToken());
            int totalFee = Integer.parseInt(st.nextToken());
            int totalTicket = Integer.parseInt(st.nextToken());

            int[][] dp = new int[totalAirport + 1][totalFee + 1];
            for(int[] i : dp) {
                Arrays.fill(i, INF);
            }

            ArrayList<Node>[] nodes = new ArrayList[totalAirport + 1];
            for(int i = 1; i < totalAirport + 1; i++) {
                nodes[i] = new ArrayList<>();
            }
            int start, end, fee, time;
            for(int i = 0; i < totalTicket; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                start = Integer.parseInt(st.nextToken());
                end = Integer.parseInt(st.nextToken());
                fee = Integer.parseInt(st.nextToken());
                time = Integer.parseInt(st.nextToken());

                nodes[start].add(new Node(end, fee, time));
            }

            Dijkstra(nodes, dp, totalAirport, totalFee);

            int answer = INF;
            for(int i = 0; i <= totalFee; i++) {
                answer = Math.min(answer, dp[totalAirport][i]);
            }

            if(answer == INF) bw.write("Poor KCM\n");
            else bw.write(answer + "\n");

            tc--;
        }

        bw.flush();
        bw.close();
        br.close();
    }

    public static void Dijkstra(ArrayList<Node>[] nodes, int[][] dp, int airport, int fee) {
        PriorityQueue<Node> q = new PriorityQueue<>();
        q.add(new Node(1, 0, 0));
        dp[1][0] = 0;

        while(!q.isEmpty()) {
            Node cur = q.poll();

            if(cur.no == airport) break;

            for(int i = 0; i < nodes[cur.no].size(); i++) {
                Node next = nodes[cur.no].get(i);

                int nextNo = next.no;
                int nextFee = cur.fee + next.fee;
                int nextTime = cur.time + next.time;

                if(nextFee > fee) continue;
                if(dp[nextNo][nextFee] <= nextTime) continue;

                dp[nextNo][nextFee] = nextTime;

                q.add(new Node(nextNo, nextFee, nextTime));
            }
        }
    }
}
