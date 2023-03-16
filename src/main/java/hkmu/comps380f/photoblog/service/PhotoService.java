package hkmu.comps380f.photoblog.service;

import hkmu.comps380f.photoblog.model.Photo;
import hkmu.comps380f.photoblog.repo.PhotoRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PhotoService {
    private final PhotoRepo photoRepo;

    public PhotoService(PhotoRepo photoRepo) {
        this.photoRepo = photoRepo;
    }

    public List<Photo> findAll() {
        return photoRepo.findAll();
    }

    public Photo findById(Long id) {
        return photoRepo.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public void save(Photo photo) {
        photoRepo.save(photo);
    }
}
