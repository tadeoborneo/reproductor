package Models;

import java.util.List;

public class Artist {
    private String name;
    private Integer monthlyListeners;
    private List<Album> albums;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMonthlyListeners() {
        return monthlyListeners;
    }

    public void setMonthlyListeners(Integer monthlyListeners) {
        this.monthlyListeners = monthlyListeners;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    public Artist(String name, Integer monthlyListeners, List<Album> albums) {
        this.name = name;
        this.monthlyListeners = monthlyListeners;
        this.albums = albums;
    }
}
