import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class InsertOperator {
    static int[] number;
    static int[] operator;

    static int maximum = -1000000000, minimum = 1000000000;

    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int numberCount = Integer.parseInt(st.nextToken());
        int operatorCount = 0;
        String[] inputNumber = br.readLine().split(" ");
        String[] inputOperator = br.readLine().split(" ");

        number = new int[inputNumber.length];
        operator = new int[inputOperator.length];
        for(int i=0; i<inputNumber.length; i++) number[i] = Integer.parseInt(inputNumber[i]);
        for(int i=0; i<inputOperator.length; i++) {
            operator[i] = Integer.parseInt(inputOperator[i]);
            operatorCount += operator[i];
        }
        Inserting(operatorCount, number[0], 1);

        System.out.println(maximum);
        System.out.println(minimum);
    }

    public static void Inserting(int operatorCnt, int total, int nextNumberIdx) {
        if(operatorCnt == 0) {
            maximum = Math.max(maximum, total);
            minimum = Math.min(minimum, total);
            return;
        }

        int nextTotal;
        for(int i=0; i<operator.length; i++) {
            if(operator[i] > 0) {
                nextTotal = Calculate(total, i, number[nextNumberIdx]);
                operator[i]--;
                Inserting(operatorCnt-1, nextTotal, nextNumberIdx+1);
                operator[i]++;
            }
        }
    }

    public static int Calculate(int curTotal, int operator, int nextNumber) {
        if(operator==0) {
            return curTotal+nextNumber;
        }
        else if(operator==1) {
            return curTotal-nextNumber;
        }
        else if(operator==2) {
            return curTotal*nextNumber;
        }
        else {
            return (curTotal < 0) ? (Math.abs(curTotal)/nextNumber)*-1 : curTotal/nextNumber;
        }
    }
}
