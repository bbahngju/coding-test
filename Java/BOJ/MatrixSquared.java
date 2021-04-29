package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//BOJ 10830
//O(logN)
public class MatrixSquared {
    public static int[][] Calculate(int[][] mL, int[][] mR) {
        int[][] tmp = new int[mL.length][mR[0].length];

        for(int i=0; i<mL.length; i++) {
            for(int j=0; j<mR[0].length; j++) {
                tmp[i][j] = 0;
                for(int k=0; k<mL[0].length; k++) {
                    tmp[i][j] += mL[i][k] * mR[k][j] % 1000;
                }
                tmp[i][j] %= 1000;
            }
        }

        return tmp;
    }

    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int size = Integer.parseInt(st.nextToken());
        long b = Long.parseLong(st.nextToken());
        int[][] matrix = new int[size][size];

        for(int i=0; i<size; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<size; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
                matrix[i][j] %= 1000;
            }
        }

        int[][] answer = FastPower(matrix, b);
        PrintMetrix(answer);
    }

    public static int[][] FastPower(int[][] m, long b){
        if(b==0) {
            int[][] unitM = new int[m.length][m[0].length];
            for(int i=0; i<m.length; i++) {
                unitM[i][i] = 1;
            }

            return unitM;
        }

        if(b==1) return m;

        int[][] tmp = FastPower(m, b/2);
        int[][] result = Calculate(tmp, tmp);

        if(b%2==1) result = Calculate(result, m);
        return result;
    }

    public static void PrintMetrix(int[][] m) {
        for (int[] ints : m) {
            for (int j = 0; j < m[0].length; j++)
                System.out.printf("%d ", ints[j]);
            System.out.println();
        }
    }
}
