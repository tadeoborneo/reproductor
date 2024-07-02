package Controller;

import Exception.AccountException.AccountException;
import Models.Account;
import Service.AccountService;

import java.util.Scanner;

import Exception.InvalidOptionException;

public class AccountController {
    private AccountService accountService;

    public AccountService getAccountService() {
        return accountService;
    }

    public AccountController() {
        accountService = new AccountService();
    }

    public Account createAccount(Boolean premium) {
        String username;
        String password;
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Username: ");
            username = sc.nextLine();
            System.out.println("Password: ");
            password = sc.nextLine();
            return this.getAccountService().add(username, password, premium);
        } catch (AccountException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }


    public Boolean removeAccount() {
        Scanner sc = new Scanner(System.in);
        Boolean result;
        do {
            System.out.println("Search by username: ");
            String username = sc.nextLine();

            try {
                result = this.getAccountService().remove(username);
                if (result != null) {
                    return result;
                }
            } catch (AccountException | InvalidOptionException e) {
                System.out.println(e.getMessage());
            }
        } while (true);

    }

    public Account updateAccount() {
        Scanner sc = new Scanner(System.in);
        Account updatedAccount;
        do {
            System.out.println("Search account by name: ");
            String accountName = sc.nextLine();
            try {
                updatedAccount = this.getAccountService().update(accountName);
                return updatedAccount;
            } catch (AccountException | InvalidOptionException e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }
}
