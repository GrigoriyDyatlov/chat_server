package ru.netology.Client;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        User user = readFromJson("src/main/resources/user.json");
        new Client(user.getADDRESS(), user.getPORT());
    }

    public static User readFromJson(String url) throws IOException {
        File file = new File(url);
        ObjectMapper objectMapper = new ObjectMapper();
        User user = objectMapper.readValue(file, User.class);
        return user;
    }
}


