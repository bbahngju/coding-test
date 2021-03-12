import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class FriendlyExpenses {
    static int[] relation;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int studentCount = Integer.parseInt(st.nextToken());
        int relationCount = Integer.parseInt(st.nextToken());
        int totalMoney = Integer.parseInt(st.nextToken());

        int[] friendlyExpenses = new int[studentCount+1]; //if studentCount = 5, 0 1 2 3 4 5

        String[] expenses = br.readLine().split(" "); // 0 1 2 3 4
        for(int i=1; i<= studentCount; i++) {
            friendlyExpenses[i] = Integer.parseInt(expenses[i-1]);
        }

        relation = relationInit(studentCount);

        String[] relationInput;
        for(int i=0; i<relationCount; i++) {
            relationInput = br.readLine().split(" ");

            int friendA = findRelation(Integer.parseInt(relationInput[0]));
            int friendB = findRelation(Integer.parseInt(relationInput[1]));

            if(friendlyExpenses[friendA] <= friendlyExpenses[friendB]) {
                friendlyExpenses[friendB] = 0;
                relation[friendB] = friendA;
            }
            else {
                friendlyExpenses[friendA] = 0;
                relation[friendA] = friendB;
            }
        }

        int totalExpenses = 0;
        for(int i=1; i<=studentCount; i++) {
            totalExpenses += friendlyExpenses[i];
        }

        if(totalExpenses <= totalMoney) System.out.println(totalExpenses);
        else System.out.println("Oh no");
    }

    public static int[] relationInit(int studentCount) {
        int[] init = new int[studentCount+1];
        for(int i=0; i<=studentCount; i++) {
            init[i] = i;
        }

        return init;
    }

    public static int findRelation(int person) {
        int current = person;
        int relationPerson = relation[person];

        while(relationPerson != current) {
            current = relationPerson;
            relationPerson = relation[current];
        }

        return relationPerson;
    }

}
