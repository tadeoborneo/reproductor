package Exception.SongException;

import Models.Song;

public class SongNotFound extends SongException {
    public SongNotFound() {
    }

    @Override
    public String getMessage() {
        return "Song not found";
    }
}
