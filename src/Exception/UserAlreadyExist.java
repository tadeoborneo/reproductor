package Exception;

public class UserAlreadyExist extends AccountException {
    public UserAlreadyExist() {
    }

    @Override
    public String getMessage() {
        return "Username already used";
    }
}
