package hkmu.comps380f.photoblog.service;

import hkmu.comps380f.photoblog.exception.UserNotFoundException;
import hkmu.comps380f.photoblog.model.User;
import hkmu.comps380f.photoblog.model.UserRole;
import hkmu.comps380f.photoblog.model.dto.UserDto;
import hkmu.comps380f.photoblog.repo.UserRepo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static hkmu.comps380f.photoblog.config.SecurityConfig.passwordEncoder;

@Service
public class UserService {
    private final UserRepo userRepo;
    private final BCryptPasswordEncoder encoder;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
        this.encoder = passwordEncoder();
    }

    public List<User> findAll() {
        return userRepo.findAll();
    }

    public User findByUsername(String name) {
        return userRepo.findByUsername(name).orElseThrow(UserNotFoundException::new);
    }

    public User findByUsername(Long id) {
        return userRepo.findById(id).orElseThrow(UserNotFoundException::new);
    }

    public User saveNewUser(UserDto dto) {
        User newUser = new User();
        newUser.setUsername(dto.getUsername());
        newUser.setPassword(encoder.encode(dto.getPassword()));
        newUser.setEmail(dto.getEmail());
        newUser.setPhoneNumber(dto.getPhoneNumber());
        newUser.setUserRoles(List.of(UserRole.ADMIN));
        userRepo.save(newUser);
        return newUser;
    }

    @Transactional(rollbackFor = UserNotFoundException.class)
    public void save(User user) {
        userRepo.save(user);
    }

    public void deleteByName(String name) {
        userRepo.deleteByUsername(name);
    }

    public void deleteById(Long id) {
        userRepo.deleteById(id);
    }
}
