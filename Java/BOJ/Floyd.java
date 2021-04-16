package BOJ;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

//BOJ 11404
public class Floyd {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int cityNumber = Integer.parseInt(br.readLine());
        int busNumber = Integer.parseInt(br.readLine());
        int INF = 10000000;
        int[][] city = new int[cityNumber+1][cityNumber+1];

        for(int[] c: city) {
            Arrays.fill(c, INF);
        }

        int start, end, cost;
        for(int i=0; i<busNumber; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());
            cost = Integer.parseInt(st.nextToken());

            city[start][end] = Math.min(city[start][end], cost);
        }

        //transfer, start, end
        for(int t=1; t<=cityNumber; t++) {
            for(int s=1; s<=cityNumber;s++) {
                for(int e=1; e<=cityNumber; e++) {
                    if(s == e) {
                        city[s][e] = 0;
                        continue;
                    }
                    city[s][e] = Math.min(city[s][e], (city[s][t]+city[t][e]));
                }
            }
        }

        for(int i=1; i<=cityNumber; i++) {
            for(int j=1; j<=cityNumber; j++) {
                if(city[i][j] == INF) {
                    bw.write(0 + " ");
                }
                else bw.write(city[i][j] + " ");
            }
            bw.write("\n");
        }

        bw.flush();
        br.close();
        bw.close();
    }
}
