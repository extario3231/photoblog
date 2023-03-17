package hkmu.comps380f.photoblog.service;

import hkmu.comps380f.photoblog.model.Comment;
import hkmu.comps380f.photoblog.repo.CommentRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private final CommentRepo commentRepo;

    public CommentService(CommentRepo commentRepo) {
        this.commentRepo = commentRepo;
    }

    public List<Comment> findAll() {
        return commentRepo.findAll();
    }

    public void save(Comment comment) {
        commentRepo.save(comment);
    }
}
