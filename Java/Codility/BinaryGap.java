package Codility;

//Lesson1. Iterations
//O(N)
public class BinaryGap {
    public int solution(int N) {
        boolean flag = false;
        int count = 0, maximum = 0;
        int rest;

        while(N != 0) {
            rest = N % 2;
            if(rest == 0 && flag)
                count++;
            else if(rest == 1) {
                if(!flag) {
                    flag = true;
                    continue;
                }

                maximum = Math.max(maximum, count);
                count = 0;
            }
            N /= 2;
        }

        return maximum;
    }
}
