package hkmu.comps380f.photoblog.repo;

import hkmu.comps380f.photoblog.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepo extends JpaRepository<Photo, Long> {
}
