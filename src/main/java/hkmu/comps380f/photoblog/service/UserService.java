package hkmu.comps380f.photoblog.service;

import hkmu.comps380f.photoblog.model.User;
import hkmu.comps380f.photoblog.model.UserRole;
import hkmu.comps380f.photoblog.model.dto.UserDto;
import hkmu.comps380f.photoblog.repo.UserRepo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

import static hkmu.comps380f.photoblog.config.SecurityConfig.getPasswordEncoder;

@Service
public class UserService {
    private final UserRepo userRepo;
    private final BCryptPasswordEncoder encoder;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
        this.encoder = getPasswordEncoder();
    }

    public List<User> findAll() {
        return userRepo.findAll();
    }

    public User findByName(String name) {
        return userRepo.findByName(name).orElseThrow(NoSuchElementException::new);
    }

    public User findByName(Long id) {
        return userRepo.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public void save(UserDto dto) {
        User newUser = new User();
        newUser.setName(dto.getUsername());
        newUser.setPassword(encoder.encode(dto.getPassword()));
        newUser.setEmail(dto.getEmail());
        newUser.setPhoneNumber(dto.getPhoneNumber());
        newUser.setUserRole(UserRole.USER);
        userRepo.save(newUser);
    }
}
