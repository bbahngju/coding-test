package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;

//BOJ 3967
//
public class MagicStar {
    private static int[][] star = {{0, 2}, {3, 4}, {0, 3},
            {2, 3}, {3, 5}, {0, 4},
            {2, 5}, {0, 1}, {1, 4},
            {1, 5}, {1, 2}, {4, 5}};
    private static int[] line;
    private static boolean[] alpha;
    private static char[][] map;

    public static void main(String[] arg) throws IOException {
        line = new int[6];
        alpha = new boolean[12]; //A ~ L
        map = new char[5][9];

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i = 0; i < map.length; i++) {
            map[i] = br.readLine().toCharArray();
        }

        Arrays.fill(line, 0);

        int cnt = 0;
        int first, second, value;
        for(char[] ma : map) {
            for(char m : ma) {
                if(m == '.') continue;
                if(m != 'x') {
                    first = star[cnt][0];
                    second = star[cnt][1];
                    value = m - 'A'; //if m == 'A', 'A'-'A' = 0
                    line[first] += value + 1;
                    line[second] += value + 1;
                    alpha[value] = true; //0 ~ 11
                }
                cnt++;
            }
        }

        fillMap(0, 0, 0);
    }

    public static void fillMap(int x, int y, int cur) {
        if(y == 9) {
            fillMap(x+1, 0, cur);
            return;
        }

        if(cur == 12) {
            for(char[] row : map) {
                for(char character : row) {
                    System.out.printf("%c", character);
                }
                System.out.println();
            }
            System.exit(0);
        }

        if(map[x][y] == '.') {
            fillMap(x, y+1, cur);
            return;
        }

        if(map[x][y] == 'x') {
            for(int i = 0; i < alpha.length; i++) {
                if(isCheck(i, cur)) {
                    map[x][y] = (char)('A' + i);
                    line[star[cur][0]] += i+1;
                    line[star[cur][1]] += i+1;
                    alpha[i] = true;

                    fillMap(x, y+1, cur+1);

                    map[x][y] = 'x';
                    line[star[cur][0]] -= i+1;
                    line[star[cur][1]] -= i+1;
                    alpha[i] = false;
                }
            }
            return;
        }
        fillMap(x, y+1, cur+1);
    }

    public static boolean isCheck(int alp, int cur) {
        return !alpha[alp] && (line[star[cur][0]] + alp + 1) <= 26 && (line[star[cur][1]] + alp + 1) <= 26;
    }
}
