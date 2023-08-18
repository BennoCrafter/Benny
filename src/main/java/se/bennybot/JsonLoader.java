package se.bennybot;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileReader;

public class JsonLoader {

    public static JSONObject loadJsonFromFile(String filename) throws Exception {
        try {
            FileReader fileReader = new FileReader(filename);
            JSONTokener tokener = new JSONTokener(fileReader);
            return new JSONObject(tokener);

        }catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}