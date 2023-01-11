package org.example;

import java.io.IOException;

public class Client {
    String name;
    private int ownedPort;
    private Messenger messenger;

    Client(Messenger messenger, int port, String name) {
        this.name = name;
        ownedPort = port;
        this.messenger = messenger;
    }

    public void connectClient() {
        try {
            messenger.createListeningThread(ownedPort);
            messenger.createBroadcastListeningThread(ownedPort, name);
            System.out.println("(debug message) Client connected.");
            broadcast();
            System.out.println("(debug message) Broadcast sent.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMessage(String message, String destination) {
        try {
            messenger.sendMessage(message, destination);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMessageToEveryone(String message) {
        messenger.sendMessageToEveryone(message);
    }

    public void disconnect() {
        try {
            messenger.disconnect(ownedPort, name);
            System.exit(0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void broadcast() throws IOException {
        messenger.broadcastIds(ownedPort , name, "add");
    }
}
