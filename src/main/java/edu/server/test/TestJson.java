package edu.server.test;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileWriter;
import java.io.IOException;

public class TestJson {
    public static void main(String[] args) throws ParseException {

        JSONObject obj = new JSONObject();
        obj.put("name", "mkyong.com");
        obj.put("age", 100);

        JSONArray list = new JSONArray();
        list.add("msg 1");
        list.add("msg 2");
        list.add("msg 3");

        Object messages = obj.put("messages", list);

        String a = (String) obj.get("name");
        System.out.println(a);
//        JSONParser parser = new JSONParser();
//        JSONObject json = (JSONObject) parser.parse(a);
//        System.out.print(json);


    }

}


