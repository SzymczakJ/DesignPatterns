package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Parser parser = new DefaultParser();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Type a word to check if grammar accepts it or 'quit': ");
            String word = scanner.nextLine();
            if (word.equals("quit")) break;

            if (parser.parse(word)) {
                System.out.println("Word is accepted by grammar!");
            } else {
                System.out.println("Word is not accepted by grammar!");
            }
        }
    }
}