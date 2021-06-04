package BOJ;

import java.io.*;
import java.util.StringTokenizer;

//BOJ 12100
//시뮬 재귀, 85분
//O(4^5 * 20 * 20 * 20)
public class Game2048 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[][] map = new int[n][n];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        play(map, 0);

        bw.write(maxValue + "\n");

        bw.flush();
        bw.close();
        br.close();
    }

    public static int maxValue = 0;
    public static void play(int[][] map, int move) {
        int len = map.length;

        if(move == 5) {
            for (int[] ints : map) {
                for (int j = 0; j < len; j++) {
                    maxValue = Math.max(maxValue, ints[j]);
                }
            }
            return;
        }

        for(int i = 0; i < 4; i++) {
            int[][] copyMap = new int[len][len];
            for(int j = 0; j < len; j++) {
                System.arraycopy(map[j], 0, copyMap[j], 0, len);
            }

            moveMap(copyMap, i, len);
            play(copyMap, move+1);
        }
    }

    public static void moveMap(int[][] curMap, int dir, int len) {
        int start, idx;
        if(dir == 0) {
            for(int i = 0; i < len; i++) {
                start = 0;
                while(start < len) {
                    if(curMap[start][i] != 0) {
                        start = findNum(curMap, start, i, dir);
                    }
                    else {
                        start++;
                    }
                }
            }
            for(int i = 0; i < len; i++) {
                idx = 0;
                for(int j = 0; j < len; j++) {
                    if(curMap[j][i] != 0) {
                        if(j != idx) {
                            curMap[idx][i] = curMap[j][i];
                            curMap[j][i] = 0;
                        }
                        idx++;
                    }
                }
            }
        }
        else if(dir == 1) {
            for(int i = 0; i < len; i++) {
                start = len - 1;
                while(start >= 0) {
                    if(curMap[i][start] != 0) {
                        start = findNum(curMap, i, start, dir);
                    }
                    else {
                        start--;
                    }
                }
            }
            for(int i = 0; i < len; i++) {
                idx = len - 1;
                for(int j = len - 1; j >= 0; j--) {
                    if(curMap[i][j] != 0) {
                        if(j != idx) {
                            curMap[i][idx] = curMap[i][j];
                            curMap[i][j] = 0;
                        }
                        idx--;
                    }
                }
            }

        }
        else if(dir == 2) {
            for(int i = 0; i < len; i++) {
                start = len - 1;
                while(start >= 0) {
                    if(curMap[start][i] != 0) {
                        start = findNum(curMap, start, i, dir);
                    }
                    else {
                        start--;
                    }
                }
            }
            for(int i = 0; i < len; i++) {
                idx = len - 1;
                for(int j = len - 1; j >= 0; j--) {
                    if(curMap[j][i] != 0) {
                        if(j != idx) {
                            curMap[idx][i] = curMap[j][i];
                            curMap[j][i] = 0;
                        }
                        idx--;
                    }
                }
            }
        }
        else {
            for(int i = 0; i < len; i++) {
                start = 0;
                while(start < len) {
                    if(curMap[i][start] != 0) {
                        start = findNum(curMap, i, start, dir);
                    }
                    else {
                        start++;
                    }
                }
            }
            for(int i = 0; i < len; i++) {
                idx = 0;
                for(int j = 0; j < len; j++) {
                    if(curMap[i][j] != 0) {
                        if(j != idx) {
                            curMap[i][idx] = curMap[i][j];
                            curMap[i][j] = 0;
                        }
                        idx++;
                    }
                }
            }
        }
    }

    public static int findNum(int[][] map, int x, int y, int dir) {
        int i;
        if(dir == 0) {
            for(i = x + 1; i < map.length; i++) {
                if(map[i][y] != 0) {
                    if(map[i][y] == map[x][y]) {
                        map[x][y] *= 2;
                        map[i][y] = 0;
                    }
                    break;
                }
            }
            return i;
        }

        else if(dir == 1) {
            for(i = y - 1; i >= 0; i--) {
                if(map[x][i] != 0) {
                    if(map[x][i] == map[x][y]) {
                        map[x][y] *= 2;
                        map[x][i] = 0;
                    }
                    break;
                }
            }
            return i;
        }

        else if(dir == 2) {
            for(i = x - 1; i >= 0; i--) {
                if(map[i][y] != 0) {
                    if(map[i][y] == map[x][y]) {
                        map[x][y] *= 2;
                        map[i][y] = 0;
                    }
                    break;
                }
            }
            return i;
        }

        else {
            for(i = y + 1; i < map.length; i++) {
                if(map[x][i] != 0) {
                    if(map[x][i] == map[x][y]) {
                        map[x][y] *= 2;
                        map[x][i] = 0;
                    }
                    break;
                }
            }
            return i;
        }
    }
}
