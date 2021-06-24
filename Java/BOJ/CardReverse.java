package BOJ;

import java.io.*;
import java.util.StringTokenizer;

//BOJ 10804
//시뮬, 10분
//O(100)
public class CardReverse {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int[] cards = new int[20];
        for(int i = 0; i < 20; i++) {
            cards[i] = i+1;
        }

        int left, right, tmp;
        for(int i = 0; i < 10; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            left = Integer.parseInt(st.nextToken()) - 1;
            right = Integer.parseInt(st.nextToken()) - 1;

            while(left < right) {
                tmp = cards[left];
                cards[left] = cards[right];
                cards[right] = tmp;

                left++;
                right--;
            }
        }

        for(int card : cards)
            bw.write(card + " ");
        bw.write("\n");

        bw.flush();
        bw.close();
        br.close();
    }
}
