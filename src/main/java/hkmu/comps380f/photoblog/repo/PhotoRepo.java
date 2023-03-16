package hkmu.comps380f.photoblog.repo;

import hkmu.comps380f.photoblog.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PhotoRepo extends JpaRepository<Photo, Long> {
    List<Photo> findAll();
    Optional<Photo> findById(Long id);
    void deleteById(Long id);
}
