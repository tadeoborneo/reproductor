package Interfaces;

import Exception.InvalidOptionException;

import java.util.List;
import java.util.Scanner;

public interface Selection<T> {
    public default Integer select(List<T> tList) throws InvalidOptionException {
        Scanner sc = new Scanner(System.in);
        Integer select;
        for (int i = 0; i < tList.size(); i++) {
            System.out.println("--------------------------------------------------------");
            System.out.println("|" + (i + 1) + "|\n" + tList.get(i));
            System.out.println("--------------------------------------------------------");
        }
        System.out.println("0- EXIT");
        System.out.println("Select: ");
        select = sc.nextInt();
        sc.nextLine();
        if (select < 0 || select > tList.size()) {
            throw new InvalidOptionException();
        } else
            return select;
    }
}
