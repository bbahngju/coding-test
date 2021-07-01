package BOJ;

import java.io.*;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//BOJ 17140
//시뮬
public class TwoDimensionalArraysAndOperations {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int row = Integer.parseInt(st.nextToken()) - 1;
        int col = Integer.parseInt(st.nextToken()) - 1;
        int value = Integer.parseInt(st.nextToken());

        int[][] arr = new int[100][100];
        PriorityQueue<Info> pq = new PriorityQueue<>((o1, o2) -> {
            if(o1.cnt == o2.cnt) {
                return o1.num - o2.num;
            }
            return o1.cnt - o2.cnt;
        });
        HashMap<Integer, Integer> count = new HashMap<>();

        for(int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int second = 0;
        int maxRow = 3; int maxCol = 3;
        int nextMax;
        while(arr[row][col] != value && second <= 100) {
            nextMax = 0;
            if(maxRow >= maxCol) {
                for(int i = 0; i < maxRow; i++) {
                    for(int j = 0; j < maxCol; j++) {
                        if(arr[i][j] == 0) continue;
                        count.put(arr[i][j], count.getOrDefault(arr[i][j], 0) + 1);
                    }

                    for(int key : count.keySet()) {
                        int v = count.get(key);
                        pq.add(new Info(key, v));
                    }

                    nextMax = Math.max(nextMax, pq.size() * 2);

                    for(int j = 0; j < 100; j += 2) {
                        if(pq.isEmpty()) {
                            arr[i][j] = 0;
                            arr[i][j+1] = 0;
                            continue;
                        }

                        Info cur = pq.poll();
                        arr[i][j] = cur.num;
                        arr[i][j+1] = cur.cnt;
                    }

                    count.clear();
                    pq.clear();
                }

                maxCol = Math.min(nextMax, 100);
            }


            else {
                for(int i = 0; i < maxCol; i++) {
                    for(int j = 0; j < maxRow; j++) {
                        if(arr[j][i] == 0) continue;
                        count.put(arr[j][i], count.getOrDefault(arr[j][i], 0) + 1);
                    }

                    for(int key : count.keySet()) {
                        int v = count.get(key);
                        pq.add(new Info(key, v));
                    }

                    nextMax = Math.max(nextMax, pq.size() * 2);

                    for(int j = 0; j < 100; j += 2) {
                        if(pq.isEmpty()) {
                            arr[j][i] = 0;
                            arr[j+1][i] = 0;
                            continue;
                        }

                        Info cur = pq.poll();
                        arr[j][i] = cur.num;
                        arr[j+1][i] = cur.cnt;
                    }

                    count.clear();
                    pq.clear();
                }

                maxRow = Math.min(nextMax, 100);
            }

            second++;
        }

        if(second > 100) second = -1;
        bw.write(second + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    public static class Info {
        int num;
        int cnt;

        public Info(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
    }
}
