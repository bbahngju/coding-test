import java.io.*;
import java.util.*;

public class Main {
    public static class Player{
        int number;
        int damage;
        public Player(int number,int damage) {
            this.number = number;
            this.damage = damage;
        }
    }

    public static class Potion{
        int number;
        int heal;
        public Potion(int number, int heal) {
            this.number = number;
            this.heal = heal;
        }
    }

    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw =  new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int playerNumber = Integer.parseInt(st.nextToken());
        int potionNumber = Integer.parseInt(st.nextToken());
        int hp = Integer.parseInt(st.nextToken());

        Queue<Player> player = new LinkedList<>();
        Queue<Potion> potion = new LinkedList<>();

        int sum_damage = 0, sum_heal = 0;
        for(int i=0; i<playerNumber; i++) {
            int damage = Integer.parseInt(br.readLine());
            sum_damage += damage;
            player.add(new Player(-(i+1), damage));
        }

        for(int i=0; i<potionNumber; i++) {
            int heal = Integer.parseInt(br.readLine());
            sum_heal += heal;
            potion.add(new Potion(i+1, heal));
        }

        if(sum_damage >= sum_heal+hp) bw.write("0\n");
        else {
            StringBuilder sb = Play(player, potion, hp);
            bw.write(sb.toString());
        }

        bw.flush();
        br.close();
        bw.close();
    }

    public static StringBuilder Play(Queue<Player> player, Queue<Potion> potion, int hp) {
        int halfHp = hp/2;

        StringBuilder sb = new StringBuilder();

        Player kill;
        Potion recovery;
        while(!player.isEmpty()) {
            kill = player.poll();
            hp -= kill.damage;
            sb.append(kill.number + "\n");

            if(hp <= halfHp) {
                while(!potion.isEmpty()) {
                    recovery = potion.poll();
                    hp += recovery.heal;
                    sb.append(recovery.number + "\n");

                    if(hp > halfHp) break;
                }
            }
        }

        while(!potion.isEmpty()) {
            recovery = potion.poll();
            sb.append(recovery.number + "\n");
        }
        return sb;
    }
}