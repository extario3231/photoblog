package hkmu.comps380f.photoblog.controller;

import hkmu.comps380f.photoblog.model.Photo;
import hkmu.comps380f.photoblog.model.PhotoDto;
import hkmu.comps380f.photoblog.model.User;
import hkmu.comps380f.photoblog.model.UserDto;
import hkmu.comps380f.photoblog.service.PhotoService;
import hkmu.comps380f.photoblog.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
public class BlogController {
    private final PhotoService photoService;
    private final UserService userService;

    public BlogController(PhotoService photoService, UserService userService) {
        this.photoService = photoService;
        this.userService = userService;
    }

    @GetMapping("/signup")
    public ModelAndView signup() {
        return new ModelAndView("signup", "signupForm", new UserDto());
    }

    @PostMapping("/signup")
    public View signup(UserDto userDto, HttpSession session) {
        User newUser = new User();
        newUser.setName(userDto.getUsername());
        newUser.setPassword(userDto.getPassword());
        newUser.setEmail(userDto.getEmail());
        newUser.setPhoneNumber(userDto.getPhoneNumber());
        userService.save(newUser);

        session.setAttribute("username", userDto.getUsername());

        return new RedirectView("/", true);
    }

    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("login", "loginForm", new UserDto());
    }

    @PostMapping("/login")
    public View login(UserDto dto, HttpSession session) {
        User user = userService.findByName(dto.getUsername());
        if (user == null || !user.getPassword().equals(dto.getPassword()))
            return new RedirectView("/login", true);
        session.setAttribute("username", user.getName());
        return new RedirectView("/", true);
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
