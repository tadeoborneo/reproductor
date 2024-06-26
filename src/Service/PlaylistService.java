package Service;

import Json.Playlist.PlaylistJson;
import Models.Playlist;

public class PlaylistService {
    private PlaylistJson playlistJson;

    public PlaylistJson getPlaylistJson() {
        return playlistJson;
    }

    public PlaylistService() {
        this.playlistJson = new PlaylistJson();
    }

    public void add(){

    }
}
