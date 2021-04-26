package Programmers;

import java.util.*;
public class Camouflage {
    public int solution(String[][] clothes) {
        HashMap<String, Integer> h = new HashMap<>();

        for (String[] clothe : clothes) {
            if (h.containsKey(clothe[1]))
                h.put(clothe[1], h.get(clothe[1]) + 1);
            else
                h.put(clothe[1], 1);
        }

        int result = 1;
        for(int value : h.values()) {
            result *= value+1;
        }

        return result-1;
    }
}
