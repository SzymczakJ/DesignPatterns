package org.example;

import java.io.*;
import java.net.*;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultMessenger implements Messenger {
    private ServerSocket serverSocket = null;
    private ConcurrentHashMap<String, Integer> ports = new ConcurrentHashMap();

    public void createListeningThread(int port) {
        Thread thread = new Thread(() -> {
            startListening(port);
        });
        thread.start();
    }

    public void createBroadcastListeningThread(int port, String name) {
        Thread thread = new Thread(() -> {
            startListeningToBroadcast(port, name);
        });
        thread.start();
    }

    public void broadcastIds(int ownedPort, String name, String method) throws IOException {
        for (int port = 4000; port < 10000; port++) {
            if (port != ownedPort) {
                broadcastToPort(ownedPort, port, name, method);
            }
        }
    }

    public void sendMessage(String message, String destination) throws IOException {
        InetAddress ip = InetAddress.getByName("127.0.0.1");
        if (ports.containsKey(destination)) {
            int destinationPort = ports.get(destination);
            Socket socket = new Socket(ip, destinationPort);
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

            dataOutputStream.writeUTF(message);
        }
    }

    public void sendMessageToEveryone(String message) {
        ports.forEach((key, value) -> {
            try {
                sendMessage(message, key);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void disconnect(int port, String name) throws IOException {
        broadcastIds(port, name, "delete");
        serverSocket.close();
    }

    private void startListening(int port) {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        while (true) {
            try {
                Socket socket = serverSocket.accept();
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

                System.out.println("Assigning new thread to client");
                Thread thread = new ListeningHandler(socket, dataInputStream, dataOutputStream);
                thread.start();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void startListeningToBroadcast(int ownedPort, String name) {
        try {
            DatagramSocket listeningSocket = new DatagramSocket(ownedPort);
            byte[] receivedData = new byte[100];
            DatagramPacket receivePacket = new DatagramPacket(receivedData, receivedData.length);

            while (true) {
                listeningSocket.receive(receivePacket);
                String[] receivedId = new String( receivePacket.getData(), 0, receivePacket.getLength()).split(" ");
                if (receivedId[2].equals("add")) {
                    if (!ports.containsKey(receivedId[1])) {
                        ports.put(receivedId[1], Integer.valueOf(receivedId[0]));
                        broadcastToPort(ownedPort, Integer.valueOf(receivedId[0]), name, "add");
                    }
                }
                if (receivedId[2].equals("delete")) {
                    ports.remove(receivedId[1]);
                }
//                System.out.println(ports.values());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void broadcastToPort(int ownedPort, int destinationPort, String name, String method) throws IOException {
        DatagramSocket socket = new DatagramSocket();
        socket.setBroadcast(true);

        InetAddress address = InetAddress.getByName("127.0.0.1");
        byte[] buffer = String.valueOf(ownedPort).concat(" " + name + " " + method).getBytes();

        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, destinationPort);
        socket.send(packet);
        socket.close();
    }

}
