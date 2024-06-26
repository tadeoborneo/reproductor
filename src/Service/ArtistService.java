package Service;
import Exception.Artist.ArtistException;
import Exception.Artist.ArtistNotFound;
import Json.Artist.ArtistJson;
import Models.Artist;

import java.util.List;
import java.util.Scanner;

public class ArtistService {
    private ArtistJson artistJson;

    public ArtistJson getArtistJson() {
        return artistJson;
    }

    public ArtistService() {
        this.artistJson = new ArtistJson();
    }

    public void add(Artist artist) {
        this.artistJson.add(artist);
    }

    public void remove(String name) throws ArtistException {
        List<Artist> artists = this.getArtistJson().searchArtists(name);
        Scanner sc = new Scanner(System.in);

        if (artists.size() > 0) {
            if (artists.size() == 1)
                this.getArtistJson().remove(artists.getFirst());
            else {
                for (int i = 0; i < artists.size(); i++) {
                    System.out.println("--------------------------------------------------------");
                    System.out.println("|" + (i + 1) + "|\n" + artists.get(i));
                    System.out.println("--------------------------------------------------------");

                }
                System.out.println("Press any other number to exit");
                Integer select = sc.nextInt();
                if (!(select < 1 || select > artists.size()))
                    this.getArtistJson().remove(artists.get(select - 1));
            }
        } else
            throw new ArtistNotFound();
    }
}
