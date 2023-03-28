package hkmu.comps380f.photoblog.controller;

import hkmu.comps380f.photoblog.model.BlogUser;
import hkmu.comps380f.photoblog.model.Comment;
import hkmu.comps380f.photoblog.model.Photo;
import hkmu.comps380f.photoblog.model.dto.CommentDto;
import hkmu.comps380f.photoblog.model.dto.PhotoDto;
import hkmu.comps380f.photoblog.service.CommentService;
import hkmu.comps380f.photoblog.service.PhotoService;
import hkmu.comps380f.photoblog.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.List;

@Controller
public class BlogController {
    private final PhotoService photoService;
    private final CommentService commentService;
    private final UserService userService;

    public BlogController(PhotoService photoService, CommentService commentService, UserService userService) {
        this.photoService = photoService;
        this.commentService = commentService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String index(ModelMap modelMap, HttpSession session) {
        List<Photo> allPhotos = photoService.findAll();
        modelMap.addAttribute("photos", allPhotos);
        modelMap.addAttribute("username", session.getAttribute("username"));
        return "index";
    }

    @GetMapping("/upload")
    public ModelAndView viewUploadPhotoPage() {
        return new ModelAndView("upload", "uploadForm", new PhotoDto());
    }

    @PostMapping("/upload")
    public View upload(PhotoDto dto, HttpSession session) {
        LocalDateTime uploadTime = LocalDateTime.now(ZoneId.systemDefault()).truncatedTo(ChronoUnit.SECONDS);

        for (MultipartFile photo : dto.getPhotos()) {
            Photo newPhoto = new Photo();

            try {
                byte[] content = photo.getBytes();
                if (content.length == 0) return new RedirectView("/upload", true);
                newPhoto.setContent(Base64.getEncoder().encodeToString(content));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            newPhoto.setDescription(dto.getDescription());
            newPhoto.setUploader((String) session.getAttribute("username"));
            newPhoto.setUploadTime(uploadTime);
            newPhoto.setUser(userService.findByUsername((String) session.getAttribute("username")));
            photoService.save(newPhoto);
        }

        return new RedirectView("/", true);
    }

    @GetMapping("/photo/{id}")
    public ModelAndView viewPhoto(@PathVariable Long id, ModelMap modelMap, HttpSession session) {
        Photo photo = photoService.findById(id);
        modelMap.addAttribute("photo", photo);
        modelMap.addAttribute("username", session.getAttribute("username"));
        return new ModelAndView("photo", "commentForm", new CommentDto());
    }

    @PostMapping("/photo/{id}")
    public View addComment(@PathVariable Long id, ModelMap modelMap, CommentDto dto) {
        Photo photo = photoService.findById(id);
        Comment newComment = new Comment();
        newComment.setComment(dto.getComment());
        newComment.setPhoto(photo);
        commentService.save(newComment);
        modelMap.addAttribute("photo", photo);

        return new RedirectView("/photo/"+id, true);
    }

    @PostMapping("/photo/delete/{id}")
    public String deletePhoto(@PathVariable Long id) {
        photoService.deleteById(id);
        return "redirect:/";
    }

    @PostMapping("/photo/{photoId}/comment/delete/{commentId}")
    public String deleteComment(@PathVariable Long photoId, @PathVariable Long commentId) {
        commentService.deleteById(commentId);
        return "redirect:/photo/"+photoId;
    }

    @GetMapping("/manage")
    public String manage(ModelMap modelMap) {
        List<BlogUser> allBlogUsers = userService.findAll();
        modelMap.addAttribute("users", allBlogUsers);
        return "manage";
    }
}
