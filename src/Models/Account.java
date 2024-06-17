package Models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.*;

public class Account {
    private String user;
    private String password;
    private List<Playlist> playlists = new ArrayList<>();
    @JsonIgnore
    private Deque<Song> songQueue = new ArrayDeque<>();

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }

    public Deque<Song> getSongQueue() {
        return songQueue;
    }

    public void setSongQueue(Deque<Song> songQueue) {
        this.songQueue = songQueue;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Account() {
    }

    public Account(String user, String password) {
        this.user = user;
        this.password = password;
        this.playlists = new ArrayList<>();
        this.songQueue = new ArrayDeque<>();
    }

    @Override
    public String toString() {
        return "Account{" +
                "user='" + user + '\'' +
                ", password='" + password + '\'' +
                ", playlists=" + playlists +
                ", songQueue=" + songQueue +
                '}';
    }

    public Playlist addPlaylist(Playlist playlist) {
        this.getPlaylists().add(playlist);
        return playlist;
    }
}
