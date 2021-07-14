package BOJ;

import java.io.*;
import java.util.StringTokenizer;


//BOJ 2947
//O(10)
//시뮬, 15분
public class WoodCarving {
    public static BufferedWriter bw;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int[] numbers = new int[5];

        for(int i = 0; i < numbers.length; i++) {
            int number = Integer.parseInt(st.nextToken());
            numbers[i] = number;
        }

        compareNumber(numbers);

        bw.flush();
        bw.close();
    }

    private static void compareNumber(int[] numbers) throws IOException {
        while(true) {
            for(int i = 0; i < numbers.length-1; i++) {
                if(isChange(numbers[i], numbers[i+1])) {
                    int tmp = numbers[i];
                    numbers[i] = numbers[i+1];
                    numbers[i+1] = tmp;

                    printNumbers(numbers);
                }
            }

            if(!isRepeat(numbers)) break;
        }
    }

    private static boolean isRepeat(int[] numbers) {
        for(int i = 0; i < numbers.length-1; i++) {
            if(numbers[i] > numbers[i+1]) {
                return true;
            }
        }

        return false;
    }

    private static boolean isChange(int first, int last) {
        return first > last;
    }

    private static void printNumbers(int[] numbers) throws IOException{
        for(int number : numbers) {
            bw.write(number + " ");
        }
        bw.newLine();
    }
}
