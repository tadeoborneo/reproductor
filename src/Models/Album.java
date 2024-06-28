package Models;


import java.util.Set;

public class Album extends Playlist{
    private Integer releaseYear;


    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Album(String name, Integer releaseYear) {
        super(name);
        this.releaseYear = releaseYear;
    }

    public Album() {
    }

    @Override
    public String toString() {
        return super.toString()+
                "\nRelease year: "+this.getReleaseYear();
    }

    @Override
    public Song addSong(Song song) {
        if (!this.getSongs().contains(song)){
            this.getSongs().add(song);
            song.setAlbum(this);
        }
        return song;
    }
}
