package Service;

import Json.AccountJson;
import Models.Account;
import Models.Free;
import Models.Premium;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Scanner;

public class AccountService {
    private static List<Account> accounts = AccountJson.getJsonAccounts();
    private static final Scanner sc = new Scanner(System.in);

    public static Account add() {
        String username;
        do {
            System.out.println("Username: ");
            username = sc.nextLine();
            if (AccountJson.existUser(username,accounts)) //COMPRUEBA SI EL NOMBRE DE USUARIO YA ESTA USADO EN EL A
                System.out.println("Username already used");
        } while (AccountJson.existUser(username,accounts));
        System.out.println("Password: ");
        String password = sc.nextLine();
        Integer selection;

        do {
            System.out.println("1- Premium  2- Free");
            selection = sc.nextInt();
            if (!(selection.equals(1) || selection.equals(2)))
                System.out.println("Select a valid number");

        } while (!(selection.equals(1) || selection.equals(2)));
        if (selection.equals(1)) {
            return new Premium(username, password);
        } else if (selection.equals(2)) {
            return new Free(username, password);
        }
        return null;
    }
}
