package Models;

import Interfaces.Reproduction;
import com.fasterxml.jackson.annotation.JsonIgnore;


import java.util.Scanner;
import java.util.Set;


public class Premium extends Account implements Reproduction {
    @JsonIgnore
    private Boolean aleatory;

    public Boolean getAleatory() {
        return aleatory;
    }

    public void setAleatory(Boolean aleatory) {
        this.aleatory = aleatory;
    }

    public Premium(String user, String password) {
        super(user, password);
        this.aleatory = false;
    }

    public Premium(String user, String password, Set<Playlist> playlists, Set<Album> albums) {
        super(user, password, playlists, albums);
        this.aleatory = false;
    }

    public Premium() {
        super();
    }

    @Override
    public void play() {
        if (!this.getSongQueue().isEmpty()) {
            Integer selection;
            Scanner sc = new Scanner(System.in);
            System.out.println("Reproducing: " + this.getSongQueue().getFirst().getName());
            do {
                System.out.println("1-Next  2-Previous  3-Stop");
                selection = sc.nextInt();
                sc.nextLine();
                if (selection.equals(1))
                    next();
                else if (selection.equals(2))
                    previous();
                else if (selection.equals(3))
                    stop();

            } while (selection < 1 || selection > 3);

        }
    }

    @Override
    public void next() {
        this.getSongQueue().addLast(this.getSongQueue().removeFirst());
        this.play();
    }

    @Override
    public void previous() {
        this.getSongQueue().addFirst(this.getSongQueue().removeLast());
        play();
    }

    @Override
    public void stop() {
        this.getSongQueue().clear();
        System.out.println("Reproduction stopped");
        play();
    }


    @Override
    public String toString() {
        return "Premium account: \n" + super.toString();
    }

}
