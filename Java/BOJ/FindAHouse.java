package BOJ;

import java.io.*;
import java.util.*;

//BOJ 13911
public class FindAHouse {
    public static class Edge {
        int vertex;
        int weight;

        public Edge(int v, int w) {
            this.vertex = v;
            this.weight = w;
        }
    }

    static PriorityQueue<Edge> pq = new PriorityQueue<>(new Comparator<Edge>() {
        @Override
        public int compare(Edge o1, Edge o2) {
            if(o1.weight < o2.weight) return -1;
            else if(o1.weight == o2.weight) return 0;
            else return 1;
        }
    });
    static int vertexNumber;
    static ArrayList<Edge>[] graph;
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int INF = Integer.MAX_VALUE;
        vertexNumber = Integer.parseInt(st.nextToken());
        int edgeNumber = Integer.parseInt(st.nextToken());

        graph = new ArrayList[vertexNumber+1];
        for(int i=0; i< graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        int u, v, w;
        for(int i=1; i<=edgeNumber; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            graph[u].add(new Edge(v, w));
            graph[v].add(new Edge(u, w));
        }

        st = new StringTokenizer(br.readLine(), " ");
        int macNumber = Integer.parseInt(st.nextToken());
        int macLimit = Integer.parseInt(st.nextToken());

        int[] macMin = new int[vertexNumber+1];
        Arrays.fill(macMin, INF);

        st = new StringTokenizer(br.readLine(), " ");
        while(st.hasMoreTokens()) {
            int location = Integer.parseInt(st.nextToken());
            macMin[location] = 0;
            pq.add(new Edge(location, 0));
        }
        FindMinimum(macMin);

        st = new StringTokenizer(br.readLine(), " ");
        int starNumber = Integer.parseInt(st.nextToken());
        int starLimit = Integer.parseInt(st.nextToken());

        int[] starMin = new int[vertexNumber+2];
        Arrays.fill(starMin, INF);

        st = new StringTokenizer(br.readLine());
        while(st.hasMoreTokens()) {
            int location = Integer.parseInt(st.nextToken());
            starMin[location] = 0;
            pq.add(new Edge(location, 0));
        }
        FindMinimum(starMin);

        int minTotal = -1;
        for(int i=1; i<=vertexNumber; i++) {
            if(macMin[i] > 0 && macMin[i] <= macLimit && starMin[i] > 0 && starMin[i] <= starLimit) {
                int total = macMin[i]+starMin[i];
                if(minTotal == -1) {
                    minTotal = total;
                }
                else if(minTotal > total) {
                    minTotal = total;
                }
            }
        }

        bw.write(minTotal + "\n");
        bw.flush();
        br.close();
        bw.close();
    }

    public static void FindMinimum(int[] distance) {
        while(!pq.isEmpty()) {
            Edge edge = pq.poll();
            for(int i=0; i<graph[edge.vertex].size(); i++) {
                Edge cur = graph[edge.vertex].get(i);
                if(distance[cur.vertex] > edge.weight + cur.weight) {
                    distance[cur.vertex] = edge.weight + cur.weight;
                    pq.add(new Edge(cur.vertex, distance[cur.vertex]));
                }
            }
        }
    }
}
