package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//BOJ 10448
//DFS, 순열
public class Eureka {
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());

        int[] triNum = new int[41];
        boolean[] num = new boolean[1001];

        makeTriNum(triNum);
        availableTriNum(triNum, num, 0, 0, 1);

        while(tc > 0) {
            int input = Integer.parseInt(br.readLine());
            if(num[input]) System.out.println(1);
            else System.out.println(0);
            tc--;
        }
    }

    public static void makeTriNum(int[] triNum) {
        triNum[0] = 0;
        for(int i = 1; i < triNum.length; i++) {
            triNum[i] = triNum[i-1]+i;
        }
    }

    public static void availableTriNum(int[] triNum, boolean[] num, int tot, int cnt, int start) {
        if(tot > 1000) return;
        if(cnt == 3) {
            num[tot] = true;
            return;
        }

        for(int i = start; i < triNum.length; i++) {
            availableTriNum(triNum, num, tot+triNum[i], cnt+1, i);
        }

    }
}
