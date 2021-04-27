package BOJ;

import java.io.*;
import java.util.*;

//BOJ 1655
//O(NlogN)
public class TellMeTheMiddleValue {
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int leftTmp, rightTmp;
        PriorityQueue<Integer> leftPq = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> rightPq = new PriorityQueue<>();

        for(int i=0; i<N; i++) {
            if(leftPq.size() == rightPq.size()) {
                leftPq.add(Integer.parseInt(br.readLine()));
            }
            else rightPq.add(Integer.parseInt(br.readLine()));

            if(!leftPq.isEmpty() && !rightPq.isEmpty()) {
                if(leftPq.peek() > rightPq.peek()) {
                    leftTmp = leftPq.poll();
                    rightTmp = rightPq.poll();
                    leftPq.add(rightTmp);
                    rightPq.add(leftTmp);
                }
            }

            bw.write(leftPq.peek() + "\n");
        }
        bw.flush();
        br.close();
        bw.close();
    }
}
