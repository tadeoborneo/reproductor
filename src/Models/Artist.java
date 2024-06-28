package Models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.*;

public class Artist implements Comparable<Artist>{
    private String name;
    private Integer monthlyListeners;
    private Set<Album> albums;

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

    public Set<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }

    public Artist(String name) {
        this.name = name;
        this.monthlyListeners = 0;
        this.albums = new TreeSet<>();
    }

    public Artist() {
    }

    @Override
    public String toString() {
        return "Name: "+this.getName()+
                "\nMonthly listeners: "+this.getMonthlyListeners()+
                "\nAlbums: "+this.getAlbums();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Artist artist)) return false;
        return Objects.equals(getName(), artist.getName()) && Objects.equals(getMonthlyListeners(), artist.getMonthlyListeners()) && Objects.equals(getAlbums(), artist.getAlbums());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getMonthlyListeners(), getAlbums());
    }

    @Override
    public int compareTo(Artist o) {
        if(this.getName().compareTo(o.getName()) > 0)
            return 1;
        else if (this.getName().compareTo(o.getName()) < 0)
            return -1;
        else
            return 0;
    }
}
