package Interfaces;

public interface Crud<T> {
    public void add(T t);

    public void remove(T t);

    public void view();
}
