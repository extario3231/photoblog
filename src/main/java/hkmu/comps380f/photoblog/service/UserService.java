package hkmu.comps380f.photoblog.service;

import hkmu.comps380f.photoblog.model.User;
import hkmu.comps380f.photoblog.repo.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {
    private UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
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
}
