package Programmers;

public class encrypt {
    public static void main(String[] arg) {
        String sentence = "encrypt this sentence";
        String keyword = "something";
        int[] skip = {0,1,3,2,1,2,0,3,0,2,4,1,3};

        System.out.println(Solve(sentence, keyword, skip));
    }

    public static String Solve(String sentence, String keyword, int[] skip) {
        String[] s = sentence.split("");
        String[] k = keyword.split("");

        int Idx = 0;
        int skipIdx = 0;
        int curSkip = 0;
        String preSentence = "";
        StringBuilder answer = new StringBuilder();
        boolean isSkip = true;

        while(Idx < s.length) {
            if(skipIdx == skip.length) isSkip = false;

            if(isSkip && (curSkip == skip[skipIdx] ||
                    k[skipIdx%keyword.length()].equals(preSentence))) {
                answer.append(k[skipIdx % keyword.length()]);
                preSentence = k[skipIdx%keyword.length()];

                skipIdx++;
                curSkip = 0;
                continue;
            }

            answer.append(s[Idx]);
            preSentence = s[Idx];

            curSkip++;
            Idx++;
        }

        while(isSkip && (curSkip == skip[skipIdx] ||
                k[skipIdx%keyword.length()].equals(preSentence))) {
            answer.append(k[skipIdx % keyword.length()]);
            skipIdx++;
            curSkip = 0;
            if(skipIdx == skip.length) isSkip = false;
        }

        return answer.toString();
    }
}
