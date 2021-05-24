package BOJ;

import java.io.*;
import java.util.StringTokenizer;

//BOJ 1072
//O(logN)
public class Game {
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int play = Integer.parseInt(st.nextToken());
        int win = Integer.parseInt(st.nextToken());
        int percent = (int)(win * 100.0 / play);

        if(percent >= 99) bw.write("-1\n");
        else bw.write(binarySearch(play, win, percent) + "\n");

        bw.flush();
        bw.close();
        br.close();
    }

    public static int binarySearch(int play, int win, int percent) {
        int start = 0;
        int end = 1000000001;

        int mid, win_n, play_n, percent_n;
        while(start <= end) {
            mid = (start + end) / 2;
            win_n = win + mid;
            play_n = play + mid;
            percent_n = (int)(win_n * 100.0 / play_n);

            if(percent_n > percent) {
                end = mid - 1;
            }
            else {
                start = mid + 1;
            }
        }

        return start;
    }
}
