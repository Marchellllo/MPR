package pl.edu.pjatk.mpr.Exception;

public class CatIsEmpty extends RuntimeException {
    public CatIsEmpty(String message) {
        super("Field is empty");
    }
}
