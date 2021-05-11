package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//BOJ 1421
//50*10000
public class WoodcutterIdasom {
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int woodCnt = Integer.parseInt(st.nextToken());
        int cutPrice = Integer.parseInt(st.nextToken());
        int sellPrice = Integer.parseInt(st.nextToken());
        long maximum = 0;
        int[] wood = new int[woodCnt];
        int maxSize = 0;
        int cutCnt, sellCnt;
        long tmp, total;

        for(int i = 0; i < woodCnt; i++) {
            wood[i] = Integer.parseInt(br.readLine());
            maxSize = Math.max(maxSize, wood[i]);
        }

        for(int i = 1; i <= maxSize; i++) {
            total = 0;
            for(int j = 0; j < woodCnt; j++) {
                sellCnt = wood[j] / i;
                cutCnt = (wood[j] % i == 0) ? sellCnt-1 : sellCnt;

                tmp = ((long) sellCnt * sellPrice * i) - ((long) cutCnt * cutPrice);
                if(tmp > 0)
                    total += tmp;
            }
            maximum = Math.max(maximum, total);
        }

        System.out.println(maximum);
    }

}
