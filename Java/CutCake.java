import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CutCake {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int cutCount = Integer.parseInt(st.nextToken());
        int stationCount = Integer.parseInt(st.nextToken());
        int cakeLength = Integer.parseInt(st.nextToken());

        int[] station = new int[stationCount+1];
        for(int i=0; i<stationCount; i++) {
            station[i] = Integer.parseInt(br.readLine());
        }
        station[stationCount] = cakeLength;

        int[] cutting = new int[cutCount];
        for(int i=0; i<cutCount; i++) {
            cutting[i] = Integer.parseInt(br.readLine());
        }

        for(int c=0; c<cutting.length; c++) {
            int result = CutCake(station, cutting[c], cakeLength);
            System.out.println(result);
        }
    }

    public static int CutCake(int[] station, int cut, int cakeLength) {
        int left = 0;
        int right = cakeLength;
        int result = 0;

        while(left <= right){
            System.out.printf("left: %d, right: %d\n", left, right);
            int mid = (left+right)/2;

            if(FindMinimum(station, cut, left, right, mid)) {
                result = Math.max(result, mid);
                left = mid+1;
            }
            else right = mid-1;
        }

        return result;
    }

    public static boolean FindMinimum(int[] station, int cut,
                                      int left, int right, int mid) {
        int previousCut = 0;
        int restCut = cut;

        for(int i=0; i<station.length; i++) {
            if(station[i]-previousCut >= mid) {
                restCut--;
                previousCut = station[i];
            }
        }
        return (restCut < 0) ? true : false;
    }

}

