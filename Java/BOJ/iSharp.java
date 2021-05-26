package BOJ;

import java.io.*;
import java.util.StringTokenizer;

//BOJ 3568
//시뮬, 16분
public class iSharp {
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");

        String type = st.nextToken();
        while(st.hasMoreTokens()) {
            char[] variable = st.nextToken().toCharArray();
            StringBuilder alpha = new StringBuilder();
            StringBuilder answer = new StringBuilder();

            for(int i = variable.length-1; i >= 0; i--) {
                if(variable[i] == ',' || variable[i] == ';' || variable[i] == ']') continue;
                if(variable[i] == '[' ) answer.append("[]");
                else if(variable[i] == '*' || variable[i] == '&') answer.append(variable[i]);
                else {
                    alpha.insert(0, variable[i]);
                }
            }

            bw.write(type + answer + " " + alpha + ";\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
