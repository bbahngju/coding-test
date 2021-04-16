package BOJ;

import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

//BOJ 10866
public class DequeProgram {
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        LinkedList<Integer> deque = new LinkedList<>();

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            switch (st.nextToken()) {
                case "push_front":
                    deque.addFirst(Integer.parseInt(st.nextToken()));
                    break;

                case "push_back":
                    deque.addLast(Integer.parseInt(st.nextToken()));
                    break;

                case "pop_front":
                    if(deque.isEmpty()) bw.write("-1\n");
                    else bw.write(deque.pollFirst() + "\n");
                    break;

                case "pop_back":
                    if(deque.isEmpty()) bw.write("-1\n");
                    else bw.write(deque.pollLast() + "\n");
                    break;

                case "size":
                    bw.write(deque.size() + "\n");
                    break;

                case "empty":
                    if(deque.isEmpty()) bw.write("1\n");
                    else
                        bw.write("0\n");
                    break;

                case "front":
                    if(deque.isEmpty()) bw.write("-1\n");
                    else bw.write(deque.peekFirst()+"\n");
                    break;

                default:
                    if(deque.isEmpty()) bw.write("-1\n");
                    else bw.write(deque.peekLast()+"\n");
                    break;
            }
        }
        bw.flush();
        br.close();
        bw.close();
    }
}
