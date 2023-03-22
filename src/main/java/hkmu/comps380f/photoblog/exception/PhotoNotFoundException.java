package hkmu.comps380f.photoblog.exception;

public class PhotoNotFoundException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Photo not found";
    }
}
