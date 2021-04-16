package BOJ;

import java.io.*;
import java.util.ArrayList;
import java.util.Locale;

//BOJ 19642
//Not Finished
public class TORIE {
    static ArrayList<Integer>[] tree;
    static String[] word;
    static ArrayList<Integer> rootNode = new ArrayList<>();
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
        word = new String[input.length()+1];

        Init(input);
        blocked = new boolean[wordCount];
        for(int i=1; i<wordCount; i++){
            Begin(i);
        }

        boolean flag = false;
        for(int r : rootNode) {
            if(!blocked[r]) {
                flag = true;
                Print(r);
            }
        }
        if(!flag) bw.write("torie!");
        else bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }

    static int f = 0;
    static int idx = 0;
    static int wordCount = 0;
    static int rootCount = 0;
    public static void Init(String input) {
        int first = f;
        String str = "";

        while(idx<input.length()) {
            if(input.charAt(idx) == '[') {
                if(rootCount == 0) rootNode.add(f+1);
                rootCount++;
                tree[first].add(++f);
                idx++;
                Init(input);
            }
            else if(input.charAt(idx) == ']') {
                rootCount--;
                idx++;
                break;
            }
            else {
                str += input.charAt(idx);
                idx++;
            }
        }
        word[first] = str;
        wordCount++;
    }

    public static void Begin(int curNode) {
        for(int i=0; i<word[curNode].length(); i++){
            int j;
            boolean check = true;
            for(j=0; j<keyword.length() && i+j<word[curNode].length(); j++) {
                if(keyword.charAt(j) != word[curNode].charAt(i+j)) {
                    check = false;
                    break;
                }
            }

            if(check) {
                if(keyword.length() == j) {
                    blocked[curNode] = true;
                    break;
                }
                for(int t : tree[curNode]) {
                    if(Down(t, j)) {
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
        for(int i=0; i<word[curNode].length(); i++) {
            if(keyIdx == keyword.length()) break;
            if(word[curNode].charAt(i) != keyword.charAt(keyIdx)) {
                   check = false;
                   break;
               }
            keyIdx++;
        }
        if(!check) return false;
        if(keyword.length() == keyIdx) return true;
        for(int t : tree[curNode]) {
            if(Down(t, keyIdx))
                return true;
        }
        return false;
    }

    public static void Print(int curNode) {
        sb.append("["+word[curNode]);
        for(int t : tree[curNode]){
            if(!blocked[t]) Print(t);
        }
        sb.append("]");
    }
}
