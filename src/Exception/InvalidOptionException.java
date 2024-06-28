package Exception;

public class InvalidOptionException extends Exception{
    public InvalidOptionException() {
    }

    @Override
    public String getMessage() {
        return "Invalid option";
    }
}
