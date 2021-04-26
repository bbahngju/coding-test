package Programmers;

//hash
//완주하지 못한 선수
//O(N)
import java.util.HashMap;

public class AthleteWhoCouldNotFinish {
    public String solution(String[] participant, String[] completion) {
        HashMap<String, Integer> m = new HashMap<>();
        String result = "";

        for(String name : participant) {
            if(m.containsKey(name))
                m.put(name, m.get(name)+1);
            else
                m.put(name, 1);
        }

        for(String name : completion) {
            m.put(name, m.get(name)-1);
        }

        for(String name : participant) {
            if(m.get(name) != 0) {
                result = name;
                break;
            }
        }

        return result;
    }
}
