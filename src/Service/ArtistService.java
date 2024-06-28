package Service;

import Exception.Artist.ArtistException;
import Exception.Artist.ArtistNotFound;
import Interfaces.Selection;
import Json.Artist.ArtistJson;
import Models.Album;
import Models.Artist;
import Exception.InvalidOptionException;
import Models.Song;

import java.util.List;
import java.util.Scanner;
import java.util.Set;


public class ArtistService implements Selection<Artist> {
    private ArtistJson artistJson;

    public ArtistJson getArtistJson() {
        return artistJson;
    }

    public ArtistService() {
        this.artistJson = new ArtistJson();
    }

    public void add(Artist artist) {
        this.artistJson.add(artist);
    }

    public void remove(String name) throws ArtistException, InvalidOptionException {
        List<Artist> artists = this.getArtistJson().searchArtists(name);

        if (!artists.isEmpty()) {
            Integer select = select(artists);
            if (!(select < 1 || select > artists.size()))
                this.getArtistJson().remove(artists.get(select - 1));
        } else
            throw new ArtistNotFound();
    }

    public Artist update(String artistName) throws ArtistException, InvalidOptionException {
        Integer select;
        Integer updateSelection;
        Artist updatedArtist;
        Scanner sc = new Scanner(System.in);
        List<Artist> filteredArtists = this.getArtistJson().searchArtists(artistName);
        if (filteredArtists.isEmpty())
            throw new ArtistNotFound();
        else {
            select = select(filteredArtists);
            if (select.equals(0))
                return null;
            else {
                updatedArtist = filteredArtists.get(select - 1);
                System.out.println("1- Update name");
                System.out.println("0- Exit");
                updateSelection = sc.nextInt();
                sc.nextLine();
                switch (updateSelection) {
                    case 1:
                        System.out.println("Enter a new name: ");
                        String newName = sc.nextLine();
                        this.getArtistJson().updateName(newName, updatedArtist);
                        break;
                    case 0:
                        break;
                    default:
                        throw new InvalidOptionException();
                }
                return updatedArtist;
            }
        }
    }

    public void addAlbumToArtist(Album album, Artist artist) {
        this.getArtistJson().getArtists().forEach(artist1 -> {
            if (artist1.equals(artist)) {
                artist1.getAlbums().add(album);
            }
        });
    }

    public void addSongToArtist(Song song, Artist artist) {
        for (Artist a : this.getArtistJson().getArtists()) {
            if (a.equals(artist))
                a.getSongs().add(song);
        }
    }

    public void updateAlbumFromArtist(Set<Album> albums, Artist artist) {
        this.getArtistJson().updateAlbums(albums, artist);
    }

}
