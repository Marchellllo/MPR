package pl.edu.pjatk.mpr.Exception;

public class CatAlreadyExists extends RuntimeException {
    public CatAlreadyExists() {
        super("Cat already exists: ");
    }
}
