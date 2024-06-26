package Controller;

import Service.PlaylistService;

import java.util.Scanner;

public class PlaylistController {
    private PlaylistService playlistService;

    public PlaylistService getPlaylistService() {
        return playlistService;
    }

    public PlaylistController() {
        this.playlistService = new PlaylistService();
    }

    public void createPlaylist(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Name: ");
        String name = sc.nextLine();

    }
}
