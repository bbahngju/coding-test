import java.io.*;
import java.util.*;

public class DualPriorityQueue {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static TreeMap<Long, Integer> priority = new TreeMap<>();
    public static void main(String[] arg) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for(int i=0; i<T; i++) {
            int N = Integer.parseInt(br.readLine());
            Play(N);

            if(priority.isEmpty()) bw.write("EMPTY\n");
            else bw.write(priority.lastKey() + " " + priority.firstKey() + "\n");
        }
        bw.flush();
        br.close();
        bw.close();
    }

    public static void Play(int N) throws IOException{
        priority.clear();
        long value;
        int del;
        for(int i=0; i<N; i++) {
            String[] input = br.readLine().split(" ");

            if (input[0].equals("I")) {
                value = Long.parseLong(input[1]);
                if (priority.containsKey(value))
                    priority.put(value, priority.get(value) + 1);
                else priority.put(value, 1);
            } else {
                if (priority.isEmpty()) continue;

                del = Integer.parseInt(input[1]);
                if (del == 1) {
                    if (priority.get(priority.lastKey()) - 1 == 0) priority.remove(priority.lastKey());
                    else priority.put(priority.lastKey(), priority.get(priority.lastKey()) - 1);
                } else {
                    if (priority.get(priority.firstKey()) - 1 == 0) priority.remove(priority.firstKey());
                    else priority.put(priority.firstKey(), priority.get(priority.firstKey()) - 1);
                }
            }
        }
    }
}
