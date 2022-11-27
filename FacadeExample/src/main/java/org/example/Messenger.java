package org.example;

import java.io.IOException;

public interface Messenger {
    public void createListeningThread(int port);
    public void createBroadcastListeningThread(int port, String name);
    public void broadcastIds(int ownedPort, String name, String method) throws IOException;
    public void sendMessage(String message, String destination) throws IOException;
    public void sendMessageToEveryone(String message);
    public void disconnect(int port, String name) throws IOException;
}
