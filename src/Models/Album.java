package Models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;
import java.util.Set;

public class Album extends Playlist {
    @JsonIgnore
    private Set<Artist> artists;
    private Integer releaseYear;

    public Set<Artist> getArtists() {
        return artists;
    }

    public void setArtists(Set<Artist> artists) {
        this.artists = artists;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Album(String name, Set<Artist> artists, Integer releaseYear) {
        super(name);
        this.artists = artists;
        this.releaseYear = releaseYear;
    }

    public Album() {
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nRelease year: " + this.getReleaseYear();
    }

    @Override
    public Song addSong(Song song) {
        if (!this.getSongs().contains(song)) {
            this.getSongs().add(song);
            song.setAlbum(this);
        }
        return song;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Album album)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getReleaseYear(), album.getReleaseYear());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getReleaseYear());
    }
}
