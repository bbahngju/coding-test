package BOJ;

import java.io.*;
import java.util.StringTokenizer;

//BOJ 2290
//시뮬, 43분
public class LCDTest {
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        int s = Integer.parseInt(st.nextToken());
        char[] num = st.nextToken().toCharArray();
        int height = 2 * s + 3;
        int width = s + 2;

        for(int i = 0; i < height; i++) {
            StringBuilder answer = new StringBuilder();

            for(char n : num) {
                for(int k = 0; k < width; k++) {
                    if(i == 0) {
                        if(k == 0 || k == width-1 || n == '1' || n == '4') {
                            answer.append(" ");
                            continue;
                        }
                        answer.append("-");
                    }
                    else if(i == (height/2)) {
                        if(k == 0 || k == width-1 || n == '1' || n == '7' || n == '0') {
                            answer.append(" ");
                            continue;
                        }
                        answer.append("-");
                    }
                    else if(i == height -1) {
                        if(k == 0 || k == width-1 || n == '1' || n == '4' || n == '7') {
                            answer.append(" ");
                            continue;
                        }
                        answer.append("-");
                    }
                    else {
                        if(i < (height/2)) {
                            if(k == 0 && (n == '4' || n == '5' || n == '6' || n == '8' || n == '9' || n == '0')) {
                                answer.append("|");
                                continue;
                            }
                            if(k == width-1 && (n == '1' || n == '2' || n == '3' || n == '4' || n == '7' || n == '8' || n == '9' || n == '0')) {
                                answer.append("|");
                                continue;
                            }
                            answer.append(" ");
                        }
                        else if(i > (height/2)) {
                            if(k == 0 && (n == '2' || n == '6' || n == '8' || n == '0')) {
                                answer.append("|");
                                continue;
                            }
                            if(k == width-1 && (n == '1' || n == '3' || n == '4' || n == '5' || n == '6' || n == '7' || n == '8' || n == '9' || n == '0')) {
                                answer.append("|");
                                continue;
                            }
                            answer.append(" ");
                        }
                    }

                }
                answer.append(" ");
            }

            bw.write(answer + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }


}
