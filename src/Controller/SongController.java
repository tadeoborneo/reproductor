package Controller;

import Enums.MusicalGenre;
import Models.Album;
import Models.Artist;
import Service.SongService;

import java.util.*;

public class SongController {
    private SongService songService;
    private ArtistController artistController;

    public SongService getSongService() {
        return songService;
    }

    public ArtistController getArtistController() {
        return artistController;
    }

    public SongController(ArtistController artistController) {
        this.songService = new SongService();
        this.artistController = artistController;
    }

    public void /*TODO BOOLEAN */ createSong() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Name: ");
        String name = sc.nextLine();
        System.out.println("Duration (seconds): ");
        Integer secDuration = sc.nextInt();
        sc.nextLine();
        Integer genre;
        MusicalGenre musicalGenre = null;
        do {
            System.out.println("1-Rock 2-Trap 3-Rap 4-Jazz 5-Metal 6-Pop 7-Classic");
            genre = sc.nextInt();
            sc.nextLine();
            if (genre.equals(1))
                musicalGenre = MusicalGenre.ROCK;
            else if (genre.equals(2))
                musicalGenre = MusicalGenre.TRAP;
            else if (genre.equals(3))
                musicalGenre = MusicalGenre.RAP;
            else if (genre.equals(4))
                musicalGenre = MusicalGenre.JAZZ;
            else if (genre.equals(5))
                musicalGenre = MusicalGenre.METAL;
            else if (genre.equals(6))
                musicalGenre = MusicalGenre.POP;
            else if (genre.equals(7))
                musicalGenre = MusicalGenre.CLASSIC;
            else
                System.out.println("Invalid option");
        } while (musicalGenre == null);

        Set<Artist> songCreators = new TreeSet<>();
        Integer select;
        List<Artist> aux;
        do {
            System.out.println("Search artist by name: ");
            String artistName = sc.nextLine();

            aux = this.getArtistController().getArtistService().getArtistJson().searchArtists(artistName);
            if (aux.size() < 1) {
                System.out.println("The artist is not registered. Do you want to create it? 1- YES  2- NO");
                select = sc.nextInt();
                sc.nextLine();
                if (select.equals(1)) {
                    songCreators.add(this.artistController.createArtist());
                }
            } else {
                for (int i = 0; i < aux.size(); i++) {
                    System.out.println("--------------------------------------------------------");
                    System.out.println("|" + (i + 1) + "|\n" + aux.get(i));
                    System.out.println("--------------------------------------------------------");
                }
                System.out.println("Select (any other number to search again): ");
                select = sc.nextInt();
                sc.nextLine();
                if (select < 1 || select > aux.size()) {
                    select = 1;
                } else {
                    songCreators.add(aux.get(select - 1));

                }
            }
            if (songCreators.size() > 0){
                System.out.println("Do you want to select another artist?\n1- YES\n2- NO");
                select = sc.nextInt();
                sc.nextLine();
            }
        } while (select.equals(1) || songCreators.size() < 1);
        Album album;
        //TODO AGREGAR FILTRADO Y SELECCION DE ALBUMS
    }
}
