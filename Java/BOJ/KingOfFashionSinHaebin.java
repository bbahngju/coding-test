package BOJ;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

//BOJ 9375
//O(tc * N)
public class KingOfFashionSinHaebin {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Map<String, Integer> clothes = new HashMap<>();
        int tc = Integer.parseInt(br.readLine());

        while(tc > 0) {
            int n = Integer.parseInt(br.readLine());
            for(int i = 0; i < n; i++) {
                String[] cloth = br.readLine().split(" ");
                clothes.put(cloth[1], clothes.getOrDefault(cloth[1], 0) + 1);
            }

            int answer = 1;
            for(String key : clothes.keySet()) {
                answer *= (clothes.get(key)+1);
            }

            bw.write((answer-1) + "\n");

            clothes.clear();

            tc--;
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
