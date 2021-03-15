import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class NumberOfDifferentPartsCount {
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split("");

        HashSet<String> result = new HashSet<>();
        String subS;
        for(int i=0; i<input.length; i++) {
            subS = "";
            for(int j=i; j<input.length; j++) {
                subS += input[j];

                result.add(subS);
            }
        }
        System.out.println(result.size());
    }
}
