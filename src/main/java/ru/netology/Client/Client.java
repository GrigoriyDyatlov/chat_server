package ru.netology.Client;

import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

class Client {

    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;
    private BufferedReader inputUser;
    private final String addr;
    private final int port;
    private String nickname;
    private Date time;
    private String dtime;
    private SimpleDateFormat dt1;


    public Client(String addr, int port) {
        this.addr = addr;
        this.port = port;
        try {
            this.socket = new Socket(addr, port);
        } catch (IOException e) {
            System.err.println("Socket failed");
        }
        try {
            inputUser = new BufferedReader(new InputStreamReader(System.in));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.pressNickname();
            new ReadMsg().start();
            new WriteMsg().start();
        } catch (IOException e) {
            Client.this.downService();
        }
    }

    private void pressNickname() {
        System.out.print("Press your nick: ");
        try {
            nickname = inputUser.readLine();
            out.write("Hello " + nickname + "\n");
            out.flush();
        } catch (IOException ignored) {
        }

    }

    private void downService() {
        try {
            if (!socket.isClosed()) {
                socket.close();
                in.close();
                out.close();
            }
        } catch (IOException ignored) {
        }
    }

    private class ReadMsg extends Thread {
        @Override
        public void run() {

            String str;
            try {
                while (true) {
                    str = in.readLine();
                    if (str.equals("/exit")) {
                        Client.this.downService();
                        break;
                    }
                    System.out.println(str);
                }
            } catch (IOException e) {
                Client.this.downService();
            }
        }
    }

    public class WriteMsg extends Thread {

        @Override
        public void run() {
            while (true) {
                String userWord;
                try {
                    time = new Date();
                    dt1 = new SimpleDateFormat("HH:mm:ss");
                    dtime = dt1.format(time);
                    userWord = inputUser.readLine();
                    if (userWord.equals("/exit")) {
                        out.write("/exit");
                        out.flush();
                        Client.this.downService();
                        break;
                    } else {
                        out.write("(" + dtime + ") " + nickname + ": " + userWord + "\n"); // отправляем на сервер
                    }
                    out.flush();
                } catch (IOException e) {
                    Client.this.downService();

                }

            }
        }
    }
}

