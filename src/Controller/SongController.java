package Controller;

import Enums.MusicalGenre;
import Models.Artist;
import Service.SongService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
            System.out.println("1- Rock 2- Trap 3- Rap 4- Jazz 5- Metal 6- Pop 7- Classic");
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
        List<Artist> songCreators = new ArrayList<>();
        do {
            String artistName = sc.nextLine();
            this.getArtistController().getArtistService().getArtistJson().searchArtists(artistName);
        }while ();
        //TODO AGREGAR UN FILTRADO DE LOS ARTISTAS EXISTENTES POR NOMBRE Y SI NO EXISTE DAR LA OPCION DE CREAR ARTISTA

    }
}
