package br.com.backend.equipe4.services;

import br.com.backend.equipe4.entity.User;
import br.com.backend.equipe4.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(User user) throws Exception {
        if (!user.isValidAge()) {
            throw new Exception("User must be at least 18 years old");
        }

        if (userRepository.existsByUsername(user.getUsername())) {
            throw new Exception("Username already exists");
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new Exception("Email already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Optional<User> loginUser(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        return userOptional.filter(user -> passwordEncoder.matches(password, user.getPassword()));
    }
}
