package app.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * TypeValidation class provides methods to validate input types.
 */
public class TypeValidation {
    
    /**
     * Validates if the given string can be parsed as an integer.
     * If the string is not a valid integer, it shows an error alert.
     *
     * @param num the string to validate
     * @return the parsed integer, or null if the input is invalid
     */
    public static Integer intValidation(String num){
        Integer result;        
        try {
            result = Integer.parseInt(num);
        } catch (Exception e){
            result = null;
        }
        return result;
    }   

    /**
     * Validates if the given string is a valid date-time format.
     * The expected format is "YYYY-MM-DD HH:MM:SS".
     *
     * @param input the string to validate
     * @return true if the input is a valid date-time, false otherwise
     */
    public static boolean isValidDateTime(String input) {
        String regex = "^(\\d{4})-(\\d{2})-(\\d{2}) (\\d{2}):(\\d{2}):(\\d{2})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        if (!matcher.matches()) return false;

        int year = Integer.parseInt(matcher.group(1));
        int month = Integer.parseInt(matcher.group(2));
        int day = Integer.parseInt(matcher.group(3));
        int hour = Integer.parseInt(matcher.group(4));
        int minute = Integer.parseInt(matcher.group(5));
        int second = Integer.parseInt(matcher.group(6));

        System.out.println(hour);
        System.out.println(minute);
        System.out.println(second);

        return year >= 0 && year <= 2025 &&
            month >= 1 && month <= 12 &&
            day >= 1 && day <= 31 &&
            hour >= 0 && hour <= 23 &&
            minute >= 0 && minute <= 59 &&
            second >= 0 && second <= 59;
    }











}
