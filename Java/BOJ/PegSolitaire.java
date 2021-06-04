package BOJ;

import java.io.*;

//BOJ 9207
public class PegSolitaire {
    public static char[][] board;
    public static int minPin, minMove;
    public static int row = 5, col = 9;
    public static int[] dx = {0, 0, -1, 1};
    public static int[] dy = {-1, 1, 0, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int tc = Integer.parseInt(br.readLine());
        board = new char[5][9];

        int totalPin;
        while(tc > 0) {
            for(int i = 0; i < row; i++) {
                board[i] = br.readLine().toCharArray();
            }
            minPin = 9; minMove = 46;

            totalPin = countPin();
            dfs(0, totalPin);

            bw.write(minPin + " " + minMove + "\n");

            tc--;
            if(tc > 0) br.readLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }

    public static int countPin() {
        int cnt = 0;
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(board[i][j] == 'o')
                    cnt++;
            }
        }

        return cnt;
    }

    public static void dfs(int move, int total) {
        int nextX, nextY, n_nextX, n_nextY;
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                for(int k = 0; k < 4; k++) {
                    nextX = i + dx[k];
                    nextY = j + dy[k];

                    if(nextX < 0 || nextX >= row || nextY < 0 || nextY >= col) continue;

                    if(board[i][j] == 'o' && board[nextX][nextY] == 'o') {
                        n_nextX = nextX + dx[k];
                        n_nextY = nextY + dy[k];

                        if(n_nextX < 0 || n_nextX >= row || n_nextY < 0 || n_nextY >= col) continue;
                        if(board[n_nextX][n_nextY] == '.') {
                            board[i][j] = board[nextX][nextY] = '.';
                            board[n_nextX][n_nextY] = 'o';
                            dfs(move+1, total);
                            board[i][j] = board[nextX][nextY] = 'o';
                            board[n_nextX][n_nextY] = '.';
                        }
                    }
                }
            }
        }

        if(minPin > total - move) {
            minPin = total - move;
            minMove = move;
        }
        else if(minPin == total - move) {
            minMove = Math.min(minMove, move);
        }
    }
}
