package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//BOJ 1629
//O(logN)
public class Multiplication {
    public static long FastPower(int a, int b, int c) {
        if(b == 0) return 1;

        long tmp = FastPower(a, b/2, c);
        long result = tmp*tmp % c;

        if(b%2==1) result = result * a % c;

        return result;
    }

    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        System.out.println(FastPower(A, B, C));
    }
}
