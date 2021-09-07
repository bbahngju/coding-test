package BOJ;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

//BOJ 4195
//O(NlogN)
public class FriendNetwork {
    public static int[] root;
    public static int[] size;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int tc = Integer.parseInt(br.readLine());

        while(tc-- > 0) {
            int n = Integer.parseInt(br.readLine());

            Map<String, Integer> relation = new HashMap<>();
            root = new int[n * 2 + 1];
            size = new int[n * 2 + 1];

            for(int i = 1; i < root.length; i++) {
                root[i] = i;
                size[i] = 1;
            }

            int idx = 1;

            for(int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                String first = st.nextToken();
                String second = st.nextToken();

                if(!relation.containsKey(first)) {
                    relation.put(first, idx++);
                }
                if(!relation.containsKey(second)) {
                    relation.put(second, idx++);
                }

                int firstRoot = find(relation.get(first));
                int secondRoot = find(relation.get(second));

                if(firstRoot != secondRoot) {
                    merge(firstRoot, secondRoot);
                }

                bw.write(Math.max(size[firstRoot], size[secondRoot]) + "\n");
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

    public static int find(int idx) {
        if(root[idx] == idx)
            return idx;

        return root[idx] = find(root[idx]);
    }

    public static void merge(int f, int s) {
        if(size[f] < size[s]) {
            int tmp = f;
            f = s;
            s = tmp;
        }

        root[s] = root[f];
        size[f] += size[s];
        size[s] = 0;
    }
}
