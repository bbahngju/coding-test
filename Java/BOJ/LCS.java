package BOJ;

import java.io.*;

//BOJ 9251
//O(NM)
public class LCS {
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        char[] first = br.readLine().toCharArray();
        char[] second = br.readLine().toCharArray();

        int ans;
        if(first.length >= second.length){
            ans = dynamicProgramming(first, second);
        }

        else {
            ans = dynamicProgramming(second, first);
        }

        bw.write(ans + "\n");

        bw.flush();
        bw.close();
        br.close();
    }

    public static int dynamicProgramming(char[] l, char[] s) {
        int[][] answer = new int[s.length+1][l.length+1];

        for(int i = 1; i <= s.length; i++) {
            for(int j = 1; j <= l.length; j++) {
                if(s[i-1] == l[j-1])
                    answer[i][j] = answer[i-1][j-1] + 1;
                else
                    answer[i][j] = Math.max(answer[i-1][j], answer[i][j-1]);
            }
        }

        return answer[s.length][l.length];
    }
}
