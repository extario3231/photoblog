package hkmu.comps380f.photoblog.repo;

import hkmu.comps380f.photoblog.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment, Long> {
}
