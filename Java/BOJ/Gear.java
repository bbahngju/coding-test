package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//BOJ 14891
//
public class Gear {
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String[] input;
        int[][] gear = new int[4][8];
        int[] idx = {0 ,0, 0, 0};
        int rot, op, direct;
        boolean[] visited;

        for(int i = 0; i < 4; i++) {
            input = br.readLine().split("");
            for(int j = 0; j < input.length; j++) {
                gear[i][j] = Integer.parseInt(input[j]);
            }
        }

        rot = Integer.parseInt(br.readLine());
        while(rot > 0) {
            visited = new boolean[4];
            st = new StringTokenizer(br.readLine(), " ");
            op = Integer.parseInt(st.nextToken())-1;
            direct = Integer.parseInt(st.nextToken());

            visited[op] = true;
            Rotation(op, direct, gear, idx, visited);
            rot--;
        }

        System.out.println(gear[0][idx[0]] + gear[1][idx[1]] * 2 +  gear[2][idx[2]] * 4 + gear[3][idx[3]] * 8);
    }

    public static void Rotation(int op, int direct, int[][] gear, int[] idx, boolean[] v) {
        int nextIdx;
        int three, nine;
        if(op == 0 ) {
            three = (idx[op] + 2) % 8;
            nine = (idx[op+1] + 6) % 8;

            if(gear[op][three] != gear[op+1][nine] && !v[op+1]) {
                v[op+1] = true;
                Rotation(op+1, direct*-1, gear, idx, v);
            }
        }
        else if(op == 1 || op == 2) {
            three = (idx[op] + 2) % 8;
            nine = (idx[op+1] + 6) % 8;

            if(gear[op][three] != gear[op+1][nine] && !v[op+1]) {
                v[op+1] = true;
                Rotation(op+1, direct*-1, gear, idx, v);
            }

            nine = (idx[op] + 6) % 8;
            three = (idx[op-1] + 2) % 8;

            if(gear[op][nine] != gear[op-1][three] && !v[op-1]) {
                v[op-1] = true;
                Rotation(op-1, direct*-1, gear, idx, v);
            }
        }
        else if(op == 3){
            nine = (idx[op] + 6) % 8;
            three = (idx[op-1] + 2) % 8;

            if(gear[op][nine] != gear[op-1][three] && !v[op-1]) {
                v[op-1] = true;
                Rotation(op-1, direct*-1, gear, idx, v);
            }
        }

        nextIdx = idx[op] + (direct*-1);
        if(nextIdx < 0) idx[op] = 7;
        else idx[op] = nextIdx % 8;
    }
}
