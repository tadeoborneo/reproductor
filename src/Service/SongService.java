package Service;

import Enums.MusicalGenre;
import Exception.*;
import Exception.SongException.SongException;
import Exception.SongException.SongNotFound;
import Interfaces.Selection;
import Json.Song.SongJson;
import Models.Song;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.List;
import java.util.Scanner;

public class SongService implements Selection<Song> {
    private SongJson songJson;

    public SongJson getSongJson() {
        return songJson;
    }

    public SongService() {
        songJson = new SongJson();
    }

    public void add (Song song){
        this.getSongJson().add(song);
    }

    public Song remove(String songName) throws SongException,InvalidOptionException{
        Integer select;
        Song song;
        List<Song> filteredSongs = this.getSongJson().searchSongs(songName);
        if (filteredSongs.isEmpty())
            throw new SongNotFound();
        else {
            select = select(filteredSongs);
            if (select.equals(0))
                return null;
            else {
                song = filteredSongs.get(select - 1);
                this.getSongJson().remove(song);
                return song;
            }
        }
    }

    public Song update(String songName) throws SongException,InvalidOptionException{
        Integer select;
        Integer updateSelection;
        Song updatedSong;
        Scanner sc = new Scanner(System.in);
        List<Song> filteredSong = this.getSongJson().searchSongs(songName);
        if (filteredSong.isEmpty())
            throw new SongNotFound();
        else{
            select = select(filteredSong);

            if (select.equals(0))
                return null;
            else {
                updatedSong = filteredSong.get(select - 1);
                do {
                    System.out.println("1- Update name");
                    System.out.println("2- Update genre");
                    System.out.println("0- Exit");
                    updateSelection = sc.nextInt();
                    sc.nextLine();
                    switch (updateSelection){
                        case 1:
                            System.out.println("Enter a new name: ");
                            String newName = sc.nextLine();
                            this.getSongJson().updateName(newName,updatedSong);
                            break;
                        case 2:
                            System.out.println("1-Rock 2-Trap 3-Rap 4-Jazz 5-Metal 6-Pop 7-Classic");
                            System.out.println("Select: ");
                            Integer genreSelection = sc.nextInt();
                            sc.nextLine();
                            MusicalGenre newMusicalGenre = MusicalGenre.selectGenre(genreSelection);
                            this.getSongJson().updateGenre(newMusicalGenre,updatedSong);
                            break;
                        case 0:
                            return updatedSong;
                        default:
                            throw new InvalidOptionException();
                    }
                }while (true);
            }


        }

    }
}
