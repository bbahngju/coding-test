package BOJ;

import java.io.*;
import java.util.Arrays;

//BOJ 16113
//O(N+(N/5))
//시뮬, 82분
public class Signal {
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int col = n/5;
        char[] input = br.readLine().toCharArray();
        char[][] signal = new char[5][col];
        int[] result = new int[col];
        StringBuilder answer = new StringBuilder();

        int idx = 0;
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < col; j++) {
                signal[i][j] = input[idx++];
            }
        }

        int total;
        for(int i = 0; i < col; i++) {
            total = 0;

            for(int k = 0; k < 5; k++) {
                if(signal[k][i] == '.') total -= k;
                else total += k;
            }

            result[i] = total;
        }

        int[] stored = new int[3];
        int j = 0;
        for(int i = 0; i < col; i++) {
            if(result[i] == -10 && j == 0 && signal[0][i] == '.')
                continue;
            if(j == 1 && stored[0] == 10 && result[i] == -10) {
                answer.append("1");
                j = 0;
                continue;
            }
            stored[j] = result[i];

            if(i == col-1 && j == 0 && result[i] != -10) {
                answer.append("1");
            }
            if(j != 2) {
                j++;
                continue;
            }

            if(stored[0] ==  10 && stored[1] == -2 && stored[2] == 10) answer.append("0");
            else if(stored[0] == 8 && stored[1] == 2 && stored[2] == 4) answer.append("2");
            else if(stored[0] == 2 && stored[1] == 2 && stored[2] == 10) answer.append("3");
            else if(stored[0] == -4 && stored[1] == -6 && stored[2] == 10) answer.append("4");
            else if(stored[0] == 4 && stored[1] == 2 && stored[2] == 8) answer.append("5");
            else if(stored[0] == 10 && stored[1] == 2 && stored[2] == 8) answer.append("6");
            else if(stored[0] == -10 && stored[1] == -10 && stored[2] == 10) answer.append("7");
            else if(stored[0] == 10 && stored[1] == 2 && stored[2] == 10) answer.append("8");
            else if(stored[0] == 4 && stored[1] == 2 && stored[2] == 10) answer.append("9");

            j = 0;
            Arrays.fill(stored, 0);
        }

        bw.write(answer + "\n");

        bw.flush();
        bw.close();
        br.close();
    }
}
