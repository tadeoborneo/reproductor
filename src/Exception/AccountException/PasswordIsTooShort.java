package Exception.AccountException;

public class PasswordIsTooShort extends AccountException{
    public PasswordIsTooShort() {
    }

    @Override
    public String getMessage() {
        return "Password must contain more than 4 characters";
    }
}
