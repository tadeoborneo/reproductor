package Service;

import Exception.InvalidOptionException;
import Exception.AccountException.AccountException;
import Exception.AccountException.PasswordIsTooShort;
import Exception.AccountException.UserAlreadyExist;
import Exception.AccountException.UserNotFound;
import Interfaces.Selection;
import Json.Account.AccountJson;
import Models.Account;

import java.util.List;

import Models.Free;
import Models.Premium;

public class AccountService implements Selection<Account> {
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
            } else
                throw new PasswordIsTooShort();
        } else
            throw new UserAlreadyExist();

    }


    public Boolean remove(String username) throws AccountException,InvalidOptionException {
        Integer select;
        List<Account> filteredAccounts = this.getJsonAcc().searchAccounts(username);
        if (filteredAccounts.isEmpty())
            throw new UserNotFound();
        else {
            select = select(filteredAccounts);
            if (select.equals(0))
                return false;
            else if (!(select < 1 || select > filteredAccounts.size())) {
                this.getJsonAcc().remove(filteredAccounts.get(select - 1));
                return true;
            } else {
                throw new InvalidOptionException();
            }
        }
    }

}
