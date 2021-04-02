import java.io.*;
import java.util.ArrayList;
import java.util.Locale;

public class TORIE {
    static ArrayList<Integer>[] tree;
    static ArrayList<String> word;
    static boolean[] visited;
    static boolean[] blocked;
    static String keyword;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String input = br.readLine();
        keyword = br.readLine();

        tree = new ArrayList[input.length()+1];
        for(int i=0; i<input.length()+1; i++) {
            tree[i] = new ArrayList<>();
        }
        word = new ArrayList<>();
        word.add(0, "0");

        visited = new boolean[input.length()+1];
        Init(input, 1);
        blocked = new boolean[word.size()];
        for(int i=0; i<word.size(); i++) {
            System.out.println(word.get(i));
        }
        for(int i=1; i<word.size(); i++){
            Begin(i);
        }

        if(Print(1)) bw.write("torie!\n");
        else bw.write(sb.toString());

        bw.flush();
        br.close();
        bw.close();
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

    public static void Begin(int curNode) {
        for(int i=0; i<word.get(curNode).length(); i++){
            int j;

            boolean check = true;
            for(j=0; j<keyword.length() && i+j<word.get(curNode).length(); j++) {
                if(keyword.charAt(j) != word.get(curNode).charAt(i+j)) {
                    check = false;
                    break;
                }
            }

            if(check) {
                if(keyword.length() == j) {
                    blocked[curNode] = true;
                    break;
                }
                boolean result;
                for(int t : tree[curNode]) {
                    result = Down(t, j);
                    if(result) {
                        blocked[curNode] = true;
                        break;
                    }
                }

            }

            if(blocked[curNode]) break;
        }
    }

    public static boolean Down(int curNode, int keyIdx) {
        boolean check = true;
        for(int i=0; i<word.get(curNode).length(); i++) {
               if(word.get(curNode).charAt(i) != keyword.charAt(keyIdx)) {
                   check = false;
                   break;
               }
               ++keyIdx;
        }
        if(!check) return false;
        if(keyword.length() == keyIdx) return true;
        for(int t : tree[curNode]) {
            if(Down(t, keyIdx))
                return true;
        }
        return false;
    }

    static boolean torie = true;
    public static boolean Print(int curNode) {
        if(blocked[curNode]) return torie;

        torie = false;
        sb.append("["+word.get(curNode));
        for(int t : tree[curNode]){
            if(!blocked[t]) Print(t);
        }
        sb.append("]");

        return torie;
    }
}
