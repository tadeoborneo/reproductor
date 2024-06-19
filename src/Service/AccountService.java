package Service;

import Json.AccountJson;
import Models.Account;

import java.util.List;

import Exception.*;
import Models.Free;
import Models.Premium;

public class AccountService {
    private AccountJson jsonAcc = new AccountJson();

    public AccountJson getJsonAcc() {
        return jsonAcc;
    }

    public AccountService() {
    }

    public void add(String username, String password, Boolean premium) throws AccountException {
        if (!this.getJsonAcc().existUser(username)) {
            if (password.length() >= 4) {
                if (premium)
                    this.getJsonAcc().add(new Premium(username, password));
                else
                    this.getJsonAcc().add(new Free(username, password));
            }else
                throw new PasswordIsTooShort();
        } else
            throw new UserAlreadyExist();

    }

    public List<Account> getAll() {
        return this.getJsonAcc().getAccounts();
    }

    public void remove(Account account) {
        this.getJsonAcc().remove(account);
    }

}
