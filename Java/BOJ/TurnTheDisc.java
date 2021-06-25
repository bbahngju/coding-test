package BOJ;

import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

//BOJ 17822
//시뮬, 68분
public class TurnTheDisc {
    public static int[][] disc;
    public static int totalValue = 0;
    public static int totalCnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        disc = new int[N][M];
        totalCnt = N * M;
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < M; j++) {
                disc[i][j] = Integer.parseInt(st.nextToken());
                totalValue += disc[i][j];
            }
        }

        int x, d, k, cnt, cur;
        while(T > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            x = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            cnt = 1;
            cur = (cnt * x) - 1;
            while(cur < N) {
                cnt++;

                if(d == 0) {
                    clockwise(cur, M, k);
                }
                else {
                    counterclockwise(cur, M, k);
                }


                cur = (cnt * x) - 1;
            }

            removeNum(N, M);
            T--;
        }

        int answer = totalValue;

        bw.write(answer + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    public static void clockwise(int cur, int M, int k) {
        HashMap<Integer, Integer> tmp = new HashMap<>();

        int next, value;
        for(int i = 0; i < M; i++) {
            value = disc[cur][i];
            next = (i + k) % M;

            tmp.put(next, value);
        }

        for(int t : tmp.keySet()) {
            disc[cur][t] = tmp.get(t);
        }
    }

    public static void counterclockwise(int cur, int M, int k) {
        HashMap<Integer, Integer> tmp = new HashMap<>();

        int next, value;
        for(int i = 0; i < M; i++) {
            value = disc[cur][i];
            next = (i + ((M-1) * k)) % M;

            tmp.put(next, value);
        }

        for(int t : tmp.keySet()) {
            disc[cur][t] = tmp.get(t);
        }
    }

    public static void removeNum(int N, int M) {
        int upX, upY;
        int rightX, rightY;
        boolean isRemove = false;
        boolean[][] remove = new boolean[N][M];

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(disc[i][j] == 0) continue;

                upX = i+1;
                upY = j;
                if(upX < N && disc[i][j] == disc[upX][upY]) {
                    remove[upX][upY] = true;
                    remove[i][j] = true;

                    if(!isRemove)
                        isRemove = true;
                }

                rightX = i;
                rightY = (j + 1) % M;
                if(disc[i][j] == disc[rightX][rightY]) {
                    remove[rightX][rightY] = true;

                    if(!remove[i][j])
                        remove[i][j] = true;
                    if(!isRemove)
                        isRemove = true;
                }


            }
        }

        if(!isRemove) {
            int newTotal = 0;

            for(int i = 0; i < N; i++) {
                for(int j = 0; j < M; j++) {
                    if(disc[i][j] == 0) continue;
                    if((disc[i][j] * totalCnt) < totalValue) {
                        disc[i][j] += 1;
                    }
                    else if((disc[i][j] * totalCnt) > totalValue) {
                        disc[i][j] -= 1;
                    }

                    newTotal += disc[i][j];
                }
            }

            totalValue = newTotal;
        }

        else {
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < M; j++) {
                    if(remove[i][j]) {
                        totalValue -= disc[i][j];
                        disc[i][j] = 0;
                        totalCnt--;
                    }
                }
            }
        }
    }
}
