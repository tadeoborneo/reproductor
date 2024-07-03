package Models;

import java.util.*;

public class Artist implements Comparable<Artist> {
    private String name;
    private Set<Album> albums;
    private Set<Song> songs;

    public Set<Song> getSongs() {
        return songs;
    }

    public void setSongs(Set<Song> songs) {
        this.songs = songs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Set<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }

    public Artist(String name) {
        this.name = name;
        this.albums = new TreeSet<>();
        this.songs = new TreeSet<>();
    }

    public Artist() {
    }

    @Override
    public String toString() {
        return "Name: " + this.getName() +
                "\nSongs: \n" + this.getSongs();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Artist artist)) return false;
        return Objects.equals(getName(), artist.getName()) && Objects.equals(getAlbums(), artist.getAlbums());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAlbums());
    }

    @Override
    public int compareTo(Artist o) {
        if (this.getName().compareTo(o.getName()) > 0)
            return 1;
        else if (this.getName().compareTo(o.getName()) < 0)
            return -1;
        else
            return 0;
    }
}
