import Models.Account;
import Models.Free;
import Models.Premium;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {
        MusicPlayer mp = new MusicPlayer();
        mp.mainMenu();

        for (Account a : mp.getAccountController().getAccountService().getAll()){
            if(a instanceof Free)
                System.out.println("Pobre");
            if (a instanceof Premium)
                System.out.println("Millo");
        }
    }

}