package pl.edu.pjatk.mpr.Exception;

public class CatNotFound extends RuntimeException {
    public CatNotFound() {
        super("Cat Not Found");
    }
}
