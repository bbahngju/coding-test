package BOJ;

import java.io.*;
import java.util.*;

//BOJ 1302
//O(N)
public class Bestseller {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Map<String, Integer> books = new HashMap<>();
        int n = Integer.parseInt(br.readLine());

        for(int i = 0; i < n; i++) {
            String book = br.readLine();
            books.put(book, books.getOrDefault(book, 0) + 1);
        }

        PriorityQueue<Book> ranking = new PriorityQueue<>();
        for(String b : books.keySet()) {
            ranking.add(new Book(b, books.get(b)));
        }

        if(!ranking.isEmpty())
            bw.write(ranking.peek().name + "");

        bw.flush();
        bw.close();
        br.close();
    }

    public static class Book implements Comparable<Book>{
        String name;
        int cnt;

        public Book(String name, int cnt) {
            this.name = name;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Book o) {
            if(this.cnt == o.cnt) {
                return this.name.compareTo(o.name);
            }
            return o.cnt - this.cnt;
        }
    }
}
