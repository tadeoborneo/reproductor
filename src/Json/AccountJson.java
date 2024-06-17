package Json;

import Models.Account;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.accessibility.AccessibleIcon;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AccountJson {
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final File accountJson = new File("src/Json/Account.json");

    public static List<Account> getJsonAccounts() {
        try {
            return mapper.readValue(accountJson, mapper.getTypeFactory().constructCollectionType(List.class, Account.class));
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public static void saveJsonAccounts(List<Account> accountList) {
        try {
            mapper.writeValue(accountJson, accountList);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Boolean existUser(String username, List<Account> accounts) {
        return accounts.stream().anyMatch(a -> a.getUser().equals(username));
    }

    public static Account searchAccount (String user,List<Account> accountList) {
        for (Account a : accountList) {
            if (a.getUser().equals(user))
                return a;
        }
        return null;    }
}
