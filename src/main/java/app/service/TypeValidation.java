package app.service;

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
}
