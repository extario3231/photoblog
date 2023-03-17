package hkmu.comps380f.photoblog.controller;

import hkmu.comps380f.photoblog.model.User;
import hkmu.comps380f.photoblog.model.dto.UserDto;
import hkmu.comps380f.photoblog.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import static hkmu.comps380f.photoblog.config.SecurityConfig.getPasswordEncoder;

@Controller
public class UserController {
    private final UserService userService;
    private final BCryptPasswordEncoder encoder;

    public UserController(UserService userService) {
        this.userService = userService;
        this.encoder = getPasswordEncoder();
    }

    @GetMapping("/signup")
    public ModelAndView signup() {
        return new ModelAndView("signup", "signupForm", new UserDto());
    }

    @PostMapping("/signup")
    public View signup(UserDto userDto, HttpSession session) {
        userService.save(userDto);
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
        if (user == null || !user.getPassword().equals(encoder.encode(dto.getPassword())))
            return new RedirectView("/login", true);
        session.setAttribute("username", user.getName());
        session.setAttribute("description", user.getDescription());
        session.setAttribute("photos", user.getPhotos());
        return new RedirectView("/", true);
    }

    @GetMapping("/myprofile")
    public String viewProfile(ModelMap modelMap, HttpSession session) {
        modelMap.addAttribute("username", session.getAttribute("username"));
        modelMap.addAttribute("description", session.getAttribute("description"));
        modelMap.addAttribute("photos", session.getAttribute("photos"));
        return "profile";
    }
}
