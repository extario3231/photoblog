package hkmu.comps380f.photoblog.controller;

import hkmu.comps380f.photoblog.model.BlogUser;
import hkmu.comps380f.photoblog.model.Comment;
import hkmu.comps380f.photoblog.model.Photo;
import hkmu.comps380f.photoblog.model.dto.CommentDto;
import hkmu.comps380f.photoblog.model.dto.PhotoDto;
import hkmu.comps380f.photoblog.service.CommentService;
import hkmu.comps380f.photoblog.service.PhotoService;
import hkmu.comps380f.photoblog.service.UserService;
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
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
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
    public String index(ModelMap modelMap) {
        List<Photo> allPhotos = photoService.findAll();
        modelMap.addAttribute("photos", allPhotos);
        return "index";
    }

    @GetMapping("/upload")
    public ModelAndView viewUploadPhotoPage() {
        return new ModelAndView("upload", "uploadForm", new PhotoDto());
    }

    @PostMapping("/upload")
    public View upload(PhotoDto dto, Principal user) throws IOException {
        LocalDateTime uploadTime = LocalDateTime.now(ZoneId.systemDefault()).truncatedTo(ChronoUnit.SECONDS);

        for (MultipartFile photo : dto.getPhotos()) {
            photoService.save(dto, photo, user, uploadTime);
        }

        return new RedirectView("/", true);
    }

    @GetMapping("/photo/{id}")
    public ModelAndView viewPhoto(@PathVariable Long id, ModelMap modelMap) {
        Photo photo = photoService.findById(id);
        modelMap.addAttribute("photo", photo);
        return new ModelAndView("photo", "commentForm", new CommentDto());
    }

    @PostMapping("/photo/{id}")
    public View addComment(@PathVariable Long id, ModelMap modelMap, CommentDto dto, Principal user) {
        Photo photo = photoService.findById(id);
        commentService.save(dto, photo, user.getName());
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

    @GetMapping("/upload/history")
    public String viewUploadHistory(ModelMap map, Principal user) {
        List<Photo> photos = photoService.findAllByUploader(user.getName());
        map.addAttribute("photos", photos);
        return "uploadHistory";
    }
}
