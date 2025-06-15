package app.service.branch_panel.ClientSimulation;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;


// we use jackson library to parse json data
/**
 *  ParserService class for handling JSON parsing and conversion.
 *  It provides methods to parse JSON strings into a map and to convert product ID and quantity into a JSON string.
 *  It uses the Jackson library for JSON processing.
 */
public class ParserService {
    public static Map<String, Integer> parseJson(String json){
        Map<String, Integer> dataMap = new HashMap<String, Integer>();
        ObjectMapper objectMapper = new ObjectMapper();


        // Parsing json text into map<string,Integer>.
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