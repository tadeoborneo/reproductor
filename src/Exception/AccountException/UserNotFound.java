package Exception.AccountException;

public class UserNotFound extends AccountException{
    public UserNotFound() {
    }

    @Override
    public String getMessage() {
        return "User not found";
    }
}
