package Codility;

import java.util.HashSet;

//Lesson4. Counting Elements
//O(N)
public class FrogRiverOne {
    public int solution(int X, int[] A) {
        HashSet<Integer> s = new HashSet<>();

        for(int i=0; i<A.length; i++) {
            s.add(A[i]);
            if(s.size() == X) return i;
        }
        return -1;
    }
}
