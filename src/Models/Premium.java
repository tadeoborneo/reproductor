package Models;

import Interfaces.Reproduction;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;
import java.util.Queue;


public class Premium extends Account implements Reproduction {
    @JsonIgnore
    private Boolean aleatory;

    public Boolean getAleatory() {
        return aleatory;
    }

    public void setAleatory(Boolean aleatory) {
        this.aleatory = aleatory;
    }

    public Premium(String user, String password) {
        super(user, password);
        this.aleatory = false;
    }

    public Premium(String user, String password, List<Playlist> playlists) {
        super(user, password, playlists);
    }

    public Premium() {
        super();
    }

    @Override
    public Song play(Queue<Song> queue) {
        return null;
    }

    @Override
    public Song next() {
        return null;
    }

    @Override
    public Song previous() {
        return null;
    }

    @Override
    public Song pause() {
        return null;
    }

    @Override
    public Song rewind() {
        return null;
    }

    @Override
    public String toString() {
        return "Premium account: \n" + super.toString();
    }

    public Playlist createPlaylist(String name) {
        return new Playlist(name);
    }

    public Song addToQueue(Song song) {
        this.getSongQueue().offer(song);
        return song;
    }

    public Song deleteFromQueue(Song song) {
        if (this.getSongQueue().contains(song))
            return song;
        else
            return null;
    }
}
