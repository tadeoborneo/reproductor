package Controller;

import Exception.AlbumException.AlbumException;
import Interfaces.Selection;
import Models.Album;
import Models.Artist;
import Exception.InvalidOptionException;
import Models.Song;
import Service.AlbumService;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class AlbumController implements Selection<Album> {
    private AlbumService albumService;
    private ArtistController artistController;

    public AlbumService getAlbumService() {
        return albumService;
    }

    public ArtistController getArtistController() {
        return artistController;
    }

    public AlbumController(ArtistController artistController) {
        this.artistController = artistController;
        this.albumService = new AlbumService(artistController);
    }

    public Album createAlbum() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Name: ");
        String name = sc.nextLine();
        Set<Artist> creators = new HashSet<>();
        Artist artist;
        Integer select = 0;
        do {
            artist = this.getArtistController().selectArtist();
            if (artist != null) {
                creators.add(artist);
            }
            if (!creators.isEmpty()) {
                System.out.println("Do you want to select another artist? 1-Yes  2-NO");
                select = sc.nextInt();
                sc.nextLine();
            }
        } while (creators.isEmpty() || select.equals(1));


        System.out.println("Release year: ");
        Integer releaseYear = sc.nextInt();
        sc.nextLine();
        Album album = new Album(name, creators, releaseYear);
        this.getArtistController().addAlbumToArtist(album);

        this.getAlbumService().add(album);
        return album;
    }

    public Album removeAlbum() {
        Scanner sc = new Scanner(System.in);
        Album result;
        do {
            System.out.println("Search by name: ");
            String albumName = sc.nextLine();
            try {
                result = this.getAlbumService().remove(albumName);
                if (result != null) {
                    for (Song s : result.getSongs()) {
                        this.removeSongFromAlbum(s);
                        this.getArtistController().removeSongFromArtist(s);
                    }
                    this.getArtistController().removeAlbumFromArtist(result);
                }
                return result;
            } catch (AlbumException | InvalidOptionException e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }

    public Album updateAlbum() {
        Scanner sc = new Scanner(System.in);
        Album updatedAlbum;
        do {
            System.out.println("Search album by name: ");
            String albumName = sc.nextLine();
            try {
                updatedAlbum = this.getAlbumService().update(albumName);
                if (updatedAlbum != null){
                    this.getArtistController().updateAlbumFromArtist(updatedAlbum);
                }
                return updatedAlbum;
            } catch (AlbumException | InvalidOptionException e) {
                System.out.println(e.getMessage());
            }
        } while (true);


    }

    public Album selectAlbum() {
        Scanner sc = new Scanner(System.in);
        Integer select;
        List<Album> aux;
        do {
            System.out.println("Search albums by name: ");
            String albumName = sc.nextLine();
            aux = this.getAlbumService().getAlbumJson().searchAlbums(albumName);

            if (aux.isEmpty()) {
                System.out.println("The album is not registered. Do you want to create it? 1- YES  2- NO");
                select = sc.nextInt();
                sc.nextLine();
                if (select.equals(1)) {
                    return this.createAlbum();
                }
            } else {
                try {
                    select = select(aux);
                    if (select.equals(0))
                        return null;
                    return aux.get(select - 1);

                } catch (InvalidOptionException e) {
                    System.out.println(e.getMessage());
                }
            }
        } while (true);
    }

    public void addSongToAlbum(Song song) {
        for (Album a : this.getAlbumService().getAlbumJson().getAlbums()) {
            if (song.getAlbum().equals(a))
                a.addSong(song);
        }
    }

    public void removeSongFromAlbum(Song song) {
        for (Album a : this.getAlbumService().getAlbumJson().getAlbums()) {
            if (a.getSongs().contains(song)) {
                a.getSongs().remove(song);
            }
        }
    }

    public void updateSongFromAlbum(Song newSong){
        for (Album a : this.getAlbumService().getAlbumJson().getAlbums()){
            for (Song s : a.getSongs()){
                if (s.equals(newSong)) {
                    s.setName(newSong.getName());
                    s.setMusicalGenre(newSong.getMusicalGenre());
                }
            }
        }
    }
}
