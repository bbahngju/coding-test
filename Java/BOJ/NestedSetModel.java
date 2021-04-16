package BOJ;

import java.io.*;
import java.util.*;

//BOJ 19641
public class NestedSetModel {
    public static class Node {
        int left;
        int right;

        public Node(int l, int r) {
            this.left = l;
            this.right = r;
        }
    }

    static boolean[] visited;
    static ArrayList<Integer>[] graph;
    static ArrayList<Node>[] result;
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int nodeCount = Integer.parseInt(br.readLine());
        graph = new ArrayList[nodeCount+1];
        for(int j=0; j<graph.length; j++)
            graph[j] = new ArrayList<>();

        result = new ArrayList[nodeCount+1];
        for(int i=0; i<result.length; i++)
            result[i] = new ArrayList<>();

        for(int i=0; i<nodeCount; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int curNode = Integer.parseInt(st.nextToken());
            while(true) {
                int edge = Integer.parseInt(st.nextToken());
                if(edge == -1) break;

                graph[curNode].add(edge);
            }
        }

        int root = Integer.parseInt(br.readLine());
        visited = new boolean[nodeCount+1];
        dfs(root, 1);

        for(int i=1; i<=nodeCount; i++) {
            bw.write(i + " " + result[i].get(0).left + " " + result[i].get(0).right + "\n");
        }

        bw.flush();
        br.close();
        bw.close();
    }

    static int right = 1;
    public static void dfs(int cur, int left) {
        visited[cur] = true;

        Collections.sort(graph[cur]);
        for(int n : graph[cur]){
            if(!visited[n]) dfs(n, ++right);
        }
        result[cur].add(new Node(left, ++right));
    }
}
