package Models;

import Interfaces.Reproduction;

import java.util.List;

import java.util.Scanner;
import java.util.concurrent.*;

public class Free extends Account implements Reproduction {

    public Free(String user, String password) {
        super(user, password);
    }

    public Free(String user, String password, List<Playlist> playlists, List<Album> albums) {
        super(user, password, playlists, albums);
    }

    public Free() {
        super();
    }

    @Override
    public void play() {
        if (!this.getSongQueue().isEmpty()) {
            Integer selection;
            Scanner sc = new Scanner(System.in);
            System.out.println("Reproducing: " + this.getSongQueue().getFirst().getName());
            do {
                System.out.println("1-Next  2-Stop");
                selection = sc.nextInt();
                sc.nextLine();
                if (selection.equals(1)){
                    runForSeconds(()->{
                        System.out.println("Waiting 5 seconds for announcement");
                    },5);
                    next();
                }
                else if (selection.equals(2))
                    stop();

            } while (selection < 1 || selection > 2);

        }
    }

    @Override
    public void next() {
        this.getSongQueue().addLast(this.getSongQueue().removeFirst());
        this.play();
    }

    @Override
    public void previous() {

    }

    @Override
    public void stop() {
        this.getSongQueue().clear();
        System.out.println("Reproduction stopped");
        play();
    }


    @Override
    public String toString() {
        return "Free account: \n" + super.toString();
    }

    public  void runForSeconds(Runnable code, int seconds) {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        ScheduledFuture<?> future = executor.schedule(() -> {}, seconds, TimeUnit.SECONDS);

        try {
            code.run();
            future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }
}

