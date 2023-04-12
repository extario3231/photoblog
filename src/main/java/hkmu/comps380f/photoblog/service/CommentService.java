package hkmu.comps380f.photoblog.service;

import hkmu.comps380f.photoblog.model.Comment;
import hkmu.comps380f.photoblog.model.Photo;
import hkmu.comps380f.photoblog.model.dto.CommentDto;
import hkmu.comps380f.photoblog.repo.CommentRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public void save(CommentDto dto, Photo photo, String user) {
        Comment newComment = new Comment();
        newComment.setComment(dto.getComment());
        newComment.setPhoto(photo);
        newComment.setUsername(user);
        commentRepo.save(newComment);
    }

    public void deleteById(Long id) {
        commentRepo.deleteById(id);
    }
}
