package ru.netology.Client;

public class User {
    private  String address;
    private  int port;

    public User() {
    }

    public void setAddress(String address){
        this.address = address;
    }

    public void setPort(int port){
        this.port = port;
    }

    public String getADDRESS() {
        return address;
    }

    public int getPORT() {
        return port;
    }

}
