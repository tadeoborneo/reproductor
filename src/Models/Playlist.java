package Models;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private String name;
    private List<Song> songs;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public Playlist(String name) {
        this.name = name;
        songs = new ArrayList<>();
    }

    public Song addSong (Song song){
        this.getSongs().add(song);
        return song;
    }
}
