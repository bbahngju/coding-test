package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//BOJ 2749
//피사노 주기 (10^(6-1) * 15)
//O(N), N is Pisano Period
public class Fibonacci3 {
    static int modular = 1000000;
    static int cycle = modular/10 * 15;

    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long number = Long.parseLong(br.readLine());

        int[] mem = new int[cycle];
        mem[0] = 0;
        mem[1] = 1;

        int answer = Fib(number, mem);
        System.out.println(answer);
    }

    public static int Fib(long n, int[] mem) {
        for(int i = 2; i< cycle; i++) {
            mem[i] = (mem[i-1] + mem[i-2])%modular;
        }

        return mem[(int)(n%cycle)];
    }
}
