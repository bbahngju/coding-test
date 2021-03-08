import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class GoStack {
    static ArrayList<String> programInstruction = new ArrayList<String>();
    static ArrayList<Integer> instructionNumber = new ArrayList<Integer>();
    static Stack<Integer> storeNumber = new Stack<>();

    static boolean errorCheck = false;
    static long maxLimit = (int)Math.pow(10, 9);

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            programInstruction.clear();
            instructionNumber.clear();

            while(true) {
                String[] input = br.readLine().split(" ");

                if(input[0].equals("QUIT")) return;
                if(input[0].equals("END")) break;

                programInstruction.add(input[0]);
                if(input.length == 2) instructionNumber.add(Integer.parseInt(input[1]));
            }

            int iteration = Integer.parseInt(br.readLine());

            while(iteration != 0) {
                int number = Integer.parseInt(br.readLine());
                storeNumber.push(number);

                play();
                if(errorCheck || storeNumber.size() != 1) {
                    System.out.println("ERROR");
                    storeNumber = new Stack<>();
                }
                else System.out.println(storeNumber.pop());

                iteration--;
                errorCheck = false;
            }
            System.out.println();

        }


    }

    public static void play() {
        int numberCount = 0;
        String currentInstruction;

        for(int i=0; i<programInstruction.size(); i++) {
            currentInstruction = programInstruction.get(i);

            if(currentInstruction.equals("NUM")) {
                InsertNumber(instructionNumber.get(numberCount));
                numberCount++;
            }
            else if(currentInstruction.equals("POP")) {
                if(storeNumber.isEmpty()) errorCheck = true;
                else DeleteNumber();
            }
            else if(currentInstruction.equals("INV")) {
                if(storeNumber.isEmpty()) errorCheck = true;
                else ChangeSign();
            }
            else if(currentInstruction.equals("DUP")){
                if(storeNumber.isEmpty()) errorCheck = true;
                else DuplicateNumber();
            }
            else if(currentInstruction.equals("SWP")) {
                if(storeNumber.size() < 2) errorCheck = true;
                else SwapNumber();
            }
            else if(currentInstruction.equals("ADD")) {
                if(storeNumber.size() < 2) errorCheck = true;
                else AddNumber();
            }
            else if(currentInstruction.equals("SUB")) {
                if(storeNumber.size() < 2) errorCheck = true;
                else SubNumber();
            }
            else if(currentInstruction.equals("MUL")) {
                if(storeNumber.size() < 2) errorCheck = true;
                else MultiplyNumber();
            }
            else if(currentInstruction.equals("DIV")) {
                if(storeNumber.size() < 2) errorCheck = true;
                else DivideNumber();
            }
            else if(currentInstruction.equals("MOD")) {
                if(storeNumber.size() < 2) errorCheck = true;
                else ModifyNumber();
            }
            if(errorCheck) return;
        }

        return;
    }

    public static boolean MaximumCheck(long total) {
        if(maxLimit < total || maxLimit*-1 > total)
            return true;
        return false;
    }
    public static void InsertNumber(int number) {
        storeNumber.push(number);
        return;
    }

    public static void DeleteNumber() {
        storeNumber.pop();
        return;
    }

    public static void ChangeSign() {
        int temp = storeNumber.pop();
        temp *= -1;

        storeNumber.push(temp);
        return;
    }

    public static void DuplicateNumber() {
        int temp = storeNumber.peek();
        storeNumber.push(temp);
        return;
    }

    public static void SwapNumber() {
        int firstNumber = storeNumber.pop();
        int secondNumber = storeNumber.pop();

        storeNumber.push(firstNumber);
        storeNumber.push(secondNumber);
        return;
    }

    public static void AddNumber() {
        long firstNumber = storeNumber.pop();
        long secondNumber = storeNumber.pop();
        long total = firstNumber+secondNumber;

        if(MaximumCheck(total)) errorCheck = true;
        else storeNumber.push((int)total);
        return;
    }

    public static void SubNumber() {
        long firstNumber = storeNumber.pop();
        long secondNumber = storeNumber.pop();
        long total = secondNumber-firstNumber;

        if(MaximumCheck(total)) errorCheck = true;
        else storeNumber.push((int)total);
        return;
    }

    public static void MultiplyNumber() {
        long firstNumber = storeNumber.pop();
        long secondNumber = storeNumber.pop();
        long total = secondNumber*firstNumber;

        if(MaximumCheck(total)) errorCheck = true;
        else storeNumber.push((int)total);
        return;
    }

    public static void DivideNumber() {
        long firstNumber = storeNumber.pop();
        long secondNumber = storeNumber.pop();

        int signCount = 0;
        if((int)firstNumber == 0) {
            errorCheck = true;
            return;
        }
        if(firstNumber < 0) signCount++;
        if(secondNumber < 0) signCount++;

        long total = Math.abs(secondNumber)/Math.abs(firstNumber);
        if(signCount == 1) total *= -1;

        if(MaximumCheck(total)) errorCheck = true;
        else storeNumber.push((int)total);
        return;
    }

    public static void ModifyNumber() {
        long firstNumber = storeNumber.pop();
        long secondNumber = storeNumber.pop();

        boolean secondNumberSign = false;
        if(secondNumber < 0) secondNumberSign = true;

        if((int)firstNumber == 0) {
            errorCheck = true;
            return;
        }

        long total = Math.abs(secondNumber)%Math.abs(firstNumber);
        if(secondNumberSign) total *= -1;

        if(MaximumCheck(total)) errorCheck = true;
        else storeNumber.push((int)total);
        return;
    }

}
