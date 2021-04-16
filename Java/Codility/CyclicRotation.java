package Codility;

//Lesson2. Arrays
//O(N)
public class CyclicRotation {
    public int[] solution(int[] A, int K) {
        if(K == 0 || K == A.length || A.length == 0) return A;

        int[] result = new int[A.length];

        int rotation = (A.length) - (K%A.length);
        int idx = 0;
        for(int i=rotation; i<A.length; i++) {
            result[idx++] = A[i];
        }
        for(int i=0; i<rotation; i++) {
            result[idx++] = A[i];
        }

        return result;
    }
}
