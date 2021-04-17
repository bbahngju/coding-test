package Codility;

//Lesson3. Time Complexity
//O(1)
public class FrogJmp {
    public int solution(int X, int Y, int D) {
        int dif = Y-X;
        int cnt = dif/D;
        if(dif%D != 0) cnt++;

        return cnt;
    }
}
