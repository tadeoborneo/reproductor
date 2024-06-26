package Models;

import Interfaces.Reproduction;

import java.util.List;
import java.util.Queue;
public class Free extends Account implements Reproduction {

    public Free(String user, String password) {
        super(user, password);
    }

    public Free(String user, String password, List<Playlist> playlists) {
        super(user, password, playlists);
    }

    public Free() {
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
        return "Free account: \n" + super.toString();
    }

}
