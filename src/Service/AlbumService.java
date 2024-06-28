package Service;

import Controller.ArtistController;
import Exception.AlbumException.AlbumException;
import Exception.AlbumException.AlbumNotFound;
import Interfaces.Selection;
import Json.Playlist.AlbumJson;
import Exception.InvalidOptionException;
import Models.Album;

import java.util.List;
import java.util.Scanner;


public class AlbumService implements Selection<Album> {
    private AlbumJson albumJson;
    private ArtistController artistController;

    public ArtistController getArtistController() {
        return artistController;
    }

    public AlbumJson getAlbumJson() {
        return albumJson;
    }

    public AlbumService(ArtistController artistController) {
        this.albumJson = new AlbumJson();
        this.artistController = artistController;
    }

    public void add(Album album) {
        this.albumJson.add(album);
    }

    public Album remove(String albumName) throws AlbumException, InvalidOptionException {
        Integer select;
        Album album;
        List<Album> filteredAlbums = this.getAlbumJson().searchAlbums(albumName);
        if (filteredAlbums.isEmpty())
            throw new AlbumNotFound();
        else {
            select = select(filteredAlbums);
            if (select.equals(0))
                return null;
            else {
                album = filteredAlbums.get(select - 1);
                this.getAlbumJson().remove(album);
                return album;
            }
        }
    }

    public Album update(String albumName) throws AlbumException, InvalidOptionException {
        Integer select;
        Integer updateSelection;
        Album updatedAlbum;
        Scanner sc = new Scanner(System.in);
        List<Album> filteredAlbums = this.getAlbumJson().searchAlbums(albumName);
        if (filteredAlbums.isEmpty())
            throw new AlbumNotFound();
        else {
            select = select(filteredAlbums);
            if (select.equals(0))
                return null;
            else {
                updatedAlbum = filteredAlbums.get(select - 1);
                do {
                    System.out.println("1- Update name");
                    System.out.println("2- Update release year");
                    System.out.println("0- Exit");
                    updateSelection = sc.nextInt();
                    sc.nextLine();
                    switch (updateSelection) {
                        case 1:
                            System.out.println("Enter a new name: ");
                            String newName = sc.nextLine();
                            this.getAlbumJson().updateName(newName, updatedAlbum);
                            break;
                        case 2:
                            System.out.println("Enter a new release year: ");
                            Integer newReleaseYear = sc.nextInt();
                            sc.nextLine();
                            this.getAlbumJson().updateReleaseYear(newReleaseYear, updatedAlbum);
                            break;
                        case 0:
                            return updatedAlbum;
                        default:
                            throw new InvalidOptionException();
                    }
                } while (true);


            }
        }
    }

}
