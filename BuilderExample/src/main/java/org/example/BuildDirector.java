package org.example;

import com.google.gson.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class BuildDirector {
    private Builder builder = new JSONObjectBuilder();
    final TypeAdapter<JsonElement> strictAdapter = new Gson().getAdapter(JsonElement.class);
    String json;
    int stringParserStart = 1;
    int stringParserEnd = 1;

    public JSONObject buildJSONObject(String pathToFile) throws IOException {
        JSONObject jsonObject = new JSONObject();
        Path fileName = Path.of(pathToFile);
        json = Files.readString(fileName);

//        if (!isValid(json)) {
//            return jsonObject;
//        }
        jsonObject = parseJSON();

        return jsonObject;
    }

    public JSONObject parseJSON() {
        JSONObject jsonObject = new JSONObject();
        while (stringParserEnd < json.length()) {
            if (checkForEnd()) {
                stringParserStart = stringParserEnd;
                return jsonObject;
            }
            String name = findName();
            Object value = findValue();

            builder.add(jsonObject, name, value);
        }
        return jsonObject;
    }

    public boolean isValid(String json) {
        try {
            strictAdapter.fromJson(json);
        } catch (JsonSyntaxException | IOException e) {
            return false;
        }
        return true;
    }

    public String findName() {
        while (json.charAt(stringParserEnd) != ':') {
            stringParserEnd++;
        }
        String string = json.substring(stringParserStart, stringParserEnd);
        stringParserEnd++;
        stringParserStart = stringParserEnd;

        string = string.replaceAll("[{} \n]", "");
        return string;
    }

    public Object findValue() {
        while (json.charAt(stringParserEnd) == '\n' || json.charAt(stringParserEnd) == ' ') {
            stringParserStart++;
            stringParserEnd++;
        }

        if (json.charAt(stringParserEnd) == '\"') {
            stringParserStart++;
            stringParserEnd++;
            return returnStringValue();
        } else if (json.charAt(stringParserEnd) == '{'){
            return parseJSON();
        } else {
            return returnNumericValue();
        }
    }

    private String returnStringValue() {
        while (json.charAt(stringParserEnd) != '\"') {
            stringParserEnd++;
        }
        String str = json.substring(stringParserStart, stringParserEnd);
        stringParserEnd++;
        jumpOverComa();

        return str;
    }

    private Object returnNumericValue() {
        char upa = json.charAt(stringParserEnd);
        while (json.charAt(stringParserEnd) == '\n' || json.charAt(stringParserEnd) == ' ') {
            stringParserStart++;
            stringParserEnd++;
            upa = json.charAt(stringParserEnd);
        }
        while (!" \n},".contains(json.substring(stringParserEnd, stringParserEnd + 1))) {
            stringParserEnd++;
        }
        String string = json.substring(stringParserStart, stringParserEnd);
        string = string.replaceAll("[{} \n]", "");
        jumpOverComa();

        if (string.contains(".")) {
            return Double.valueOf(string);
        } else {
            return Integer.valueOf(string);
        }
    }

    private void jumpOverComa() {
        if (checkForEnd()) {
            return;
        }
        while (json.charAt(stringParserEnd) != ',') {
            stringParserEnd++;
        }
        stringParserEnd++;
        stringParserStart = stringParserEnd;
    }

    private boolean checkForEnd() {
        int index = stringParserEnd;
        while (index < json.length() && (json.charAt(index) == ' ' || json.charAt(index) == '\n')) {
            index++;
        }
        return (index < json.length() && json.charAt(index) == '}');
    }
}
