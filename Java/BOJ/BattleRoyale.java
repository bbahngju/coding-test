package BOJ;

import java.io.*;
import java.util.*;

//BOJ 19639
public class BattleRoyale {
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

        for(int i=0; i<playerNumber; i++) {
            int damage = Integer.parseInt(br.readLine());
            player.add(new Player(-(i+1), damage));
        }

        for(int i=0; i<potionNumber; i++) {
            int heal = Integer.parseInt(br.readLine());
            potion.add(new Potion(i+1, heal));
        }

        String sb = Play(player, potion, hp);
        bw.write(sb);

        bw.flush();
        br.close();
        bw.close();
    }

    public static String Play(Queue<Player> player, Queue<Potion> potion, int hp) {
        int halfHp = hp/2;

        StringBuilder sb = new StringBuilder();

        Player kill;
        Potion recovery;

        boolean check = false;
        while(!player.isEmpty()) {
            if(hp > halfHp) {
                kill = player.poll();
                hp -= kill.damage;
                sb.append(kill.number + "\n");
            }
            else if(hp <= halfHp) {
                while(!potion.isEmpty()) {
                    recovery = potion.poll();
                    hp += recovery.heal;
                    sb.append(recovery.number + "\n");

                    if(hp > halfHp) break;
                }

                if(hp <= halfHp) {
                    check = true;
                    break;
                }
            }
        }

        while(!check && !potion.isEmpty()) {
            recovery = potion.poll();
            sb.append(recovery.number + "\n");
        }

        if(!check)
            return sb.toString();
        else return "0\n";
    }
}