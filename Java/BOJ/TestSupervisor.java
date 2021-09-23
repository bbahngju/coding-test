package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//BOJ 13458
//O(N)
//수학
public class TestSupervisor {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int room;
        int[] students;
        int general, deputy;
        long answer = 0L;

        room = Integer.parseInt(br.readLine());
        students = new int[room];

        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < room; i++) {
            students[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine(), " ");
        general = Integer.parseInt(st.nextToken());
        deputy = Integer.parseInt(st.nextToken());

        answer += room;
        for(int student : students) {
            int quo, rem, deputyStudent;

            deputyStudent = student - general;

            if(deputyStudent <= 0) {
                continue;
            }

            quo = deputyStudent / deputy;
            rem = deputyStudent % deputy;

            answer += quo;
            if(rem != 0) {
                answer++;
            }
        }

        System.out.println(answer);
    }
}
