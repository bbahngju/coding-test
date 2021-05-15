package BOJ;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

//BOJ 7453
//
public class FourIntegersThatSumToZero {
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int[] one = new int[n];
        int[] two = new int[n];
        int[] three = new int[n];
        int[] four = new int[n];
        int[] mergeOne = new int[n*n];
        int[] mergeTwo = new int[n*n];
        long answer = 0;

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            one[i] = Integer.parseInt(st.nextToken());
            two[i] = Integer.parseInt(st.nextToken());
            three[i] = Integer.parseInt(st.nextToken());
            four[i] = Integer.parseInt(st.nextToken());
        }

        int idx = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                mergeOne[idx] = one[i] + two[j];
                mergeTwo[idx] = three[i] + four[j];
                idx++;
            }
        }

        Arrays.sort(mergeOne);

        for(int value : mergeTwo) {
            int up = upper(mergeOne, -value);
            int low = lower(mergeOne, -value);

            answer += up - low;
        }

        bw.write(answer + "\n");

        br.close();
        bw.flush();
        bw.close();

    }

    public static int upper(int[] merge, int value) {
        int left = 0, right = merge.length;
        int mid;

        while(left < right) {
            mid = (left+right)/2;

            if(merge[mid] <= value) left = mid + 1;
            else right = mid;
        }

        return left;
    }

    public static int lower(int[] merge, int value) {
        int left = 0, right = merge.length;
        int mid;

        while(left < right) {
            mid = (left+right)/2;

            if(merge[mid] < value) left = mid + 1;
            else right = mid;
        }

        return left;
    }
}
