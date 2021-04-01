import java.io.*;
import java.util.ArrayList;
import java.util.Locale;

public class TORIE {
    static ArrayList<Integer>[] tree;
    static ArrayList<String> word;
    static boolean[] visited;
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String input = br.readLine().toString();
        String keyword = br.readLine().toString();

        tree = new ArrayList[input.length()+1];
        for(int i=0; i<input.length()+1; i++) {
            tree[i] = new ArrayList<>();
        }
        word = new ArrayList<>();
        word.add(0, "0");
        visited = new boolean[input.length()+1];
        Init(input, 1);

        Search(1, keyword, 0);
    }

    static int f = 1;
    public static void Init(String input, int idx) {
        int first = f;
        for(int i=idx; i<input.length(); i++) {
            if(visited[i]) continue;

            visited[i] = true;
            if(input.charAt(i) == '[') {
                if(input.charAt(i-1) != ']')
                    word.add(f, input.substring(idx, i));
                tree[first].add(++f);
                Init(input, i+1);
            }

            if(input.charAt(i) == ']') {
                if(input.charAt(i-1) != ']')
                    word.add(f, input.substring(idx, i));
                return;
            }
        }

    }

    public static void Search(int node, String key, int keyIdx) {

    }
}
