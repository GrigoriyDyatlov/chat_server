package ru.netology.Client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

public class Main {

    public static void main(String[] args) throws JsonProcessingException {
        User user = readFromJson("src/main/resources/user.json");
        new Client(user.getADDRESS(), user.getPORT());
    }

    public static User readFromJson(String url) throws JsonProcessingException {
        File file = new File(url);
        ObjectMapper objectMapper = new ObjectMapper();
        User user = objectMapper.readValue(url, User.class);
        return user;
    }
}


