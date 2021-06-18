package BOJ;

import java.io.*;
import java.util.*;

//BOJ 16235
//시뮬
public class treeFinance {
    public static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    public static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
    public static int[][] food;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int treeCnt = Integer.parseInt(st.nextToken());
        int year = Integer.parseInt(st.nextToken());

        food = new int[n][n];
        int[][] land = new int[n][n];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < n; j++) {
                food[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int[] l : land) {
            Arrays.fill(l, 5);
        }

        PriorityQueue<Tree> trees = new PriorityQueue<>(new Comparator<Tree>() {
            @Override
            public int compare(Tree o1, Tree o2) {
                return o1.age - o2.age;
            }
        });

        int x, y, age;
        for(int i = 0; i < treeCnt; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            x = Integer.parseInt(st.nextToken()) - 1;
            y = Integer.parseInt(st.nextToken()) - 1;
            age = Integer.parseInt(st.nextToken());

            trees.add(new Tree(x, y, age));
        }

        while(year > 0) {
            spring(land, trees);
            year--;
        }

        bw.write(trees.size() + "\n");

        bw.flush();
        bw.close();
        br.close();
    }

    public static void spring(int[][] land, PriorityQueue<Tree> trees) {
        Queue<Tree> dieTrees = new LinkedList<>();
        Queue<Tree> tmp = new LinkedList<>();

        int remainFood;
        while(!trees.isEmpty()) {
            Tree cur = trees.poll();
            remainFood = land[cur.x][cur.y] - cur.age;
            if(remainFood < 0) {
                dieTrees.add(new Tree(cur.x, cur.y, cur.age));
            }
            else {
                land[cur.x][cur.y] = remainFood;
                tmp.add(new Tree(cur.x, cur.y, cur.age+1));
            }
        }

        while(!tmp.isEmpty()) {
            trees.add(tmp.poll());
        }

        summer(land, dieTrees);
        fall(land, trees);
        winter(land);
    }

    public static void summer(int[][] land, Queue<Tree> die) {
        int food;
        while(!die.isEmpty()) {
            Tree cur = die.poll();
            food = cur.age/2;
            land[cur.x][cur.y] += food;
        }
    }

    public static void fall(int[][] land, PriorityQueue<Tree> trees) {
        Queue<Tree> propagation = new LinkedList<>();
        Queue<Tree> tmp = new LinkedList<>();

        while(!trees.isEmpty()) {
            Tree cur = trees.poll();
            if(cur.age % 5 == 0) {
                propagation.add(cur);
            }
            tmp.add(cur);
        }

        while(!tmp.isEmpty()) {
            trees.add(tmp.poll());
        }

        while(!propagation.isEmpty()) {
            Tree cur = propagation.poll();
            propagate(land, cur.x, cur.y, trees);
        }
    }

    public static void propagate(int[][] land, int x, int y, PriorityQueue<Tree> trees) {
        int nextX, nextY;
        int row = land.length;
        int col = land[0].length;
        for(int i = 0; i < 8; i++) {
            nextX = x + dx[i];
            nextY = y + dy[i];

            if(nextX < 0 || nextX >= row || nextY < 0 || nextY >= col) continue;
            trees.add(new Tree(nextX, nextY, 1));
        }
    }

    public static void winter(int[][] land) {
        int row = land.length;
        int col = land[0].length;

        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                land[i][j] += food[i][j];
            }
        }
    }

    public static class Tree {
        int x;
        int y;
        int age;

        public Tree(int x, int y, int age) {
            this.x = x;
            this.y = y;
            this.age = age;
        }
    }
}
