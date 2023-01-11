package org.example;

import java.net.SocketTimeoutException;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Give me your name: ");
        String input = scanner.nextLine();
        int port = Integer.parseInt(args[0]);
        Client client = new Client(new DefaultMessenger(), port, input);
        client.connectClient();
        while (true) {
            System.out.println("Viable commands: send to one - 0, broadcast to everybody - 1, 2 - exit(log out)");
            System.out.println("Give me your input: ");
            input = scanner.nextLine();
            System.out.println();
            try {
                int option = Integer.parseInt(input);
                switch (option) {
                    case 0:
                        System.out.println("To who send the message: ");
                        input = scanner.nextLine();
                        String toWho = input;
                        System.out.println();
                        System.out.println("What message?: ");
                        input = scanner.nextLine();
                        System.out.println();
                        client.sendMessage(input, toWho);
                        break;
                    case 1:
                        System.out.println("What message do you want to send to everyone?: ");
                        input = scanner.nextLine();
                        System.out.println();
                        client.sendMessageToEveryone(input);
                        break;
                    case 2:
                        System.out.println("See ya");
                        client.disconnect();
                        return;
                    default:
                        System.out.println("no such option!");
                        break;
                }
            } catch (NumberFormatException error) {
                System.out.println("wrong input!");
            }
        }
    }
}