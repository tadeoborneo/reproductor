package Models;

import Interfaces.Reproduction;

import java.util.Queue;

public class Premium extends Account implements Reproduction {
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

    public Playlist createPlaylist(String name) {
        return new Playlist(name);
    }

    public Song addToQueue (Song song) {
        this.getSongQueue().offer(song);
        return song;
    }

    public Song deleteFromQueue (Song song) {
        if(this.getSongQueue().contains(song))
            return song;
        else
            return null;
    }
}
