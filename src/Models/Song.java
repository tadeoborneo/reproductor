package Models;

import Enums.MusicalGenre;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public class Song implements Comparable<Song> {
    private String name;
    private Integer secondDuration;
    private MusicalGenre musicalGenre;
    @JsonProperty
    private UUID songId;
    @JsonIgnore
    private Set<Artist> artists;
    @JsonIgnore
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

    public Set<Artist> getArtists() {
        return artists;
    }

    public void setArtists(Set<Artist> artists) {
        this.artists = artists;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        if (this.album != album) {
            this.album = album;
            if (album != null && !album.getSongs().contains(this))
                album.addSong(this);
        }

    }

    public Song(String name, Integer secondDuration, MusicalGenre musicalGenre, Set<Artist> artists, Album album) {
        this.name = name;
        this.secondDuration = secondDuration;
        this.musicalGenre = musicalGenre;
        this.songId = UUID.randomUUID();
        this.artists = artists;
        this.album = album;
    }

    public Song() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Song song)) return false;
        return Objects.equals(songId, song.songId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(songId);
    }

    @Override
    public int compareTo(Song o) {
        if (this.getName().compareTo(o.getName()) < 0)
            return -1;
        else if (this.getName().compareTo(o.getName()) > 0)
            return 1;
        else
            return 0;
    }

    @Override
    public String toString() {
        return "\nName: " + this.getName() +
                "\nSecond duration: " + this.getSecondDuration() +
                "\nMusical genre: " + this.getMusicalGenre()+"\n";
    }
}
