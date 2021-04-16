package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//BOJ 12891
public class DNAPassward {
    static int inputLength;
    static int passwardLength;
    static char[] dna = new char[] {'A', 'C', 'G', 'T'};
    static int[] passwardRule = new int[4];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        inputLength = Integer.parseInt(st.nextToken());
        passwardLength = Integer.parseInt(st.nextToken());

        String inputString = br.readLine();

        String[] rule = br.readLine().split(" ");
        for(int i=0; i<passwardRule.length; i++) {
            passwardRule[i] = Integer.parseInt(rule[i]);
        }

        System.out.println(DetectPassward(inputString));
    }

    public static int DetectPassward(String inputString) {
        int[] count = new int[85];
        int result = 0;
        for(int i=0; i<inputLength; i++) {
            count[inputString.charAt(i)]++;

            if(i<passwardLength-1) continue;
            else {
                if(i>passwardLength-1) count[inputString.charAt(i-passwardLength)]--;

                boolean flag = true;
                for(int j=0; j<4; j++) {
                    if(count[dna[j]] < passwardRule[j]) flag = false;
                }

                if(flag) result++;
            }
        }
        return result;
    }
}
