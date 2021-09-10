package BOJ;

import java.io.*;

//BOJ 8958
//O(N)
public class OXQuiz {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringBuilder sb = new StringBuilder();
        int tc = Integer.parseInt(br.readLine());

        while(tc-- > 0) {
            char[] board = br.readLine().toCharArray();

            int cur = 0;
            int total = 0;
            for(char b : board) {
                if(b == 'O') {
                    total += ++cur;
                    continue;
                }

                cur = 0;
            }
            sb.append(total).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
