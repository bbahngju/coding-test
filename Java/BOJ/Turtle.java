package BOJ;

import java.io.*;

//BOJ 8911
//O(N)
//시뮬, 24분
public class Turtle {
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int tc = Integer.parseInt(br.readLine());

        String move;
        int[] dx = {0, -1, 0, 1};
        int[] dy = {1, 0, -1, 0};

        int curX, curY, direct;
        int left, right, up, down;
        char op;
        while(tc > 0) {
            move = br.readLine();
            curX = 0; curY = 0; direct = 0;
            left = 0; right = 0; up = 0; down = 0;

            for(int i = 0; i < move.length(); i++) {
                op = move.charAt(i);
                if('F' == op) {
                    curX += dx[direct];
                    curY += dy[direct];
                 }
                else if('B' == op) {
                    curX -= dx[direct];
                    curY -= dy[direct];
                }
                else if('L' == op) {
                    direct = (direct + 1) % 4;
                }
                else if('R' == op) {
                    direct = (direct - 1 < 0) ? 3 : direct - 1;
                }

                if(curX < 0) {
                    left = Math.min(left, curX);
                }
                else {
                    right = Math.max(right, curX);
                }

                if(curY < 0) {
                    down = Math.min(down, curY);
                }
                else {
                    up = Math.max(up, curY);
                }
            }

            int answer = (right - left) * (up - down);
            bw.write(answer + "\n");
            tc--;
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
