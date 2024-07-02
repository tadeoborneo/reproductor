import Exception.InvalidOptionException;

public class Main {
    public static void main(String[] args) {
        MusicPlayer mp = new MusicPlayer();
        Integer exit = 0;
        do {
            try {
                exit = mp.mainMenu();
                mp.mainMenu();
            } catch (InvalidOptionException e) {
                System.out.println(e.getMessage());
            }
        }while (exit.equals(0));

    }
}