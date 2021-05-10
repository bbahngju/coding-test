package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//BOJ 9466
//
public class TermProject {
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int tc = Integer.parseInt(br.readLine());
        int cnt, answer;
        int[] students;
        int[] visited;
        int[] sequence;
        while(tc > 0) {
            cnt = Integer.parseInt(br.readLine());
            answer = 0;
            students = new int[cnt];
            visited = new int[cnt];
            sequence = new int[cnt];
            Arrays.fill(visited, 0);
            st = new StringTokenizer(br.readLine(), " ");

            for(int i = 0; i < cnt; i++) {
                students[i] = Integer.parseInt(st.nextToken())-1;
            }

            for(int i = 0; i < cnt; i++) {
                if(visited[i] == 0) {
                    visited[i] = i+1;
                    sequence[i] = 1;
                    answer += Search(students, visited, sequence, i+1, i);
                }
            }

            System.out.println(cnt - answer);
            tc--;
        }
    }

    public static int Search(int[] students, int[] visited, int[] sequence, int c, int idx) {
        int nextSeq = sequence[idx]+1;
        int next;
        while(true) {
            next = students[idx];
            if(visited[next] != 0) {
                if(visited[next] != c) {
                    return 0;
                }

                return nextSeq - sequence[next];

            }

            visited[next] = c;
            sequence[next] = nextSeq;
            idx = next;
            nextSeq++;
        }
    }
}
