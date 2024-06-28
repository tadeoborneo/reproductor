package Json.Artist;

import Exception.Artist.ArtistNotFound;
import Interfaces.Crud;
import Models.Album;
import Models.Artist;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class ArtistJson implements Crud<Artist> {
    private static final File jsonArtist = new File("src/Json/Artist/Artist.json");
    private ObjectMapper mapper = new ObjectMapper();
    private List<Artist> artists;

    public List<Artist> getArtists() {
        return artists;
    }

    public ArtistJson() {
        this.artists = getJsonArtists();
    }

    public List<Artist> getJsonArtists() {
        try {
            if (jsonArtist.exists()) {
                return mapper.readValue(jsonArtist, new TypeReference<ArrayList<Artist>>() {
                });
            }
            return new ArrayList<>();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }
    }

    public void saveJsonArtist() {
        try {
            mapper.writeValue(jsonArtist, this.getArtists());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void add(Artist artist) {
        this.getArtists().add(artist);
    }

    @Override
    public void remove(Artist artist) {
        if (!existArtist(artist))
            this.getArtists().remove(artist);
    }

    @Override
    public void view() {
        for (Artist a : this.getArtists()) {
            System.out.println("--------------------------------------------------------");
            System.out.println(a);
            System.out.println("--------------------------------------------------------");
        }
    }

    public void updateName(String newName, Artist artist) throws ArtistNotFound {
        if (existArtist(artist)) {
            for (Artist a : this.getArtists()) {
                if (a.equals(artist)) {
                    a.setName(newName);
                }
            }
        } else
            throw new ArtistNotFound();
    }

    public void updateAlbums(Set<Album> newAlbums, Artist artist) {
        if (existArtist(artist)) {
            for (Artist a : this.getArtists()) {
                if (a.equals(artist))
                    a.setAlbums(newAlbums);
            }
        }

    }

    public Boolean existArtist(Artist artist) {
        return this.getArtists().stream().anyMatch(artist1 -> artist1.equals(artist));
    }

    public List<Artist> searchArtists(String name) {
        return this.getArtists().stream().filter(artist -> artist.getName().toLowerCase().startsWith(name.toLowerCase())).toList();
    }

}
