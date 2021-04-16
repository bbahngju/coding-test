package BOJ;

import java.io.*;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//BOJ 19638
public class SentiandtheMagicHammer {
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int senti = Integer.parseInt(st.nextToken());
        int hammer = Integer.parseInt(st.nextToken());

        PriorityQueue<Integer> giant = new PriorityQueue<>(Collections.reverseOrder());
        for(int i=0; i<N; i++) {
            giant.add(Integer.parseInt(br.readLine()));
        }

        int result = Magic(hammer, senti, giant);
        if(result == -1) bw.write("NO\n" + giant.peek());
        else {
            bw.write("YES\n" + result);
        }

        bw.flush();
        br.close();
        bw.close();
    }
    public static int Magic(int hammer, int senti, PriorityQueue<Integer> giant) {
        int using = 0;
        int height;

        while(using<hammer) {
            height = giant.poll();

            if(height < senti) return using;
            if(height == 1) {
                giant.add(height);
                return -1;
            }

            height = (int)Math.floor((double)height/2);
            using++;
            giant.add(height);
        }

        if(giant.peek() < senti) return using;
        else return -1;
    }
}
