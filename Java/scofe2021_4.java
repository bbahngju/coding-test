import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
//안 본 컨텐츠 없게 해주세요
public class scofe2021_4 {
    public static class Contents implements Comparable<Contents>{
        int x;
        int y;
        char rateAlpha;
        float rating;

        public Contents(int x, int y, char rateAlpha, float r) {
            this.x = x;
            this.y = y;
            this.rateAlpha = rateAlpha;
            this.rating = r;
        }

        @Override
        public int compareTo(Contents o) {
            if(this.rating > o.rating) return -1;
            else if(this.rating == o.rating) {
                if(this.x == o.x) {
                    return Integer.compare(o.y, this.y);
                }
                return Integer.compare(o.x, this.x);
            }
            else return 1;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        float[] input = new float[5];
        for(int i=0; i<5; i++) {
            input[i] = Float.parseFloat(st.nextToken());
        }

        st = new StringTokenizer(br.readLine(), " ");
        int xCount = Integer.parseInt(st.nextToken());
        int yCount = Integer.parseInt(st.nextToken());

        PriorityQueue<Contents> yContents = new PriorityQueue<>();
        PriorityQueue<Contents> oContents = new PriorityQueue<>();

        char[][] content = new char[xCount][yCount];
        for(int i=0; i<xCount; i++) {
            content[i] = br.readLine().toCharArray();
        }

        char[][] rate = new char[xCount][yCount];
        for(int i=0; i<xCount; i++) {
            rate[i] = br.readLine().toCharArray();
        }

        for(int i=0; i<xCount; i++) {
            for(int j=0; j<yCount; j++) {
                if(content[i][j] == 'Y'){
                    yContents.add(new Contents(i, j, rate[i][j], input[(rate[i][j]-'A')]));
                }
                else if(content[i][j] == 'O') {
                    oContents.add(new Contents(i, j, rate[i][j], input[(rate[i][j]-'A')]));
                }
            }
        }

        while(!yContents.isEmpty()) {
            Contents con = yContents.poll();
            bw.write(con.rateAlpha + " " + con.rating + " " + con.x + " " + con.y + "\n");
        }
        while(!oContents.isEmpty()) {
            Contents con = oContents.poll();
            bw.write(con.rateAlpha + " " + con.rating + " " + con.x + " " + con.y + "\n");
        }

        bw.flush();
        br.close();
        bw.close();
    }
}
