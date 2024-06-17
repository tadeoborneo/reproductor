package Models;

import Interfaces.Reproduction;

import java.util.Queue;

public class Free extends Account implements Reproduction {
    public Free(String user, String password) {
        super(user, password);
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

}
