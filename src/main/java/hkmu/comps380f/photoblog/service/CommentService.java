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
    private final PhotoService photoService;

    public CommentService(CommentRepo commentRepo, PhotoService photoService) {
        this.commentRepo = commentRepo;
        this.photoService = photoService;
    }

    public List<Comment> findAllByPhoto(Long id) {
        return commentRepo.findAllByPhotoId(id);
    }

    @Transactional
    public Photo save(CommentDto dto, Long photoId, String user) {
        Photo photo = photoService.findById(photoId);
        Comment newComment = new Comment();
        newComment.setComment(dto.getComment());
        newComment.setPhoto(photo);
        newComment.setUsername(user);
        commentRepo.save(newComment);
        return photo;
    }

    public void deleteById(Long id) {
        commentRepo.deleteById(id);
    }
}
