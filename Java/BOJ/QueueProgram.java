package BOJ;

import java.io.*;
import java.util.StringTokenizer;

//BOJ 10845
public class QueueProgram {
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[] queue = new int[N+1];
        int front = 0, end = 0;

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            switch (st.nextToken()) {
                case "push":
                    queue[end] = Integer.parseInt(st.nextToken());
                    end++;
                    break;
                case "pop":
                    if(front==end) bw.write("-1\n");
                    else {
                        bw.write(queue[front] + "\n");
                        front++;
                    }
                    break;
                case "size":
                    bw.write(end-front+"\n");
                    break;
                case "empty":
                    if(front==end) bw.write("1\n");
                    else
                        bw.write("0\n");
                    break;
                case "front":
                    if(front==end) bw.write("-1\n");
                    else bw.write(queue[front]+"\n");
                    break;
                default:
                    if(front==end) bw.write("-1\n");
                    else bw.write(queue[end-1]+"\n");
                    break;
            }
        }

        bw.flush();
        br.close();
        bw.close();
    }
}
