package ru.netology.Server;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class Main {
    public static LinkedList<Server> serverList = new LinkedList<>(); // список всех нитей

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(readPort("src/main/resources/port.txt"));
        try {
            while (true) {
                Socket socket = server.accept();
                try {
                    serverList.add(new Server(socket));
                } catch (IOException e) {
                    socket.close();
                }
            }
        } finally {
            server.close();
        }
    }

    public static int readPort(String url) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(url))) {
            return Integer.parseInt(br.readLine());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return 0;
    }
}