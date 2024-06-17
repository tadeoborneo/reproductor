import Json.AccountJson;
import Models.Account;
import Models.Free;
import Models.Premium;

import java.lang.reflect.Type;
import java.util.*;

public class MusicPlayer {
    private static List<Account> accounts = AccountJson.getJsonAccounts();

    public MusicPlayer() {
        this.accounts = new ArrayList<>();
    }

    public static void mainMenu() {
        Scanner sc = new Scanner(System.in);
        Integer select = 0;
        do {
            try {
                System.out.println("1- Sign in");
                System.out.println("2- Create account");
                System.out.println("3- Sign as administrator");
                System.out.println("0- Exit");
                select = sc.nextInt();
                sc.nextLine();
                switch (select) {
                    case 1: //SIGN IN
                        System.out.println("Username: ");
                        String user = sc.nextLine();
                        String password;
                        if (AccountJson.existUser(user,accounts)) {
                            System.out.println("Password: ");
                            password = sc.nextLine();
                            Account account = AccountJson.searchAccount(user,accounts);

                            if(account.getPassword().equals(password)) {

                                if (account instanceof Free){
                                    //MENU FREE

                                }else if (account instanceof Premium){
                                    //MENU PREMIUM
                                }

                            }else
                                System.out.println("Wrong password");

                        }else
                            System.out.println("This username is not registered");
                        break;

                    case 2://CREATE ACCOUNT
                        do {
                            System.out.println("Username: ");
                            user = sc.nextLine();
                            if (AccountJson.existUser(user,accounts))//VERIFICA SI EL NOMBRE DE USUARIO ESTA USADO
                                System.out.println("Username already used");
                        }while (AccountJson.existUser(user,accounts));
                        System.out.println("Password: ");
                        password = sc.nextLine();
                        accounts.add(new Free(user,password));
                        AccountJson.saveJsonAccounts(accounts);
                        //MENU FREE
                        break;

                    case 3://SIGN AS AN ADMINISTRATOR
                        System.out.println("Key: ");
                        password = sc.nextLine();
                        if (password.equals("hola123")){
                            //MENU ADMINISTRADOR
                        }else
                            System.out.println("Wrong key");
                        break;

                    case 0:
                        break;

                    default:
                        System.out.println("Select a valid option");

                }
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("It isn't a number");
            }

        } while (select != 0);
    }

}
