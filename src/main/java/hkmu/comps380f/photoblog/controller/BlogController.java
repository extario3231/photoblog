package hkmu.comps380f.photoblog.controller;

import hkmu.comps380f.photoblog.model.Comment;
import hkmu.comps380f.photoblog.model.Photo;
import hkmu.comps380f.photoblog.model.dto.CommentDto;
import hkmu.comps380f.photoblog.model.dto.PhotoDto;
import hkmu.comps380f.photoblog.service.CommentService;
import hkmu.comps380f.photoblog.service.PhotoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.List;

@Controller
@RequestMapping("/blog")
public class BlogController {
    private final PhotoService photoService;
    private final CommentService commentService;

    public BlogController(PhotoService photoService, CommentService commentService) {
        this.photoService = photoService;
        this.commentService = commentService;
    }

    @GetMapping("/upload")
    public ModelAndView uploadNewPhoto() {
        return new ModelAndView("upload", "uploadForm", new PhotoDto());
    }

    @PostMapping("/upload")
    public View upload(PhotoDto dto, HttpSession session) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/M/yyyy HH:mm:ss");
        LocalDateTime uploadTime = Instant.now().atZone(ZoneId.systemDefault()).toLocalDateTime();
        String uploadTimeStr = uploadTime.format(formatter);

        for (MultipartFile photo : dto.getPhotos()) {
            Photo photoObj = new Photo();
            photoObj.setDescription(dto.getDescription());
            photoObj.setUploader((String) session.getAttribute("username"));
            photoObj.setUploadTime(uploadTimeStr);

            try {
                byte[] content = photo.getBytes();
                if (content.length == 0) return new RedirectView("/upload", true);
                photoObj.setContent(Base64.getEncoder().encodeToString(content));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            photoService.save(photoObj);
        }

        return new RedirectView("/", true);
    }

    @GetMapping("/photo/{id}")
    public ModelAndView viewPhoto(@PathVariable Long id, ModelMap modelMap) {
        modelMap.addAttribute("photo", photoService.findById(id));
        return new ModelAndView("photo", "commentForm", new CommentDto());
    }

    @PostMapping("/photo/{id}")
    public View addComment(@PathVariable Long id, HttpSession session, ModelMap modelMap, CommentDto dto) {
        Photo photo = photoService.findById(id);
        Comment newComment = new Comment();
        newComment.setComment(dto.getComment());
        newComment.setPhoto(photo);
        commentService.save(newComment);
        modelMap.addAttribute("photo", photo);

        return new RedirectView("/blog/photo/"+id, true);
    }
}
