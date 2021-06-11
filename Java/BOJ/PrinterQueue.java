package BOJ;

import java.io.*;
import java.util.*;

//BOJ 1966
//O((N*(N+1))/2)
//시뮬 30분
public class PrinterQueue {
    public static class Info {
        int idx;
        int importance;

        public Info(int idx, int importance) {
            this.idx = idx;
            this.importance = importance;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int tc = Integer.parseInt(br.readLine());
        PriorityQueue<Info> pq = new PriorityQueue<>((o1, o2) -> o2.importance - o1.importance);
        Queue<Info> q = new LinkedList<>();

        int docNum, qIdx, cnt, imp;
        while(tc > 0) {
            pq.clear();
            q.clear();
            st = new StringTokenizer(br.readLine(), " ");

            docNum = Integer.parseInt(st.nextToken());
            qIdx = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine(), " ");
            for(int i = 0; i < docNum; i++) {
                imp = Integer.parseInt(st.nextToken());
                pq.add(new Info(i, imp));
                q.add(new Info(i, imp));
            }

            cnt = 1;
            while(!q.isEmpty() && !pq.isEmpty()) {
                Info curMax = pq.peek();
                Info cur = q.poll();
                if(cur.importance < curMax.importance) {
                    q.add(cur);
                    continue;
                }

                if(cur.idx == qIdx) {
                    bw.write(cnt + "\n");
                    break;
                }
                pq.poll();
                cnt++;
            }
            tc--;
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
