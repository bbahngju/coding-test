package BOJ;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.StringTokenizer;
import java.util.Stack;

//BOJ 10828
public class StackProgram {
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int orderNumber = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();
        for(int i=0; i<orderNumber; i++) {
            String[] order = br.readLine().split(" ");
            if (order[0].equals("push")) {
                stack.push(Integer.parseInt(order[1]));
            } else if (order[0].equals("pop")) {
                if (stack.empty()) bw.write(-1 + "\n");
                else bw.write(stack.pop() + "\n");
            } else if (order[0].equals("size")) {
                bw.write(stack.size() + "\n");
            } else if (order[0].equals("empty")) {
                if (stack.empty()) bw.write(1 + "\n");
                else bw.write(0 + "\n");
            } else if (order[0].equals("top")) {
                if (stack.empty()) bw.write(-1 + "\n");
                else bw.write(stack.peek() + "\n");
            }
        }
        bw.flush();
        br.close();
        bw.close();
    }

}
