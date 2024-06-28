package Json.Playlist;

import Exception.AlbumException.AlbumNotFound;
import Interfaces.Crud;
import Models.Album;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AlbumJson implements Crud<Album> {
    private ObjectMapper mapper = new ObjectMapper();
    private static final File jsonAlbum = new File("src/Json/Playlist/Album.json");
    private Set<Album> albums;

    public Set<Album> getAlbums() {
        return albums;
    }

    public AlbumJson() {
        this.albums = getJsonAlbums();
    }

    public Set<Album> getJsonAlbums() {
        if (jsonAlbum.exists()) {
            try {
                return mapper.readValue(jsonAlbum, new TypeReference<HashSet<Album>>() {
                });
            } catch (IOException e) {
                System.out.println(e.getMessage());
                return new HashSet<>();
            }
        } else
            return new HashSet<>();
    }

    public void saveJsonAlbums() {
        try {
            mapper.writeValue(jsonAlbum, this.getAlbums());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void add(Album album) {
        this.getAlbums().add(album);
    }

    @Override
    public void remove(Album album) {
        if (existAlbum(album))
            this.getAlbums().remove(album);
    }

    @Override
    public void view() {
        for (Album a : this.getAlbums()) {
            System.out.println("--------------------------------------------------------");
            System.out.println(a);
            System.out.println("--------------------------------------------------------");
        }
    }

    public void updateName(String newName, Album album) throws AlbumNotFound {
        if (existAlbum(album)) {
            for (Album a : this.getAlbums()) {
                if (a.equals(album)) {
                    a.setName(newName);
                }
            }
        } else
            throw new AlbumNotFound();
    }

    public void updateReleaseYear(Integer newReleaseYear, Album album) throws AlbumNotFound {
        if (existAlbum(album)) {
            for (Album a : this.getAlbums()) {
                if (a.equals(album)) {
                    a.setReleaseYear(newReleaseYear);
                }
            }
        } else
            throw new AlbumNotFound();
    }

    public Boolean existAlbum(Album album) {
        return this.getAlbums().stream().anyMatch(a -> a.equals(album));
    }

    public List<Album> searchAlbums(String name) {
        return this.getAlbums().stream().filter(album -> album.getName().toLowerCase().startsWith(name.toLowerCase())).toList();
    }
}

