package Json.Song;

import Interfaces.Crud;
import Models.Song;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class SongJson implements Crud<Song> {
    private ObjectMapper mapper = new ObjectMapper();
    private static final File songJson = new File("src/Json/Song/Song.json");
    private Set<Song> songs;

    public Set<Song> getSongs() {
        return songs;
    }

    public SongJson() {
        songs = getJsonSongs();
    }

    public Set<Song> getJsonSongs() {
        try {
            if (songJson.exists()) {
                return mapper.readValue(songJson, new TypeReference<TreeSet<Song>>() {
                });
            }
            return new TreeSet<>();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return new TreeSet<>();
        }
    }

    public void saveJsonSongs() {
        try {
            mapper.writeValue(songJson,this.getSongs());
        } catch (IOException e) {
            System.out.println(e.getMessage());
            ;
        }
    }

    @Override
    public void add(Song song) {
        this.getSongs().add(song);
    }

    @Override
    public void remove(Song song) {

    }

    @Override
    public void view() {

    }
}
