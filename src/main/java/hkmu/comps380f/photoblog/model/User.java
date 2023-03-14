package hkmu.comps380f.photoblog.model;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class User {
    private String name;
    private String description;
    private Map<String, Photo> photos = new ConcurrentHashMap<>();
    private boolean isAdmin;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(Map<String, Photo> photos) {
        this.photos = photos;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
