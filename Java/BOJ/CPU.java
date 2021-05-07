package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

//BOJ 16506
public class CPU {
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int tc = Integer.parseInt(br.readLine());
        String[] input;
        Map<String, String> op = new HashMap<>();
        Init(op);

        while(tc > 0) {
            input = br.readLine().split(" ");
            StringBuilder answer = new StringBuilder();
            String tmp;
            answer.append(op.get(input[0]));
            answer.append("0");

            tmp = Integer.toBinaryString(Integer.parseInt(input[1]));
            for(int i = tmp.length(); i < 3; i++) {
                answer.append("0");
            }
            answer.append(tmp);

            if("0".equals(input[2])) {
                answer.append("000");
            }
            else {
                tmp = Integer.toBinaryString(Integer.parseInt(input[2]));
                for(int i = tmp.length(); i < 3; i++) {
                    answer.append("0");
                }
                answer.append(tmp);
            }

            if('0' == answer.charAt(4)) {
                tmp = Integer.toBinaryString(Integer.parseInt(input[3]));
                for(int i = tmp.length(); i < 3; i++) {
                    answer.append("0");
                }
                answer.append(tmp);
                answer.append("0");
            }
            else {
                tmp = Integer.toBinaryString(Integer.parseInt(input[3]));
                for(int i = tmp.length(); i < 4; i++) {
                    answer.append("0");
                }
                answer.append(tmp);
            }

            System.out.println(answer);
            tc--;
        }
    }

    public static void Init(Map<String, String> op) {
        op.put("ADD", "00000");
        op.put("ADDC", "00001");
        op.put("SUB", "00010");
        op.put("SUBC", "00011");
        op.put("MOV", "00100");
        op.put("MOVC", "00101");
        op.put("AND", "00110");
        op.put("ANDC", "00111");
        op.put("OR", "01000");
        op.put("ORC", "01001");
        op.put("NOT", "01010");
        op.put("MULT", "01100");
        op.put("MULTC", "01101");
        op.put("LSFTL", "01110");
        op.put("LSFTLC", "01111");
        op.put("LSFTR", "10000");
        op.put("LSFTRC", "10001");
        op.put("ASFTR", "10010");
        op.put("ASFTRC", "10011");
        op.put("RL", "10100");
        op.put("RLC", "10101");
        op.put("RR", "10110");
        op.put("RRC", "10111");
    }
}
