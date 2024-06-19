import Controller.AccountController;
import Json.AccountJson;
import Models.Account;
import Models.Free;
import Models.Premium;

import java.lang.reflect.Type;
import java.util.*;

public class MusicPlayer {
    private AccountController accountController;
    public static final Scanner sc = new Scanner(System.in);

    public AccountController getAccountController() {
        return accountController;
    }

    public MusicPlayer() {
        accountController = new AccountController();
    }

    public void mainMenu() {
        Integer select = 0;
        while (true) {
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
                        if (this.getAccountController().getAccountService().getJsonAcc().existUser(user)) {
                            System.out.println("Password: ");
                            password = sc.nextLine();
                            Account account = this.getAccountController().getAccountService().getJsonAcc().searchAccount(user);
                            if (!account.equals(null)) {
                                if (account.getPassword().equals(password)) {

                                    if (account instanceof Free) {
                                        //MENU FREE

                                    } else if (account instanceof Premium) {
                                        //MENU PREMIUM
                                    }
                                } else
                                    System.out.println("Wrong password");
                            }

                        } else
                            System.out.println("This username is not registered");
                        break;

                    case 2://CREATE ACCOUNT
                        this.getAccountController().createAccount(false);
                        //MENU FREE
                        break;

                    case 3://SIGN AS AN ADMINISTRATOR
                        System.out.println("Key: ");
                        password = sc.nextLine();
                        if (password.equals("hola123")) {
                            menuAdmin();
                        } else
                            System.out.println("Wrong key");
                        break;

                    case 0:
                        sc.close();
                        return;

                    default:
                        System.out.println("Select a valid option");

                }
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("It isn't a number");
                sc.nextLine();
            } finally {
                this.getAccountController().getAccountService().getJsonAcc().saveJsonAccounts();
            }
        }
    }

    public void menuAdmin() {
        Integer select = 0;
        while (true) {
            try {
                System.out.println("1- Account");
                System.out.println("2- Song");
                System.out.println("3- Playlist");
                System.out.println("4- Artist");
                System.out.println("0- Exit");
                select = sc.nextInt();
                sc.nextLine();
                switch (select) {
                    case 1://ACCOUNT
                        accountAdmin();
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 0:
                        return;

                    default:
                        System.out.println("Select a valid option");

                }
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("It isn't a number");
                sc.nextLine();
            }
        }
    }

    public void accountAdmin() {
        Integer select = 0;
        while (true) {
            try {
                System.out.println("1- Create account");
                System.out.println("2- Delete account");
                System.out.println("3- Update account");
                System.out.println("0- Exit");
                select = sc.nextInt();
                sc.nextLine();
                switch (select) {
                    case 1://CREATE ACCOUNT
                        Integer option = 0;
                        do {
                            System.out.println("1- Premium\n2- Free");
                            option = sc.nextInt();
                            sc.nextLine();
                        } while (option != 1 && option != 2);
                        if (option.equals(1)) {
                            this.getAccountController().createAccount(true);
                        } else if (option.equals(2)) {
                            this.getAccountController().createAccount(false);
                        }
                        break;
                    case 2://DELETE ACCOUNT
                        System.out.println(this.getAccountController().getAll());
                        System.out.println("Select by name: ");
                        String username = sc.nextLine();
                        break;
                    case 3:
                        break;
                    case 0:
                        return;

                    default:
                        System.out.println("Select a valid option");

                }
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("It isn't a number");
                sc.nextLine();
            }
        }
    }
}
