package hkmu.comps380f.photoblog.controller;

import hkmu.comps380f.photoblog.model.Photo;
import hkmu.comps380f.photoblog.service.PhotoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class IndexController {
    private final PhotoService photoService;

    public IndexController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @GetMapping("/")
    public String index(ModelMap modelMap, HttpSession session) {
        List<Photo> allPhotos = photoService.findAll();
        modelMap.addAttribute("photos", allPhotos);
        modelMap.addAttribute("username", session.getAttribute("username"));
        return "index";
    }
}
