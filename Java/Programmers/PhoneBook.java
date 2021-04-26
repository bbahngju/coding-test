package Programmers;

//hash
//전화번호 목록
//O(N)
import java.util.*;

public class PhoneBook {
    public boolean solution(String[] phone_book) {
        Arrays.sort(phone_book, (o1, o2) -> o2.length()-o1.length());

        HashSet<String> h = new HashSet<>();
        for(String num : phone_book) {
            if(h.contains(num)) return false;

            for(int i=1; i<=num.length(); i++)
                h.add(num.substring(0,i));
        }

        return true;
    }
}
