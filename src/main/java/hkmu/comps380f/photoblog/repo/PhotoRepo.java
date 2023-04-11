package hkmu.comps380f.photoblog.repo;

import hkmu.comps380f.photoblog.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhotoRepo extends JpaRepository<Photo, Long> {
    List<Photo> findAllByUploader(String uploader);
}
