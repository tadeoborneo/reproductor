package Json.Song;

import Enums.MusicalGenre;
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
            mapper.writeValue(songJson, this.getSongs());
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
        this.getSongs().remove(song);
    }

    @Override
    public void view() {
        for (Song s : this.getSongs()) {
            System.out.println("--------------------------------------------------------");
            System.out.println(s);
            System.out.println("--------------------------------------------------------");
        }
    }

    public void updateName(String newName, Song song) {
        for (Song s : this.getSongs()) {
            if (s.equals(song))
                s.setName(newName);
        }
    }

    public void updateGenre(MusicalGenre newMusicalGenre, Song song) {
        for (Song s : this.getSongs()) {
            if (s.equals(song))
                s.setMusicalGenre(newMusicalGenre);
        }
    }

    public List<Song> searchSongs(String songName) {
        return this.getSongs().stream().filter(song -> song.getName().toLowerCase().startsWith(songName.toLowerCase())).toList();
    }
}
