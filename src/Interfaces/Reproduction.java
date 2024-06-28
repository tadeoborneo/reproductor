package Interfaces;

import Models.*;

import java.util.Queue;

public interface Reproduction {
    public Song play(Queue<Song> queue);

    public Song next();

    public Song previous();

    public Song pause();

    public Song rewind();
}
