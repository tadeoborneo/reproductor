package Json.Account;

import Interfaces.Crud;
import Models.Account;
import Models.Free;
import Models.Premium;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountJson implements Crud<Account> {
    private ObjectMapper mapper = new ObjectMapper();
    private static final File accountJson = new File("src/Json/Account/Account.json");
    private List<Account> accounts;

    public List<Account> getAccounts() {
        return accounts;
    }

    public AccountJson() {
        accounts = getJsonAccounts();
    }

    public List<Account> getJsonAccounts() {
        try {
            if (accountJson.exists()) {
                Map<String, List<Account>> accountMap = mapper.readValue(accountJson, new TypeReference<HashMap<String, List<Account>>>() {
                });
                List<Account> aux = new ArrayList<>();
                for (Map.Entry<String, List<Account>> entry : accountMap.entrySet()) {
                    if (entry.getKey().equals("premium")) {
                        for (Account a : entry.getValue()) {
                            aux.add(new Premium(a.getUser(), a.getPassword(), a.getPlaylists(),a.getAlbums()));
                        }
                    } else if (entry.getKey().equals("free")) {
                        for (Account a : entry.getValue()) {
                            aux.add(new Free(a.getUser(), a.getPassword(), a.getPlaylists(),a.getAlbums()));
                        }
                    }
                }
                return aux;
            } else
                return new ArrayList<>();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public void saveJsonAccounts() {
        try {
            Map<String, List<Account>> accountMap = new HashMap<>();
            for (Account a : this.getAccounts()) {
                if (a instanceof Free)
                    if (!accountMap.containsKey("free")) {
                        accountMap.put("free", new ArrayList<>());
                        accountMap.get("free").add(a);
                    } else
                        accountMap.get("free").add(a);
                else if (a instanceof Premium) {
                    if (!accountMap.containsKey("premium")) {
                        accountMap.put("premium", new ArrayList<>());
                        accountMap.get("premium").add(a);
                    } else
                        accountMap.get("premium").add(a);
                }
            }
            mapper.writeValue(accountJson, accountMap);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void add(Account account) {
        this.getAccounts().add(account);
    }

    @Override
    public void remove(Account account) {
        this.getAccounts().remove(account);
    }

    @Override
    public void view() {

        for (Account a : this.getAccounts()) {
            System.out.println("--------------------------------------------------------");
            System.out.println(a);
            System.out.println("--------------------------------------------------------");

        }
    }

    public void updateUsername(String newUsername, Account account) {
        for (Account a : this.getAccounts()) {
            if (a.equals(account))
                a.setUser(newUsername);
        }
    }

    public void updatePassword(String newPassword, Account account) {
        for (Account a : this.getAccounts()) {
            if (a.equals(account))
                a.setPassword(newPassword);
        }
    }

    public Boolean existUser(String username) {
        return this.getAccounts().stream().anyMatch(a -> a.getUser().equals(username));
    }

    public List<Account> searchAccounts(String user) {
        return this.getAccounts().stream().filter(account -> account.getUser().toLowerCase().startsWith(user.toLowerCase())).toList();
    }

    public Account searchAccount(String username) {
        for (Account a : this.getAccounts()) {
            if (a.getUser().toLowerCase().equals(username.toLowerCase()))
                return a;
        }
        return null;
    }
}
