package ru.netology.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Server extends Thread {

    private final Socket socket;
    private final BufferedReader in;
    private final PrintWriter out;

    public Server(Socket socket) throws IOException {
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        start();
    }

    @Override
    public void run() {
        String msg;
        try {
            while (true) {
                msg = in.readLine();
                if (msg != null) {
                    if (msg.equals("/exit")) {
                        System.out.println(Log.getLoger());
                        System.out.println("Connection closed");
                        break;
                    }

                    Log.getLoger().log(msg);
                    for (Server s : Main.serverList) {
                        s.send(msg);
                    }
                }
            }
        } catch (IOException e) {
        }
    }

    private void send(String msg) {
        out.println(msg);
    }

}
