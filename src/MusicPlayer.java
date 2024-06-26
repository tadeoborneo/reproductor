import Controller.AccountController;
import Controller.ArtistController;
import Controller.AlbumController;
import Controller.SongController;
import Interfaces.Selection;
import Models.*;
import Exception.InvalidOptionException;

import java.util.*;

public class MusicPlayer implements Selection {
    private final AccountController accountController;
    private final SongController songController;
    private final ArtistController artistController;
    private final AlbumController albumController;
    public static final Scanner sc = new Scanner(System.in);

    public AccountController getAccountController() {
        return accountController;
    }

    public SongController getSongController() {
        return songController;
    }

    public ArtistController getArtistController() {
        return artistController;
    }

    public AlbumController getAlbumController() {
        return albumController;
    }

    public MusicPlayer() {
        this.accountController = new AccountController();
        this.artistController = new ArtistController();
        this.albumController = new AlbumController(this.getArtistController());
        this.songController = new SongController(this.getArtistController(), this.getAlbumController());
    }

    public void mainMenu() throws InvalidOptionException {
        Integer select = 0;
        while (true) {
            try {
                System.out.println("1- Sign in");
                System.out.println("2- Create account");
                System.out.println("3- Sign as administrator");
                System.out.println("0- Exit");
                System.out.print("Selection: ");
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
                                        menuFree((Free)account);
                                        System.out.println(account);
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
                        throw new InvalidOptionException();

                }
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("It isn't a number");
                sc.nextLine();
            } finally {
                this.getAccountController().getAccountService().getJsonAcc().saveJsonAccounts();
                this.getSongController().getSongService().getSongJson().saveJsonSongs();
                this.getArtistController().getArtistService().getArtistJson().saveJsonArtist();
                this.getAlbumController().getAlbumService().getAlbumJson().saveJsonAlbums();
            }
        }
    }

    public void menuFree(Free account)throws InvalidOptionException {
        Integer select = 0;
        while (true) {
            try {
                System.out.println("1- Play");
                System.out.println("2- Search albums");
                System.out.println("3- My playlists");
                System.out.println("0- Exit");
                System.out.print("Selection: ");
                select = sc.nextInt();
                sc.nextLine();
                switch (select) {
                    case 1:
                        account.viewPlaylists();
                        Integer playSelection = select(account.getPlaylists());
                        Playlist selectedPlaylist = account.getPlaylists().get(playSelection - 1);
                        
                        break;
                    case 2:
                        this.getAlbumController().getAlbumService().getAlbumJson().view();
                        Integer selectAlbum;
                        System.out.println("Search by name: ");
                        String albumName = sc.nextLine();
                        List<Album> filteredAlbums = this.getAlbumController().getAlbumService().getAlbumJson().searchAlbums(albumName);
                        selectAlbum = select(filteredAlbums);
                        account.addPlaylist(filteredAlbums.get(selectAlbum - 1));
                        break;
                    case 3:
                        account.viewPlaylists();
                        break;
                    case 0://EXIT
                        return;

                    default:
                        throw new InvalidOptionException();

                }
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("It isn't a number");
                sc.nextLine();
            }
        }
    }

    public void menuAdmin()throws InvalidOptionException {
        Integer select = 0;
        while (true) {
            try {
                System.out.println("1- Account");
                System.out.println("2- Song");
                System.out.println("3- Album");
                System.out.println("4- Artist");
                System.out.println("0- Exit");
                System.out.print("Selection: ");
                select = sc.nextInt();
                sc.nextLine();
                switch (select) {
                    case 1://ACCOUNT
                        accountAdmin();
                        break;
                    case 2://SONG
                        songAdmin();
                        break;
                    case 3://ALBUM
                        albumAdmin();
                        break;
                    case 4://ARTIST
                        artistAdmin();
                        break;
                    case 0://EXIT
                        return;

                    default:
                        throw new InvalidOptionException();

                }
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("It isn't a number");
                sc.nextLine();
            }
        }
    }

    public void albumAdmin() throws InvalidOptionException{
        Integer select = 0;
        while (true) {
            try {
                System.out.println("1- Create album");
                System.out.println("2- Delete album");
                System.out.println("3- Update album");
                System.out.println("4- View album");
                System.out.println("0- Exit");
                System.out.print("Selection: ");
                select = sc.nextInt();
                sc.nextLine();
                switch (select) {
                    case 1://CREATE ALBUM
                        this.getAlbumController().createAlbum();
                        break;
                    case 2://DELETE ALBUM
                        Album album;
                        this.getAlbumController().getAlbumService().getAlbumJson().view();
                        album = this.getAlbumController().removeAlbum();
                        if (album != null) {
                            for (Song s : album.getSongs()) {
                                this.getSongController().getSongService().getSongJson().remove(s);
                            }
                        }
                        break;
                    case 3://UPDATE ALBUM
                        this.getAlbumController().getAlbumService().getAlbumJson().view();
                        this.getAlbumController().updateAlbum();
                        break;
                    case 4://VIEW ALBUM
                        this.getAlbumController().getAlbumService().getAlbumJson().view();
                        break;
                    case 0://EXIT
                        return;

                    default:
                        throw new InvalidOptionException();

                }
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("It isn't a number");
                sc.nextLine();
            }
        }
    }

    public void artistAdmin() throws InvalidOptionException{
        Integer select = 0;
        while (true) {
            try {
                System.out.println("1- Create artist");
                System.out.println("2- Delete artist");
                System.out.println("3- Update artist");
                System.out.println("4- View artists");
                System.out.println("0- Exit");
                System.out.print("Selection: ");
                select = sc.nextInt();
                sc.nextLine();
                switch (select) {
                    case 1://CREATE ARTIST
                        this.getArtistController().createArtist();
                        break;
                    case 2://DELETE ARTIST
                        this.getArtistController().removeArtist();
                        break;
                    case 3://UPDATE ARTIST
                        this.getArtistController().getArtistService().getArtistJson().view();
                        this.getArtistController().updateArtist();
                        break;
                    case 4://VIEW ARTIST
                        this.getArtistController().getArtistService().getArtistJson().view();
                        break;
                    case 0://EXIT
                        return;

                    default:
                        throw new InvalidOptionException();

                }
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("It isn't a number");
                sc.nextLine();
            }
        }
    }

    public void songAdmin()throws InvalidOptionException {
        Integer select = 0;
        while (true) {
            try {
                System.out.println("1- Create song");
                System.out.println("2- Delete song");
                System.out.println("3- Update song");
                System.out.println("4- View songs");
                System.out.println("0- Exit");
                System.out.print("Selection: ");
                select = sc.nextInt();
                sc.nextLine();
                switch (select) {
                    case 1://CREATE SONG
                        this.getSongController().createSong();
                        break;
                    case 2://DELETE SONG
                        this.getSongController().getSongService().getSongJson().view();
                        this.getSongController().removeSong();
                        break;
                    case 3://UPDATE SONG
                        this.getSongController().getSongService().getSongJson().view();
                        this.getSongController().updateSong();
                        break;
                    case 4://VIEW SONG
                        this.getSongController().getSongService().getSongJson().view();
                        break;
                    case 0://EXIT
                        return;

                    default:
                        throw new InvalidOptionException();

                }
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("It isn't a number");
                sc.nextLine();
            }
        }
    }

    public void accountAdmin() throws InvalidOptionException{
        Integer select = 0;
        while (true) {
            try {
                System.out.println("1- Create account");
                System.out.println("2- Delete account");
                System.out.println("3- Update account");
                System.out.println("4- View accounts");
                System.out.println("0- Exit");
                System.out.print("Selection: ");
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
                        this.getAccountController().getAccountService().getJsonAcc().view();
                        this.getAccountController().removeAccount();
                        break;
                    case 3://UPDATE ACCOUNT
                        this.getAccountController().getAccountService().getJsonAcc().view();
                        this.getAccountController().updateAccount();
                        break;
                    case 4://VIEW ACCOUNTS
                        this.getAccountController().getAccountService().getJsonAcc().view();
                        break;
                    case 0://EXIT
                        return;

                    default:
                        throw new InvalidOptionException();

                }
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("It isn't a number");
                sc.nextLine();
            }
        }
    }
}
