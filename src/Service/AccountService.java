package Service;

import Exception.AccountException.AccountException;
import Exception.AccountException.PasswordIsTooShort;
import Exception.AccountException.UserAlreadyExist;
import Exception.AccountException.UserNotFound;
import Json.Account.AccountJson;
import Models.Account;

import java.util.List;

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

    public void remove(String username) throws AccountException {
        Account account;
        if ((account = this.getJsonAcc().searchAccount(username)) != null)
            this.getJsonAcc().remove(account);
        else
            throw new UserNotFound();
    }

}
