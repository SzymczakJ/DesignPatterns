package org.example;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        BuildDirector buildDirector = new BuildDirector();
        try {
            JSONObject jsonObject = buildDirector.buildJSONObject("/home/kubson/DesignPatterns/BuilderExample/src/main/resources/json.txt");
            System.out.println(jsonObject.properties);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}