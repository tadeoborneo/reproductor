import Exception.InvalidOptionException;
public class Main {
    public static void main(String[] args) {
        MusicPlayer mp = new MusicPlayer();
        try{
            mp.mainMenu();
        }catch (InvalidOptionException e){
            System.out.println(e.getMessage());
        }
    }
}