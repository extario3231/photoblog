package hkmu.comps380f.photoblog.service;

import hkmu.comps380f.photoblog.exception.UserNotFoundException;
import hkmu.comps380f.photoblog.model.BlogUser;
import hkmu.comps380f.photoblog.model.dto.UserDto;
import hkmu.comps380f.photoblog.repo.UserRepo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    private final UserRepo userRepo;
    private final BCryptPasswordEncoder encoder;

    public UserService(UserRepo userRepo, BCryptPasswordEncoder encoder) {
        this.userRepo = userRepo;
        this.encoder = encoder;
    }

    public List<BlogUser> findAll() {
        return userRepo.findAll();
    }

    public BlogUser findByUsername(String name) {
        return userRepo.findByUsername(name).orElseThrow(UserNotFoundException::new);
    }

    public BlogUser findByUsername(Long id) {
        return userRepo.findById(id).orElseThrow(UserNotFoundException::new);
    }

    @Transactional
    public BlogUser saveNewUser(UserDto dto) {
        BlogUser newBlogUser = new BlogUser();
        newBlogUser.setUsername(dto.getUsername());
        newBlogUser.setPassword(encoder.encode(dto.getPassword()));
        newBlogUser.setEmail(dto.getEmail());
        newBlogUser.setPhoneNumber(dto.getPhoneNumber());
        newBlogUser.setUserRoles(dto.getRoles());
        userRepo.save(newBlogUser);
        return newBlogUser;
    }

    @Transactional(rollbackFor = UserNotFoundException.class)
    public void save(BlogUser blogUser) {
        userRepo.save(blogUser);
    }

    public void deleteByName(String name) {
        userRepo.deleteByUsername(name);
    }

    public void deleteById(Long id) {
        userRepo.deleteById(id);
    }
}
