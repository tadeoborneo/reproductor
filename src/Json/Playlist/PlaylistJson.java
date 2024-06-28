package Json.Playlist;

import Interfaces.Crud;
import Models.Playlist;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PlaylistJson implements Crud<Playlist> {
    private ObjectMapper mapper = new ObjectMapper();
    private static final File jsonPlaylist = new File("src/Json/Playlist/Playlist.json");
    private Set<Playlist> playlists;

    public Set<Playlist> getPlaylists() {
        return playlists;
    }

    public PlaylistJson() {
        this.playlists = getJsonPlaylists();
    }

    public Set<Playlist> getJsonPlaylists() {
        if (jsonPlaylist.exists()) {
            try {
                return mapper.readValue(jsonPlaylist, new TypeReference<HashSet<Playlist>>() {
                });
            } catch (IOException e) {
                System.out.println(e.getMessage());
                return new HashSet<>();
            }
        } else
            return new HashSet<>();
    }

    public void saveJsonPlaylists() {
        try {
            mapper.writeValue(jsonPlaylist, this.getPlaylists());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void add(Playlist playlist) {
        this.getPlaylists().add(playlist);
    }

    @Override
    public void remove(Playlist playlist) {
        this.getPlaylists().remove(playlist);
    }

    @Override
    public void view() {
        for (Playlist p : this.getPlaylists()) {
            System.out.println("--------------------------------------------------------");
            System.out.println(p);
            System.out.println("--------------------------------------------------------");
        }
    }

    public List<Playlist> searchPlaylists(String name) {
        return this.getPlaylists().stream().filter(playlist -> playlist.getName().toLowerCase().startsWith(name.toLowerCase())).toList();
    }
}
