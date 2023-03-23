package hkmu.comps380f.photoblog.controller;

import hkmu.comps380f.photoblog.model.User;
import hkmu.comps380f.photoblog.model.dto.UserDto;
import hkmu.comps380f.photoblog.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/signup")
    public ModelAndView viewSignupPage() {
        return new ModelAndView("signup", "signupForm", new UserDto());
    }

    @PostMapping("/signup")
    public View signup(UserDto userDto, HttpSession session) {
        User newUser = userService.saveNewUser(userDto);
        session.setAttribute("username", userDto.getUsername());
        session.setAttribute("userRoles", newUser.getUserRoles());
        return new RedirectView("/", true);
    }

    @GetMapping("/login")
    public ModelAndView viewLoginPage() {
        return new ModelAndView("login", "loginForm", new UserDto());
    }

    @GetMapping("/profile")
    public String viewProfile(ModelMap modelMap, HttpSession session) {
        String username = (String) session.getAttribute("username");
        modelMap.addAttribute("username", username);
        modelMap.addAttribute("description", session.getAttribute("description"));
        modelMap.addAttribute("photos", userService.findByUsername(username).getPhotos());
        return "profile";
    }

    @GetMapping("/profile/edit")
    public String viewEditProfilePage(HttpSession session, ModelMap modelMap) {
        modelMap.addAttribute("username", session.getAttribute("username"));
        modelMap.addAttribute("description", session.getAttribute("description"));
        return "profileEdit";
    }

    @PostMapping("/profile/edit")
    public String editProfile(HttpSession session, HttpServletRequest request) {
        String description = request.getParameterValues("description")[0];
        session.setAttribute("description", description);
        User user = userService.findByUsername((String) session.getAttribute("username"));
        user.setDescription(description);
        userService.save(user);
        return "redirect:/profile";
    }

    @PostMapping("/user/delete/{id}")
    public String deleteUserById(@PathVariable Long id) {
        userService.deleteById(id);
        return "redirect:/manage";
    }
}
