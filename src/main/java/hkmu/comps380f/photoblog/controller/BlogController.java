package hkmu.comps380f.photoblog.controller;

import hkmu.comps380f.photoblog.model.Photo;
import hkmu.comps380f.photoblog.model.UploadForm;
import hkmu.comps380f.photoblog.service.PhotoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;

@Controller
@RequestMapping("/blog")
public class BlogController {
    private PhotoService photoService;

    public BlogController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @GetMapping("/upload")
    public ModelAndView uploadNewPhoto() {
        return new ModelAndView("upload", "uploadForm", new UploadForm());
    }

    @PostMapping("/upload")
    public View upload(UploadForm form) {
        for (MultipartFile photo : form.getPhotos()) {
            Photo photoObj = new Photo();
            photoObj.setDescription(form.getDescription());
            photoObj.setUploader("user");

            try {
                byte[] content = photo.getBytes();
                if (content.length == 0) return new RedirectView("/upload", true);
                photoObj.setContent(content);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            photoService.save(photoObj);
        }

        return new RedirectView("/", true);
    }
}
