package Codility;

//Lesson3. Time Complexity
//O(N)
public class TapeEquilibrium {
    public int solution(int[] A) {
        int left = A[0];
        int right = 0;
        for(int i=1; i<A.length; i++) right+=A[i];

        int minimum = Math.abs(left-right);

        for(int i=2; i<A.length; i++) {
            left += A[i-1];
            right -= A[i-1];
            minimum = Math.min(minimum, Math.abs(left-right));
        }

        return minimum;
    }
}
