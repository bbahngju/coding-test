package BOJ;

import java.io.*;
import java.util.*;

//BOJ 17143
//시뮬, 73분
public class FishingKing {
    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        PriorityQueue<Shark> sharks = new PriorityQueue<>(new Comparator<Shark>() {
            @Override
            public int compare(Shark o1, Shark o2) {
                return o2.size - o1.size;
            }
        });

        int row = Integer.parseInt(st.nextToken());
        int col = Integer.parseInt(st.nextToken());
        int sharkNum = Integer.parseInt(st.nextToken());

        int[][] ocean = new int[row][col];
        init(ocean);

        int x, y, speed, direction, size;
        for(int i = 0; i < sharkNum; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            x = Integer.parseInt(st.nextToken())-1;
            y = Integer.parseInt(st.nextToken())-1;
            speed = Integer.parseInt(st.nextToken());
            direction = Integer.parseInt(st.nextToken())-1;
            size = Integer.parseInt(st.nextToken());

            ocean[x][y] = size;
            sharks.add(new Shark(x, y, speed, direction, size));
        }

        int[] result;
        int answer = 0;
        int nextX, nextY, dir;
        Queue<Shark> nextShark = new LinkedList<>();
        for(int i = 0; i < col; i++) {
            result = fishing(row, i, ocean);
            if(result[2] != -1) answer += result[2];

            init(ocean);
            nextShark.clear();
            while(!sharks.isEmpty()) {
                Shark cur = sharks.poll();
                dir = cur.direct;
                if(cur.x == result[0] && cur.y == result[1]) continue;

                nextX = cur.x + (dx[dir] * cur.speed);
                nextY = cur.y + (dy[dir] * cur.speed);

                while(nextX < 0 || nextX >= row || nextY < 0 || nextY >= col) {
                    if(dir == 0) {
                        nextX = nextX * -1;
                        dir = 1;
                    }
                    else if(dir == 1) {
                        nextX = (row - 1) - (nextX - (row - 1));
                        dir = 0;
                    }
                    else if(dir == 2) {
                        nextY = (col - 1) - (nextY - (col - 1));
                        dir = 3;
                    }
                    else if(dir == 3) {
                        nextY = nextY * -1;
                        dir = 2;
                    }
                }

                if(ocean[nextX][nextY] == 0) {
                    ocean[nextX][nextY] = cur.size;
                    nextShark.add(new Shark(nextX, nextY, cur.speed, dir, cur.size));
                }
            }

            while(!nextShark.isEmpty()) {
                sharks.add(nextShark.poll());
            }
        }

        bw.write(answer + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    public static void init(int[][] ocean) {
        for(int[] oc : ocean) {
            Arrays.fill(oc, 0);
        }
    }

    public static int[] fishing(int x, int y, int[][] ocean) {
        int catchX = -1, catchY = -1;
        int size = -1;
        for(int i = 0; i < x; i++) {
            if(ocean[i][y] != 0) {
                size = ocean[i][y];
                catchX = i;
                catchY = y;
                break;
            }
        }
        return new int[]{catchX, catchY, size};
    }

    public static class Shark {
        int x;
        int y;

        int speed;
        int direct;
        int size;

        public Shark(int x, int y, int s, int d, int z) {
            this.x = x;
            this.y = y;
            this.speed = s;
            this.direct = d;
            this.size = z;
        }
    }
}
