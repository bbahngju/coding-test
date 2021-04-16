package BOJ;

import java.util.Scanner;

//BOJ 17070
public class MovePipe {
  static int N;
  static int[][] house;
  static int result;
  static int[][] moving = {{0,1}, {1,0}, {1,1}};

  static boolean isRange(int dx, int dy){
    if (dx < 0 || dx >= N || dy < 0 || dy >= N) {
      return false;
    }
    return true;
  }

  static void dfs(int x, int y, int m) {
    if(x == N-1 && y == N-1) {
      result++;
      return;
    }

    for(int i=0; i<3; i++) {
      if (isRange(x+moving[i][0], y+moving[i][1]) && house[x+moving[i][0]][y+moving[i][1]] == 0) {
        if((i == 0 && m == 1) || (i == 1 && m == 0)) {
          continue;
        }

        if(i == 2) {
          if(house[x][y+1] != 0 || house[x+1][y] != 0) {
            continue;
          }
        }

        dfs(x+moving[i][0], y+moving[i][1], i);
      }
    }
  }
  
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		
    house = new int[N][N];
    for(int i=0; i<N; i++) {
      for(int j=0; j<N; j++) {
        house[i][j] = sc.nextInt();
      }
    }
		
    result = 0;
    dfs(0, 1, 0);
    
    System.out.println(result);
	}
}