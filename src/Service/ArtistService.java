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

    public void remove(String name) throws ArtistException,InvalidOptionException {
        List<Artist> artists = this.getArtistJson().searchArtists(name);

        if (!artists.isEmpty()) {
            Integer select = select(artists);
            if (!(select < 1 || select > artists.size()))
                this.getArtistJson().remove(artists.get(select - 1));
        } else
            throw new ArtistNotFound();
    }

    public void addAlbumToArtist(Album album, Artist artist){
        this.getArtistJson().getArtists().forEach(artist1 -> {
            if (artist1.equals(artist)) {
                artist1.getAlbums().add(album);
            }
        });
    }

    public void addSongToArtist (Song song, Artist artist){
        for (Artist a : this.getArtistJson().getArtists()){
            if (a.equals(artist))
                a.getSongs().add(song);
        }
    }

}
