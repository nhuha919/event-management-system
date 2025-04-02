/**
 * Exception class to handle Format Mismatch Exception for Date, Time, and Event
 * Extends from Exception class
 * @author Nhu Ha
 * @version 3.0
 */
public class FormatMismatchException extends Exception{
    /**
     * Default constructor
     */
    public FormatMismatchException(){
        super("Invalid Format");
    }

    /**
     * 1-parameter constructor 
     * @param message value of the message
     */
    public FormatMismatchException(String message){
        super(message);
    }
}
