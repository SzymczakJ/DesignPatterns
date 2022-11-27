package org.example;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ListeningHandler extends Thread{
    final DataInputStream dataInputStream;
    final DataOutputStream dataOutputStream;
    final Socket socket;

    public ListeningHandler(Socket socket, DataInputStream dataInputStream, DataOutputStream dataOutputStream) {
        this.socket = socket;
        this.dataInputStream = dataInputStream;
        this.dataOutputStream = dataOutputStream;
    }

    @Override
    public void run() {
        String received;
        while (true) {
            try {
                received = dataInputStream.readUTF();
                System.out.println(received);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
