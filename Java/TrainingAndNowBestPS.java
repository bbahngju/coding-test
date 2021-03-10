import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TrainingAndNowBestPS {
    static int[] limited;
    static int[] award;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int festivalCount = Integer.parseInt(br.readLine());

        limited = new int[festivalCount];
        award = new int[festivalCount];
        for(int i=0; i<festivalCount; i++) {
            String[] festival = br.readLine().split(" ");

            limited[i] = Integer.parseInt(festival[0]);
            award[i] = Integer.parseInt(festival[1]);
        }

        boolean result = Availability(festivalCount);

        if(result) System.out.println("Kkeo-eok");
        else System.out.println("Zzz");
    }

    public static boolean Availability(int festivalCount) {
        long totalAward = 0;
        int maxAward = 0;
        boolean tolerance = true;

        for(int i=0; i<festivalCount; i++) {
            if(limited[i] >= totalAward) {
                totalAward += award[i];
                maxAward = Math.max(maxAward, award[i]);
            }
            else {
                if(tolerance) {
                    if(totalAward-maxAward <= limited[i]) {
                        if(maxAward > award[i]) {
                            totalAward -= maxAward;
                            totalAward += award[i];
                        }
                    }
                    tolerance = false;
                }
                else return false;
            }
        }

        return true;
    }
}
