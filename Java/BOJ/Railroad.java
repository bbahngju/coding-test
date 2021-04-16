package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//BOJ 13334
public class Railroad {
    public static class Point implements Comparable<Point> {
        int left;
        int right;

        public Point(int inputLeft, int inputRight) {
            this.left = inputLeft;
            this.right = inputRight;
        }

        @Override
        public int compareTo(Point o) {
            return Integer.compare(this.right, o.right);
        }
    }

    static ArrayList<Point> points = new ArrayList<>();

    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int maximum = 0;
        int left, right;

        int[] swapping = new int[2];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            left = Integer.parseInt(st.nextToken());
            right = Integer.parseInt(st.nextToken());

            if(left > right) {
                int temp = left;
                left = right;
                right = temp;
            }
            points.add(new Point(left, right));
        }
        int railLength = Integer.parseInt(br.readLine());
        Collections.sort(points);

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for(Point point : points) {
            if(point.right-point.left <= railLength) priorityQueue.add(point.left);
            else continue;

            while(!priorityQueue.isEmpty()) {
                int preLeft = priorityQueue.peek();
                if(point.right-preLeft <= railLength)
                    break;
                else
                    priorityQueue.poll();
            }
            maximum = Math.max(maximum, priorityQueue.size());
        }

        System.out.println(maximum);
    }

}
