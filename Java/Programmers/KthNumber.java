package Programmers;

import java.util.*;
public class KthNumber {
    public int[] solution(int[] array, int[][] commands) {
        int[] result = new int[commands.length];
        int[] sub;
        for(int i=0; i<commands.length; i++) {
            sub = Arrays.copyOfRange(array, commands[i][0]-1, commands[i][1]);
            Arrays.sort(sub);
            result[i] = sub[commands[i][2]-1];
        }

        return result;
    }
}
