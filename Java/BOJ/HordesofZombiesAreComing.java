package BOJ;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//BOJ 19644
public class HordesofZombiesAreComing {

    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int L = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int[] gun = new int[2];
        for(int i=0; i<2; i++)
            gun[i] = Integer.parseInt(st.nextToken());
        int mine = Integer.parseInt(br.readLine());

        int[] zombies = new int[L+1];
        zombies[0] = 0;
        for(int i=1; i<=L; i++)
            zombies[i] = Integer.parseInt(br.readLine());

        if(isLive(gun, mine, zombies)) bw.write("YES\n");
        else bw.write("NO\n");

        bw.flush();
        br.close();
        bw.close();
    }

    public static boolean isLive(int[] gun, int mine, int[] zombies) {
        boolean f = true;
        Queue<Integer> useMine = new LinkedList<>();
        int m = mine;
        for(int i=1; i<zombies.length; i++) {
            if(!useMine.isEmpty() && useMine.peek() < i) useMine.remove();
            if(i <= gun[0])
                zombies[i] -= gun[1] * (i - useMine.size());
            else
                zombies[i] -= gun[1] * (gun[0] - useMine.size());

            if(zombies[i] > 0) {
                if(mine > 0) {
                    zombies[i] = 0;
                    useMine.add(i+(gun[0]-1));
                    mine--;
                }
                else {
                    f = false;
                    break;
                }
            }
        }

        if(!f) return false;
        return true;
    }
}
