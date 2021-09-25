package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//BOJ 14501
public class Leave {
    static int n;
    static int[] days;
    static int[] pays;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n  = Integer.parseInt(br.readLine())-1;

        days = new int[n+1];
        pays = new int[n+1];
        for(int i = 0; i <= n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int day = Integer.parseInt(st.nextToken());
            int pay = Integer.parseInt(st.nextToken());

            days[i] = day;
            pays[i] = pay;
        }

        for(int i = 0; i <= n; i++) {
            int next = i + days[i];
            if(next > n + 1) {
                continue;
            }
            dfs(next, pays[i]);
        }

        System.out.println(answer);
    }

    public static void dfs(int day, int pay) {
        answer = Math.max(answer, pay);

        int plus = 0;
        for(int i = day; i <= n; i++) {
            int next = day + days[i] + plus;

            if(next > n + 1) {
                plus++;
                continue;
            }
            dfs(next, pay + pays[i]);
            plus++;
        }
    }
}
