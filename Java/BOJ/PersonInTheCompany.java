package BOJ;

import java.io.*;
import java.util.*;

//BOJ 7785
//O(N)
public class PersonInTheCompany {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringBuilder sb = new StringBuilder();
        Set<String> names = new HashSet<>();
        int num = Integer.parseInt(br.readLine());

        for(int i = 0; i < num; i++) {
            String[] person = br.readLine().split(" ");

            if(names.contains(person[0])) {
                names.remove(person[0]);
                continue;
            }

            names.add(person[0]);
        }

        List<String> sortNames = new ArrayList(names);
        Collections.sort(sortNames, Collections.reverseOrder());

        for(String name : sortNames) {
            sb.append(name).append("\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
