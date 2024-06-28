package Controller;

import Exception.AccountException.AccountException;
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

    public Boolean createAccount(Boolean premium) {
        String username;
        String password;
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Username: ");
            username = sc.nextLine();
            System.out.println("Password: ");
            password = sc.nextLine();
            this.getAccountService().add(username, password, premium);
            return true;
        } catch (AccountException e) {
            System.out.println(e.getMessage());
            return false;
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
}
