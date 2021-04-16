package BOJ;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

//BOJ 19637
public class WriteIFStatementsInstead {
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        int titleNumber = Integer.parseInt(st.nextToken());
        int characterNumber = Integer.parseInt(st.nextToken());

        List<String> title = new ArrayList<>();
        List<Integer> limited = new ArrayList<>();

        int cnt = 0;
        for(int i=0; i<titleNumber; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            String t = st.nextToken();
            int l = Integer.parseInt(st.nextToken());

            if(i==0 || !limited.get(cnt-1).equals(l)) {
                title.add(t);
                limited.add(l);
                cnt++;
            }
        }

        for(int i=0; i<characterNumber; i++) {
            int power = Integer.parseInt(br.readLine());
            int idx = Search(power, limited);
            bw.write(title.get(idx) + "\n");
        }

        bw.flush();
        br.close();
        bw.close();

    }

    public static int Search(int power, List<Integer> limited){
        int left = 0;
        int right = limited.size();
        int mid = (left+right)/2;
        while(left<right) {
            if(power == limited.get(mid)) return mid;
            else if(power < limited.get(mid)) {
                right = mid-1;
            }
            else {
                left = mid+1;
            }

            mid = (left+right)/2;
        }
        if(power > limited.get(mid)) return mid+1;
        else return mid;
    }
}
