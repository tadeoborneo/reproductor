package Controller;

import Models.Account;
import Service.AccountService;
import Exception.*;

import java.util.List;
import java.util.Scanner;

public class AccountController {
    private AccountService accountService;

    public AccountService getAccountService() {
        return accountService;
    }

    public AccountController() {
         accountService = new AccountService();
    }

    public void createAccount(Boolean premium) {
        String username;
        String password;
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Username: ");
            username = sc.nextLine();
            System.out.println("Password: ");
            password = sc.nextLine();
            this.getAccountService().add(username,password,premium);
        } catch (AccountException e) {
            System.out.println(e.getMessage());
        }
    }


    public List<Account> getAll() {
        return this.getAccountService().getAll();
    }
}
