package BOJ;

import java.io.*;
import java.util.StringTokenizer;

//BOJ 1244
//시뮬, 32분
public class SwitchOnOff {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] flag = new int[N+1];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 1; i <= N; i++) {
            flag[i] = Integer.parseInt(st.nextToken());
        }
        int students = Integer.parseInt(br.readLine());

        while(students > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            int sex = Integer.parseInt(st.nextToken());
            int number = Integer.parseInt(st.nextToken());

            if(isBoy(sex)) {
                boyRule(number, flag, N);
            }
            else {
                girlRule(number, flag, N);
            }
            students--;
        }

        for(int i = 1; i < flag.length; i++) {
            bw.write(flag[i] + " ");
            if(i%20 == 0) {
                bw.newLine();
            }
        }
        bw.flush();
        bw.close();
    }

    private static boolean isBoy(int sex) {
        return sex == 1;
    }

    private static void boyRule(int num, int[] flag, int n) {
        for(int i = num; i <= n; i += num) {
            flag[i] = (flag[i] + 1) % 2;
        }
    }

    private static void girlRule(int num, int[] flag, int n) {
        int[] range = findRange(num, flag, n);
        int left = range[0];
        int right = range[1];

        for(int i = left; i <= right; i++) {
            flag[i] = (flag[i] + 1) % 2;
        }
    }

    private static int[] findRange(int num, int[] flag, int n) {
        int left = num - 1;
        int right = num + 1;

        while(left > 0 && right <= n) {
            if(flag[left] != flag[right])
                break;

            left--;
            right++;
        }

        return new int[]{left+1, right-1};
    }
}