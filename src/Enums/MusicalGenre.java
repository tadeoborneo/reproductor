package Enums;

public enum MusicalGenre {
    ROCK, TRAP, RAP, JAZZ, METAL, POP, CLASSIC;

    public static MusicalGenre selectGenre(Integer selection) {
        if (selection.equals(1))
            return MusicalGenre.ROCK;
        else if (selection.equals(2))
            return MusicalGenre.TRAP;
        else if (selection.equals(3))
            return MusicalGenre.RAP;
        else if (selection.equals(4))
            return MusicalGenre.JAZZ;
        else if (selection.equals(5))
            return MusicalGenre.METAL;
        else if (selection.equals(6))
            return MusicalGenre.POP;
        else if (selection.equals(7))
            return MusicalGenre.CLASSIC;
        else
            System.out.println("Invalid option");
        return null;
    }
}
