package Service;

import Json.Song.SongJson;
import Models.Song;

public class SongService {
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
}
