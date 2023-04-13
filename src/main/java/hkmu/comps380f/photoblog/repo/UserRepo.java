package hkmu.comps380f.photoblog.repo;

import hkmu.comps380f.photoblog.model.BlogUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<BlogUser, Long> {
    Optional<BlogUser> findByUsername(String username);
    boolean existsByUsername(String username);
}
