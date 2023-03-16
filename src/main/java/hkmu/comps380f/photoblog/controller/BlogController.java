package hkmu.comps380f.photoblog.controller;

import hkmu.comps380f.photoblog.model.Photo;
import hkmu.comps380f.photoblog.model.dto.PhotoDto;
import hkmu.comps380f.photoblog.service.PhotoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/blog")
public class BlogController {
    private final PhotoService photoService;

    public BlogController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @GetMapping("/upload")
    public ModelAndView uploadNewPhoto() {
        return new ModelAndView("upload", "uploadForm", new PhotoDto());
    }

    @PostMapping("/upload")
    public View upload(PhotoDto dto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/M/yyyy HH:mm:ss");
        LocalDateTime uploadTime = Instant.now().atZone(ZoneId.systemDefault()).toLocalDateTime();
        String uploadTimeStr = uploadTime.format(formatter);

        for (MultipartFile photo : dto.getPhotos()) {
            Photo photoObj = new Photo();
            photoObj.setDescription(dto.getDescription());
            photoObj.setUploader("user");
            photoObj.setUploadTime(uploadTimeStr);

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
