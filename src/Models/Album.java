package Models;

import java.util.List;

public class Album extends Playlist{
    private List<Artist> artists;
    private Integer releaseYear;

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Album(String name, List<Artist> artists, Integer releaseYear) {
        super(name);
        this.artists = artists;
        this.releaseYear = releaseYear;
    }
}
