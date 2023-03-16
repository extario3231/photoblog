package hkmu.comps380f.photoblog.controller;

import hkmu.comps380f.photoblog.model.Photo;
import hkmu.comps380f.photoblog.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class IndexController {
    private PhotoService photoService;

    public IndexController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @GetMapping("/")
    public String index(ModelMap modelMap) {
        List<Photo> allPhotos = photoService.findAll();
        modelMap.addAttribute("photos", allPhotos);
        return "index";
    }
}
