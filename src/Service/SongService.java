package Service;

import Exception.*;
import Exception.SongException.SongException;
import Exception.SongException.SongNotFound;
import Interfaces.Selection;
import Json.Song.SongJson;
import Models.Song;

import java.util.List;

public class SongService implements Selection<Song> {
    private SongJson songJson;

    public SongJson getSongJson() {
        return songJson;
    }

    public SongService() {
        songJson = new SongJson();
    }

    public void add (Song song){
        this.getSongJson().add(song);
    }

    public Song remove(String songName) throws SongException,InvalidOptionException{
        Integer select;
        Song song;
        List<Song> filteredSongs = this.getSongJson().searchSongs(songName);
        if (filteredSongs.isEmpty())
            throw new SongNotFound();
        else {
            select = select(filteredSongs);
            if (select.equals(0))
                return null;
            else {
                song = filteredSongs.get(select - 1);
                this.getSongJson().remove(song);
                return song;
            }
        }
    }
}
