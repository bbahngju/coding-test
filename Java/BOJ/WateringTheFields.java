package BOJ;

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//BOJ 10021
//O(2003000)
//MST(크루스칼), Union-Find
//Union 함수에서 루트 노드를 바꿔야 했었는데 현재 노드를 바꾸는 실수를 범함
public class WateringTheFields {
    static int[] parents;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int[][] points = new int[N][2];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            points[i][0] = x; points[i][1] = y;
        }

        PriorityQueue<Node> nodes = new PriorityQueue<>();

        for(int i = 0; i < N; i++) {
            for(int j = i+1; j < N; j++) {
                int cost = (int)(Math.pow(points[i][0] - points[j][0], 2)
                        + Math.pow(points[i][1] - points[j][1], 2));
                if(cost < C) continue;
                nodes.offer(new Node(i, j, cost));
            }
        }

        parents = new int[N];
        for(int i = 0; i < N; i++) {
            parents[i] = i;
        }

        int answer = 0;
        int edge = 0;
        while(!nodes.isEmpty()) {
            Node node = nodes.poll();
            if(find(node.start) == find(node.end)) continue;
            answer += node.cost;
            edge++;
            union(node.start, node.end);
            if(edge == N-1) break;
        }

        if(edge == N-1) {
            bw.write(answer + "\n");
        } else {
            bw.write("-1\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static class Node implements Comparable<Node>{
        int start;
        int end;
        int cost;

        public Node(int s, int e, int c) {
            this.start = s;
            this.end = e;
            this.cost = c;
        }

        public int compareTo(Node n) {
            return this.cost > n.cost ? 1 : -1;
        }
    }

    private static int find(int node) {
        if(parents[node] == node) return node;

        return find(parents[node]);
    }

    private static void union(int start, int end) {
        int startParents = find(start);
        int endParents = find(end);

        if(startParents < endParents) {
            parents[endParents] = startParents;
        } else {
            parents[startParents] = endParents;
        }
    }
}
