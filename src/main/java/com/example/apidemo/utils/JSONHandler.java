package com.example.apidemo.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;

/**
 * Utility class for processing JSON format files
 */
public final class JSONHandler {
    private static final JSONParser parser = new JSONParser();

    private JSONHandler() {
    }

    public static ArrayList<JSONObject> getJsonData(final String data){
        return getJsonObjectsFromString(data);
    }


    private static ArrayList<JSONObject> getJsonObjectsFromString(final String data){
        return parseString(data);
    }

    private static ArrayList<JSONObject> parseString(final String data){
        ArrayList<JSONObject> jsonObjects = new ArrayList<>();
        try{
            if(parser.parse(data) instanceof JSONObject jsonObject){
                jsonObjects.add(jsonObject);
            }
            else{
                jsonObjects.addAll(getJsonObjectsFromJsonArray((JSONArray) parser.parse(data)));
            }
        }
        catch (final ParseException e){
            e.printStackTrace();
        }
        return jsonObjects;
    }

    private static ArrayList<JSONObject> getJsonObjectsFromJsonArray(final JSONArray jsonArray){
        final ArrayList<JSONObject> arrayList = new ArrayList<>();
        for(final Object array : jsonArray){
            arrayList.add((JSONObject) array);
        }
        return arrayList;
    }

}

