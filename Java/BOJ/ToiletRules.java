package BOJ;

import java.io.*;
import java.util.*;

//BOJ 19640
public class ToiletRules {
    public static class Person implements Comparable<Person> {
        int day;
        int hurry;
        int line;
        int idx;

        public Person(int d, int h, int l, int i) {
            this.day = d;
            this.hurry = h;
            this.line = l;
            this.idx = i;
        }

        @Override
        public int compareTo(Person other) {
            if(this.day < other.day) return 1;
            else if(this.day == other.day) {
                if(this.hurry < other.hurry) return 1;
                else if(this.hurry == other.hurry) {
                    return this.line - other.line;
                }
                else return other.hurry - this.hurry;
            }
            return other.day - this.day;
        };
    }
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Queue<Person>[] queue = new Queue[M];
        for(int i=0; i<M; i++) {
            queue[i] = new LinkedList<>();
        }

        int day, hurry, line;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            day = Integer.parseInt(st.nextToken());
            hurry = Integer.parseInt(st.nextToken());
            line = i%M;

            queue[line].add(new Person(day, hurry, line, i));
        }

        PriorityQueue<Person> person = new PriorityQueue<>();
        for(int i=0; i<M; i++) {
            if(!queue[i].isEmpty())
                person.add(queue[i].poll());
        }
        int result = FindDeca(person, queue, K);
        bw.write(result + "\n");
        bw.flush();
        br.close();
        bw.close();
    }

    public static int FindDeca(PriorityQueue<Person> person, Queue<Person>[] queue, int K) {
        int result = 0;
        while(!person.isEmpty()) {
            Person curPerson = person.poll();
            if(curPerson.idx == K) {
                break;
            }
            else {
                if(!queue[curPerson.line].isEmpty())
                    person.add(queue[curPerson.line].poll());
            }
            result++;
        }

        return result;

    }
}
