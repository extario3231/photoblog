package hkmu.comps380f.photoblog.exception;

public class UserNotFoundException extends RuntimeException {
    @Override
    public String getMessage() {
        return "User not found";
    }
}
