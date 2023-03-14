package hkmu.comps380f.photoblog.controller;

import hkmu.comps380f.photoblog.model.Photo;
import hkmu.comps380f.photoblog.model.UploadForm;
import hkmu.comps380f.photoblog.model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;

@Controller
@RequestMapping("/blog")
public class BlogController {
    @GetMapping("/upload")
    public String uploadNewPhoto() {
        return "upload";
    }

    @PostMapping("/upload")
    public View upload(UploadForm form, HttpSession session) {
        form.getPhotos().forEach(photo -> {
            Photo photoObj = new Photo();
            photoObj.setDescription(form.getDescription());
            photoObj.setUploader((String) session.getAttribute("username"));
            try {
                byte[] content = photo.getBytes();
                if (content.length == 0) return;
                photoObj.setContent(content);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        return new RedirectView("/myprofile", true);
    }
}
