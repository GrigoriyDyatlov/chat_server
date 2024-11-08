package ru.netology.Client;

public class User {
    private String ADDRESS;
    private int PORT;

    public User(String address, int port) {
        this.ADDRESS = address;
        this.PORT = port;
    }

    public String getADDRESS() {
        return ADDRESS;
    }

    public int getPORT() {
        return PORT;
    }

}
