package Exception.AlbumException;

public class AlbumNotFound extends AlbumException{
    public AlbumNotFound() {
    }

    @Override
    public String getMessage() {
        return "Album not found";
    }
}
