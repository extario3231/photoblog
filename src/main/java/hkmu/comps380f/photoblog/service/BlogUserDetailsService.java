package hkmu.comps380f.photoblog.service;

import hkmu.comps380f.photoblog.exception.UserNotFoundException;
import hkmu.comps380f.photoblog.model.BlogUser;
import hkmu.comps380f.photoblog.repo.UserRepo;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class BlogUserDetailsService implements UserDetailsService {
    private final UserRepo userRepo;

    public BlogUserDetailsService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        BlogUser blogUser = userRepo.findByUsername(username).orElseThrow(UserNotFoundException::new);
        return User.builder().username(blogUser.getUsername()).password(blogUser.getPassword())
                .roles(blogUser.getUserRoles().stream().map(Enum::name).toArray(String[]::new)).build();
    }
}
