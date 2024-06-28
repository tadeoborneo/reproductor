package Models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Playlist implements Comparable<Playlist> {
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

    public Playlist() {
    }

    public Song addSong(Song song) {
        if (!this.getSongs().contains(song)) {
            this.getSongs().add(song);
        }
        return song;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Playlist playlist)) return false;
        return Objects.equals(getName(), playlist.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getName());
    }

    @Override
    public int compareTo(Playlist o) {
        if (this.getName().compareTo(o.getName()) > 0)
            return 1;
        else if (this.getName().compareTo(o.getName()) < 0)
            return -1;
        else
            return 0;
    }

    @Override
    public String toString() {
        return "Name: " + this.getName() +
                "\nSongs:\n " + this.getSongs();
    }
}
