package Exception.Artist;

public class ArtistNotFound extends ArtistException{
    public ArtistNotFound() {
    }

    @Override
    public String getMessage() {
        return "Artist not found";
    }
}
