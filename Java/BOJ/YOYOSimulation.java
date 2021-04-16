package BOJ;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

//BOJ 19636
public class YOYOSimulation {
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        int W0 = Integer.parseInt(st.nextToken());
        int I0 = Integer.parseInt(st.nextToken());
        int B = I0;
        int T = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        int D = Integer.parseInt(st.nextToken());
        int I = Integer.parseInt(st.nextToken());
        int A = Integer.parseInt(st.nextToken());

        int W = W0;

        int noChange = W + (I-(B+A)) * D;
        if(noChange <= 0) bw.write("Danger Diet\n");
        else{
            bw.write(noChange + " " + B + "\n");
        }

        int tmp;
        boolean check = false;
        for(int i=0; i<D; i++) {
            tmp = I-(B+A);
            W += tmp;
            if(Math.abs(tmp) > T) {
                B += (int)Math.floor((double)tmp/2);
            }
            if(W<=0 || B<=0) {
                bw.write("Danger Diet\n");
                check = true;
                break;
            }
        }

        if(!check) {
            if((I0-B) > 0) bw.write(W + " " + B + " YOYO\n");
            else{
                bw.write(W + " " + B + " NO\n");
            }
        }

        bw.flush();
        br.close();
        bw.close();
    }

}
