package Service;

import Exception.AlbumException.AlbumException;
import Exception.AlbumException.AlbumNotFound;
import Interfaces.Selection;
import Json.Playlist.AlbumJson;
import Exception.InvalidOptionException;
import Models.Album;
import Models.Song;

import java.util.List;


public class AlbumService implements Selection<Album> {
    private AlbumJson albumJson;

    public AlbumJson getAlbumJson() {
        return albumJson;
    }

    public AlbumService() {
        this.albumJson = new AlbumJson();
    }

    public void add(Album album) {
        this.albumJson.add(album);
    }

    public Album remove(String albumName) throws AlbumException,InvalidOptionException {
        Integer select;
        Album album;
        List<Album> filteredAlbums = this.getAlbumJson().searchAlbums(albumName);
        if (filteredAlbums.isEmpty())
            throw new AlbumNotFound();
        else {
            select = select(filteredAlbums);
            if (select.equals(0))
                return null;
            else {
                album = filteredAlbums.get(select - 1);
                this.getAlbumJson().remove(album);
                return album;
            }
        }
    }

}
