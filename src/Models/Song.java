package Models;

import Enums.MusicalGenre;

import java.util.List;
import java.util.Objects;

public class Song {
    private String name;
    private Integer secondDuration;
    private MusicalGenre musicalGenre;
    private List<Artist> artists;
    private Album album;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSecondDuration() {
        return secondDuration;
    }

    public void setSecondDuration(Integer secondDuration) {
        this.secondDuration = secondDuration;
    }

    public MusicalGenre getMusicalGenre() {
        return musicalGenre;
    }

    public void setMusicalGenre(MusicalGenre musicalGenre) {
        this.musicalGenre = musicalGenre;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Song(String name, Integer secondDuration, MusicalGenre musicalGenre, List<Artist> artists, Album album) {
        this.name = name;
        this.secondDuration = secondDuration;
        this.musicalGenre = musicalGenre;
        this.artists = artists;
        this.album = album;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Song song)) return false;
        return Objects.equals(getName(), song.getName()) && Objects.equals(getSecondDuration(), song.getSecondDuration()) && getMusicalGenre() == song.getMusicalGenre() && Objects.equals(getArtists(), song.getArtists()) && Objects.equals(getAlbum(), song.getAlbum());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getSecondDuration(), getMusicalGenre(), getArtists(), getAlbum());
    }
}
