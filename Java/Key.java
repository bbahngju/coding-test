import java.io.*;
import java.util.*;

public class Key {
        public static class Points {
            int x;
            int y;

            public Points(int x, int y) {
                this.x = x;
                this.y = y;
            }
        }

        public static void main(String[] arg) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            StringTokenizer st;

            int testCase = Integer.parseInt(br.readLine());
            Queue<Points> pq = new LinkedList<>();
            Queue<Points>[] closed;

            boolean[] keys = new boolean[26];
            int[] dx = new int[]{0, 0, -1, 1};
            int[] dy = new int[]{-1, 1, 0, 0};

            while(testCase > 0) {
                int result = 0;
                closed = new LinkedList[26];
                for(int i=0; i<26; i++) closed[i] = new LinkedList<>();

                st = new StringTokenizer(br.readLine(), " ");
                int h = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());

                char[][] map = new char[h+1][w+1];
                boolean[][] visited = new boolean[h][w];
                int[] hIdx = {0, h-1};
                int[] wIdx = {0, w-1};

                for(int i=0; i<h; i++) {
                    map[i] = br.readLine().toCharArray();
                }

                for(int i : hIdx) {
                    for(int j=0; j<w; j++) {
                        if(map[i][j] != '*') {
                            pq.add(new Points(i, j));
                            visited[i][j] = true;
                        }
                    }
                }

                for(int i : wIdx) {
                    for(int j=0; j<h; j++) {
                        if(map[j][i] != '*' && !visited[j][i]) {
                            pq.add(new Points(j,i));
                            visited[j][i] = true;
                        }
                    }
                }

                Arrays.fill(keys, false);
                char[] key = br.readLine().toCharArray();
                for(int i=0; i<key.length; i++) {
                    if(key[i] == '0') break;
                    keys[key[i]-'a'] = true;
                }

                while(!pq.isEmpty()) {
                    Points point = pq.poll();
                    if(Character.isUpperCase(map[point.x][point.y])) {
                        if(!keys[map[point.x][point.y]-'A']) {
                            closed[map[point.x][point.y]-'A'].add(point);
                            continue;
                        }
                    }
                    if(Character.isLowerCase(map[point.x][point.y])) {
                        int idx = map[point.x][point.y]-'a';
                        if(!keys[idx]) {
                            keys[idx] = true;
                            while(!closed[idx].isEmpty()) {
                                pq.add(closed[idx].poll());
                            }
                        }
                    }
                    if(map[point.x][point.y] == '$') {
                        result++;
                    }

                    for(int i=0; i<dx.length; i++) {
                        int curX = point.x + dx[i];
                        int curY = point.y + dy[i];
                        if(curX >= 0 && curX < h && curY >= 0 && curY < w
                                && !visited[curX][curY] && map[curX][curY] != '*') {
                            pq.add(new Points(curX, curY));
                            visited[curX][curY] = true;
                        }
                    }
                }
                bw.write(result + "\n");
                testCase--;
                pq.clear();
            }

            bw.flush();
            br.close();
            bw.close();
        }
}
