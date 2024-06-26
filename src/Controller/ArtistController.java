package Controller;

import Exception.Artist.ArtistException;
import Models.Artist;
import Service.ArtistService;

import java.io.File;
import java.util.Scanner;

public class ArtistController {
    private ArtistService artistService;

    public ArtistService getArtistService() {
        return artistService;
    }

    public ArtistController() {
        this.artistService = new ArtistService();
    }

    public Artist createArtist (){
        Scanner sc = new Scanner(System.in);
        System.out.println("Name :");
        String name = sc.nextLine();
        Artist artist = new Artist(name);
        this.getArtistService().add(artist);
        return artist;
    }

    public void removeArtist(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Search by name: ");
        String name = sc.nextLine();
        try{
            this.getArtistService().remove(name);
        }catch (ArtistException e){
            System.out.println(e.getMessage());
        }

    }
}
