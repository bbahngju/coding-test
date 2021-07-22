package BOJ;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


//BOJ 15591
//그래프 - BFS
//O(Q*(N+N-1))
public class MooTube {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        ArrayList<int[]>[] nodes = new ArrayList[N+1];
        for(int i = 1; i <= N; i++) {
            nodes[i] = new ArrayList<>();
        }

        for(int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            nodes[p].add(new int[]{q, r});
            nodes[q].add(new int[]{p, r});
        }

        for(int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int K = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());

            bw.write(recommandVideo(start, K, nodes, N) + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static int recommandVideo(int start, int K, ArrayList<int[]>[] nodes, int N) {
        Queue<Integer> next = new LinkedList<>();
        boolean[] visited = new boolean[N+1];
        int answer = 0;

        next.offer(start);
        visited[start] = true;

        while(!next.isEmpty()) {
            int nodeNumber = next.poll();
            for(int[] node : nodes[nodeNumber]) {
                if(!visited[node[0]] && node[1] >= K) {
                    next.offer(node[0]);
                    visited[node[0]] = true;
                    answer++;
                }
            }
        }

        return answer;
    }
}
