package hkmu.comps380f.photoblog.model;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class PhotoDto {
    private String description;
    private List<MultipartFile> photos;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<MultipartFile> getPhotos() {
        return photos;
    }

    public void setPhotos(List<MultipartFile> photos) {
        this.photos = photos;
    }
}
