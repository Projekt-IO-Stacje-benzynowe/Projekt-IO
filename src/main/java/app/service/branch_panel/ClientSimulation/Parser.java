package app.service.branch_panel.ClientSimulation;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class Parser {
    public static Map<String, Integer> parseJson(String json){
        Map<String, Integer> dataMap = new HashMap<String, Integer>();
        ObjectMapper objectMapper = new ObjectMapper();

        try{
        dataMap = objectMapper.readValue(json, HashMap.class);
        }catch(Exception e){
            System.out.println("Error while mapping json with Jackson" + e);
        }
        
        return dataMap;
    }
    public static String toJson(int productID, int quantity) {
        return String.format("{\"productID\":%d, \"quantity\":%d}", productID, quantity);
    }
}
