package hkmu.comps380f.photoblog.service;

import hkmu.comps380f.photoblog.model.Photo;
import hkmu.comps380f.photoblog.model.dto.PhotoDto;
import hkmu.comps380f.photoblog.repo.PhotoRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;

@Service
@Transactional
public class PhotoService {
    private final PhotoRepo photoRepo;
    private final UserService userService;
    @PersistenceContext
    private final EntityManager manager;

    public PhotoService(PhotoRepo photoRepo, UserService userService, EntityManager manager) {
        this.photoRepo = photoRepo;
        this.userService = userService;
        this.manager = manager;
    }

    public List<Photo> findAll() {
        return photoRepo.findAll();
    }

    public Photo findById(Long id) {
        Session session = manager.unwrap(Session.class).getSessionFactory().openSession();
        return session.get(Photo.class, id);
    }

    @Transactional
    public void save(PhotoDto dto, MultipartFile photoUploaded, Principal user, LocalDateTime uploadTime) throws IOException {
        Photo newPhoto = new Photo();

        newPhoto.setName(photoUploaded.getOriginalFilename());
        newPhoto.setContent(Base64.getEncoder().encodeToString(photoUploaded.getBytes()));
        newPhoto.setDescription(dto.getDescription());
        newPhoto.setUploader(user.getName());
        newPhoto.setUploadTime(uploadTime);
        newPhoto.setUser(userService.findByUsername(user.getName()));

        photoRepo.save(newPhoto);
    }

    public void deleteById(Long id) {
        photoRepo.deleteById(id);
    }
}
