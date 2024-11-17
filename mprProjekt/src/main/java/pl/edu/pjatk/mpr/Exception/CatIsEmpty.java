package pl.edu.pjatk.mpr.Exception;

public class CatIsEmpty extends RuntimeException {
    public CatIsEmpty() {
        super("Field is empty");
    }
}
