package Controller;

import Enums.MusicalGenre;
import Exception.*;
import Exception.SongException.SongException;
import Models.Album;
import Models.Artist;
import Models.Song;
import Service.SongService;

import java.util.*;

public class SongController {
    private SongService songService;
    private ArtistController artistController;
    private AlbumController albumController;

    public SongService getSongService() {
        return songService;
    }

    public AlbumController getAlbumController() {
        return albumController;
    }

    public ArtistController getArtistController() {
        return artistController;
    }

    public SongController(ArtistController artistController, AlbumController albumController) {
        this.songService = new SongService();
        this.artistController = artistController;
        this.albumController = albumController;
    }

    public void createSong() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Name: ");
        String name = sc.nextLine();
        System.out.println("Duration (seconds): ");
        Integer secDuration = sc.nextInt();
        sc.nextLine();
        Song song;

        Integer genre;
        MusicalGenre musicalGenre;
        do {
            System.out.println("1-Rock 2-Trap 3-Rap 4-Jazz 5-Metal 6-Pop 7-Classic");
            genre = sc.nextInt();
            sc.nextLine();
            musicalGenre = MusicalGenre.selectGenre(genre);
        } while (musicalGenre == null);
        Set<Artist> songCreators = new HashSet<>();
        Artist artist;
        Integer select = 0;
        do {
            artist = this.getArtistController().selectArtist();
            if (artist != null)
                songCreators.add(artist);
            if (!songCreators.isEmpty()) {
                System.out.println("Do you want to select another artist? 1-Yes  2-NO");
                select = sc.nextInt();
                sc.nextLine();
            }
        } while (songCreators.isEmpty() || select.equals(1));

        Album album = this.getAlbumController().selectAlbum();
        song = new Song(name, secDuration, musicalGenre, songCreators, album);
        this.getSongService().add(song);
        this.getAlbumController().addSongToAlbum(song);
        this.getArtistController().addSongToArtist(song);
    }

    public Song removeSong() {
        Scanner sc = new Scanner(System.in);
        Song result;
        do {
            System.out.println("Search by name: ");
            String songName = sc.nextLine();
            try {
                result = this.getSongService().remove(songName);
                if (result != null) {
                    this.getAlbumController().removeSongFromAlbum(result);
                    this.getArtistController().removeSongFromArtist(result);
                }
                return result;
            } catch (SongException | InvalidOptionException e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }

    public Song updateSong() {
        Scanner sc = new Scanner(System.in);
        Song updatedSong;
        do {
            System.out.println("Search song by name: ");
            String songName = sc.nextLine();
            try {
                updatedSong = this.getSongService().update(songName);
                if (updatedSong != null){
                    this.getAlbumController().updateSongFromAlbum(updatedSong);
                    this.getArtistController().updateSongFromArtist(updatedSong);
                }
                return updatedSong;
            } catch (SongException | InvalidOptionException e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }
}
