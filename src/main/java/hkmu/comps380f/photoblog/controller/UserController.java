package hkmu.comps380f.photoblog.controller;

import hkmu.comps380f.photoblog.model.BlogUser;
import hkmu.comps380f.photoblog.model.dto.UserDto;
import hkmu.comps380f.photoblog.service.BlogUserDetailsService;
import hkmu.comps380f.photoblog.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

@Controller
public class UserController {
    private final UserService userService;
    private final BlogUserDetailsService userDetailsService;

    public UserController(UserService userService, BlogUserDetailsService userDetailsService) {
        this.userService = userService;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/signup")
    public ModelAndView viewSignupPage() {
        return new ModelAndView("signup", "signupForm", new UserDto());
    }

    @PostMapping("/signup")
    public View signup(UserDto userDto, HttpServletRequest request) {
        BlogUser newUser = userService.saveNewUser(userDto);
        UserDetails userDetails = userDetailsService.loadUserByUsername(newUser.getUsername());

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(token);

        HttpSession session = request.getSession(true);
        session.setAttribute("username", userDto.getUsername());
        session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, context);

        return new RedirectView("/", true);
    }

    @GetMapping("/login")
    public ModelAndView viewLoginPage() {
        return new ModelAndView("login", "loginForm", new UserDto());
    }

    @GetMapping("/profile")
    public String viewProfile(ModelMap modelMap, Principal user, HttpSession session) {
        BlogUser blogUser = userService.findByUsername(user.getName());
        modelMap.addAttribute("description", blogUser.getDescription());
        session.setAttribute("description", blogUser.getDescription());
        modelMap.addAttribute("photos", blogUser.getPhotos());
        return "profile";
    }

    @GetMapping("/profile/edit")
    public String viewEditProfilePage(HttpSession session, ModelMap modelMap, Principal user) {
        modelMap.addAttribute("username", user.getName());
        modelMap.addAttribute("description", session.getAttribute("description"));
        return "profileEdit";
    }

    @PostMapping("/profile/edit")
    public String editProfile(@RequestBody MultiValueMap<String, String> description, Principal user) {
        System.out.println(description);
        BlogUser blogUser = userService.findByUsername(user.getName());
        blogUser.setDescription(description.get("description").get(0));
        userService.save(blogUser);
        return "redirect:/profile";
    }

    @PostMapping("/user/delete/{id}")
    public String deleteUserById(@PathVariable Long id) {
        userService.deleteById(id);
        return "redirect:/manage";
    }

    @GetMapping("/user/add")
    public ModelAndView viewAddUserPage(ModelMap modelMap) {
        modelMap.addAttribute("roles", new String[]{"USER", "ADMIN"});
        return new ModelAndView("addUser", "userForm", new UserDto());
    }

    @PostMapping(value = "/user/add")
    public String addUser(UserDto dto) {
        userService.saveNewUser(dto);
        return "redirect:/manage";
    }
}
