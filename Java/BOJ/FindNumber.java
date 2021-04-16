package BOJ;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

//BOJ 1920
public class FindNumber {
    static int N;
    static int[] addNumber;
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());

        addNumber = new int[N];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<N; i++) {
            addNumber[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(addNumber);

        st = new StringTokenizer(br.readLine(), " ");
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        while(st.hasMoreTokens()) {
            int curNumber = Integer.parseInt(st.nextToken());
            boolean result = CheckNumber(curNumber);

            if(result) bw.write(1+"\n");
            else bw.write(0+"\n");
        }

        bw.flush();
        br.close();
        bw.close();
    }

    public static boolean CheckNumber(int n) {
        int left = 0;
        int right = N - 1;

        while(left<=right) {
            int mid = (left+right) / 2;
            if(addNumber[mid] == n) return true;
            else{
                if(addNumber[mid] < n) left = mid + 1;
                else right = mid - 1;
            }
        }

        return false;
    }

}
