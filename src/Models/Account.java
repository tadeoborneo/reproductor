package Models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.*;

public class Account {
    private String user;
    private String password;
    private Set<Playlist> playlists;
    private Set<Album> albums;
    @JsonIgnore
    private Deque<Song> songQueue;

    public Set<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }

    public Set<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(Set<Playlist> playlists) {
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

    public Account(String user, String password, Set<Playlist> playlists, Set<Album> albums) {
        this.user = user;
        this.password = password;
        this.playlists = playlists;
        this.albums = albums;
        this.songQueue = new ArrayDeque<>();
    }

    public Account(String user, String password) {
        this.user = user;
        this.password = password;
        this.playlists = new HashSet<>();
        this.albums = new HashSet<>();
        this.songQueue = new ArrayDeque<>();
    }

    public void viewPlaylists(){
        if (this.getPlaylists().isEmpty())
            System.out.println("Empty");
        else {
            for (Playlist p : this.getPlaylists()){
                System.out.println("--------------------------------------------------------");
                System.out.println(p);
                System.out.println("--------------------------------------------------------");
            }
        }
    }

    public void viewAlbums (){
        if (this.getAlbums().isEmpty())
            System.out.println("Empty");
        else {
            for (Album a : this.getAlbums()){
                System.out.println("--------------------------------------------------------");
                System.out.println(a);
                System.out.println("--------------------------------------------------------");
            }
        }
    }

    @Override
    public String toString() {
        return "Username: " + this.getUser() +
                "\nPassword: " + this.getPassword();

    }

    public List<Album> searchAlbums(String name){
        return this.getAlbums().stream().filter(albums-> albums.getName().toLowerCase().startsWith(name.toLowerCase())).toList();
    }

    public List<Playlist> searchPlaylist(String name){
        return this.getPlaylists().stream().filter(albums-> albums.getName().toLowerCase().startsWith(name.toLowerCase())).toList();
    }
}
