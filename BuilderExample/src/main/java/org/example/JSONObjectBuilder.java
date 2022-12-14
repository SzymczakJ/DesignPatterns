package org.example;

import java.util.SplittableRandom;

public class JSONObjectBuilder implements Builder{

    @Override
    public void add(JSONObject jsonObject, String name, Object value) {
        jsonObject.properties.put(name, value);
    }
}
