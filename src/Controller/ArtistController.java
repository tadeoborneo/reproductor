package Controller;

import Exception.Artist.ArtistException;
import Interfaces.Selection;
import Models.Album;
import Models.Artist;
import Models.Song;
import Service.ArtistService;

import Exception.InvalidOptionException;

import java.util.List;
import java.util.Scanner;


public class ArtistController implements Selection<Artist> {
    private ArtistService artistService;

    public ArtistService getArtistService() {
        return artistService;
    }

    public ArtistController() {
        this.artistService = new ArtistService();
    }

    public Artist createArtist() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Name :");
        String name = sc.nextLine();
        Artist artist = new Artist(name);
        this.getArtistService().add(artist);
        return artist;
    }

    public void removeArtist() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Search by name: ");
        String name = sc.nextLine();
        try {
            this.getArtistService().remove(name);
        } catch (ArtistException | InvalidOptionException e) {
            System.out.println(e.getMessage());
        }
    }

    public Artist selectArtist() {
        Scanner sc = new Scanner(System.in);
        Integer select;
        List<Artist> aux;

        do {
            System.out.println("Search artist by name: ");
            String artistName = sc.nextLine();
            aux = this.getArtistService().getArtistJson().searchArtists(artistName);

            if (aux.isEmpty()) {
                System.out.println("The artist is not registered. Do you want to create it? 1- YES  2- NO");
                select = sc.nextInt();
                sc.nextLine();
                if (select.equals(1)) {
                    return this.createArtist();
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

    public void addAlbumToArtist(Album album) {
        for (Artist a : album.getArtists()){
            this.getArtistService().addAlbumToArtist(album,a);
        }
    }

    public void addSongToArtist(Song song){
        for (Artist a : song.getArtists()){
            this.getArtistService().addSongToArtist(song,a);
        }
    }

    public void removeAlbumFromArtist(Album album){
        for (Artist a : this.getArtistService().getArtistJson().getArtists()){
            if (a.getAlbums().contains(album)){
                a.getAlbums().remove(album);
            }
        }
    }
    public void removeSongFromArtist(Song song){
        for (Artist a : this.getArtistService().getArtistJson().getArtists()){
            if (a.getSongs().contains(song))
                a.getSongs().remove(song);
            for (Album album : a.getAlbums()){
                if (album.getSongs().contains(song))
                    album.getSongs().remove(song);
            }
        }
    }
}
