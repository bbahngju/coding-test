package Contest;

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
//대여 시간을 추천해드립니다
class scofe2021_1 {
    public static class Times implements Comparable<Times>{
        int startTime;
        int endTime;

        public Times(int s, int e) {
            this.startTime = s;
            this.endTime = e;
        }

        @Override
        public int compareTo(Times o) {
            return (Integer.compare(this.endTime, o.endTime));
        }

    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());

        PriorityQueue<Times> pq = new PriorityQueue<>();
        for(int i=0; i<N; i++) {
            String[] input = br.readLine().split("");
            int startTime = (Integer.parseInt(input[0])*10 + Integer.parseInt(input[1]))*60
                    + (Integer.parseInt(input[3])*10 + Integer.parseInt(input[4]));
            int endTime = (Integer.parseInt(input[8])*10 + Integer.parseInt(input[9]))*60
                    + (Integer.parseInt(input[11])*10 + Integer.parseInt(input[12]));

            pq.add(new Times(startTime, endTime));
        }

        Times curTime = pq.poll();
        int minEnd = curTime.endTime;
        int maxStartTime = curTime.startTime;
        int cnt = 1;
        while(!pq.isEmpty()) {
            curTime = pq.poll();
            if(curTime.startTime <= minEnd && curTime.endTime >= minEnd) cnt++;
            maxStartTime = Math.max(maxStartTime, curTime.startTime);
        }

        if(cnt == N) {
            System.out.printf("%02d:%02d ~ %02d:%02d", maxStartTime/60, maxStartTime%60, minEnd/60, minEnd%60);
        }
        else System.out.println(-1);
    }
}