package hkmu.comps380f.photoblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("/")
    public String index(ModelMap modelMap) {
        modelMap.addAttribute("photos");
        return "index";
    }
}
