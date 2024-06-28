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
import java.util.Scanner;

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


    public Boolean remove(String username) throws AccountException, InvalidOptionException {
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

    public Account update(String accountName) throws AccountException, InvalidOptionException {
        Integer select;
        Integer updateSelection;
        Account updatedAccount;
        Scanner sc = new Scanner(System.in);
        List<Account> filteredAccounts = this.getJsonAcc().searchAccounts(accountName);
        if (filteredAccounts.isEmpty())
            throw new UserNotFound();
        else {
            select = select(filteredAccounts);
            if (select.equals(0))
                return null;
            updatedAccount = filteredAccounts.get(select - 1);
            do {
                System.out.println("1- Update username");
                System.out.println("2- Update password");
                System.out.println("0- Exit");
                updateSelection = sc.nextInt();
                sc.nextLine();
                switch (updateSelection) {
                    case 1:
                        System.out.println("Enter a new username: ");
                        String newUsername = sc.nextLine();
                        this.getJsonAcc().updateUsername(newUsername, updatedAccount);
                        break;
                    case 2:
                        System.out.println("Enter a new password: ");
                        String newPassword = sc.nextLine();
                        this.getJsonAcc().updatePassword(newPassword, updatedAccount);
                        break;
                    case 0:
                        return updatedAccount;
                    default:
                        throw new InvalidOptionException();
                }

            } while (true);
        }
    }

}
