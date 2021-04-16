package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//BOJ 2003
public class SumOfSequence {
    static int[] sequence;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int sequenceCount = Integer.parseInt(st.nextToken());
        int target = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        sequence = new int[sequenceCount];
        for(int i=0; i<sequenceCount; i++) {
            sequence[i] = Integer.parseInt(st.nextToken());
        }

        int result = matchTarget(target);
        System.out.println(result);
    }

    public static int matchTarget(int target){
        int left = 0;
        int right = 1;

        int curTotal = 0;
        int count = 0;

        curTotal += sequence[0];
        while(left <= right) {
            if(curTotal == target) {
                count++;
                curTotal -= sequence[left];
                left++;
            }
            else if(curTotal < target) {
                if(right >= sequence.length) break;

                curTotal += sequence[right];
                right++;
            }
            else {
                curTotal -= sequence[left];
                left++;
            }
        }

        return count;
    }
}
