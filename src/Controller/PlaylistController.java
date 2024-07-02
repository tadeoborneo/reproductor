package Controller;

import Models.Playlist;
import Models.Song;
import Service.PlaylistService;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class PlaylistController {
    private PlaylistService playlistService;

    public PlaylistService getPlaylistService() {
        return playlistService;
    }

    public PlaylistController() {
        this.playlistService = new PlaylistService();
    }

    public Playlist createPlaylist(){
        Scanner sc = new Scanner(System.in);
        Playlist playlist;
        System.out.println("Playlist name: ");
        String playlistName = sc.nextLine();
        playlist = new Playlist(playlistName);
        this.getPlaylistService().add(playlist);
        return playlist;

    }
}
