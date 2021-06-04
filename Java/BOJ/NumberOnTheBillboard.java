package BOJ;

import java.io.*;
import java.util.Arrays;

//BOJ 16159
//시뮬, 110분
public class NumberOnTheBillboard {
    public static int[] answer;
    public static boolean same, finish;
    public static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int row, col;
        row = 7;

        String[] first = br.readLine().split("");
        col = first.length;

        int[][][] saveNum = new int[10][7][6];
        int[][] billboard = new int[row][col];
        int[] num = new int[col/6];
        int numIdx = 0; int oneIdx = 0; int one;
        int[] totalOne = new int[6];
        answer = new int[col/6];
        visited = new boolean[col/6];

        for(int i = 0; i < col; i++) {
            billboard[0][i] = Integer.parseInt(first[i]);
        }
        for(int i = 1; i < row; i++) {
            String[] input = br.readLine().split("");
            for(int j = 0; j < col; j++) {
                billboard[i][j] = Integer.parseInt(input[j]);
            }
        }

        for(int i = 0; i < col; i++) {
            one = 0;
            for(int j = 0; j < row; j++) {
                if(billboard[j][i] == 1) one++;
            }
            totalOne[oneIdx++] = one;

            if(oneIdx == 6) {
                num[numIdx++] = findNum(totalOne); //1의 개수를 이용해 숫자 찾기
                oneIdx = 0;
            }
        }

        //전광판 배열을 수마다 저장하기
        int idx = 0; int line = 0;
        for(int i = 1; i <= col; i++) {
            for(int j = 0; j < row; j++) {
                saveNum[num[idx]][j][line] = billboard[j][i-1];
            }
            line++;

            if(i % 6 == 0) {
                idx++;
                line = 0;
            }
        }

        int[] sortNum = Arrays.copyOf(num, num.length);
        Arrays.sort(sortNum);
        permutation(sortNum, num, 0, 0); //순열

        if(!finish) bw.write("The End");
        else {
            for(int i = 0; i < 7; i++) {
                for (int value : answer) {
                    for (int k = 0; k < 6; k++) {
                        bw.write(saveNum[value][i][k] + "");
                    }
                }
                bw.write("\n");
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

    public static int findNum(int[] total) {
        int one = total[0]; int two = total[1]; int three = total[2];
        int four = total[3]; int five = total[4]; int six = total[5];

        if(one == 0 && two == 3 && three == 2 && four == 2 && five == 3 && six == 0)
            return 0;
        else if(one == 0 && two == 0 && three == 1 && four == 5 && five == 0 && six == 0)
            return 1;
        else if(one == 0 && two == 4 && three == 3 && four == 3 && five == 4 && six == 0)
            return 2;
        else if(one == 0 && two == 2 && three == 2 && four == 3 && five == 2 && six == 0)
            return 3;
        else if(one == 1 && two == 2 && three == 2 && four == 5 && five == 1 && six == 0)
            return 4;
        else if(one == 0 && two == 4 && three == 3 && four == 3 && five == 3 && six == 0)
            return 5;
        else if(one == 0 && two == 5 && three == 2 && four == 2 && five == 3 && six == 0)
            return 6;
        else if(one == 0 && two == 1 && three == 1 && four == 4 && five == 2 && six == 0)
            return 7;
        else if(one == 0 && two == 5 && three == 3 && four == 3 && five == 5 && six == 0)
            return 8;
        else return 9;
    }

    public static void permutation(int[] sort, int[] num, int cnt, int idx) {
        if(cnt == sort.length) {
            if(same) {
                finish = true;
                return;
            }

            int i;
            for(i = 0; i < num.length; i++) {
                if(answer[i] != num[i]) break;
            }
            if(i == num.length) {
                same = true;
            }

            return;
        }

        for(int i = 0; i < sort.length; i++) {
            if(!visited[i]) {
                answer[cnt] = sort[i];
                visited[i] = true;
                permutation(sort, num, cnt+1, i+1);
                visited[i] = false;
                if(finish) return;
            }
        }
    }
}
