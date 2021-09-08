package BOJ;

import java.io.*;
import java.util.*;

//BOJ 4358
//O(NlogN)
public class Ecology {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder answer = new StringBuilder();

        Map<String, Double> trees = new HashMap<>();

        String tree;
        int cnt = 0;
        while((tree = br.readLine()) != null) {
            cnt++;
            if(!trees.containsKey(tree)) {
                trees.put(tree, 1.0);
                continue;
            }

            trees.put(tree, trees.get(tree) + 1);
        }

        List<Map.Entry<String, Double>> sortedTrees = new ArrayList<>(trees.entrySet());
        sortedTrees.sort(((o1, o2) -> o1.getKey().compareTo(o2.getKey())));

        for(Map.Entry<String, Double> t : sortedTrees) {
            answer.append(t.getKey()).append(" ").append(String.format("%.4f", t.getValue()/cnt * 100)).append("\n");
        }

        bw.write(answer.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
