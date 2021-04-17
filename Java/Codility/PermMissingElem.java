package Codility;

//Lesson3. Time Complexity
//O(N)
public class PermMissingElem {
    public int solution(int[] A) {
        boolean[] check = new boolean[A.length+2];
        for(int num : A) {
            check[num] = true;
        }

        int i;
        for(i=1; i<check.length; i++) {
            if(!check[i]) break;
        }

        return i;
    }
}
