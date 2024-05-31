package br.com.backend.equipe4.controllers;

import br.com.backend.equipe4.entity.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.backend.equipe4.services.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user) {
        try {
            User registeredUser = userService.registerUser(user);
            Map<String, Object> response = new HashMap<>();
            response.put("id", registeredUser.getId());
            response.put("fullName", registeredUser.getFirstName() + " " + registeredUser.getLastName());
            response.put("username", registeredUser.getUsername());
            response.put("summary", registeredUser.getSummary());
            response.put("follows", new ArrayList<>()); // Mock data
            response.put("followers", new ArrayList<>()); // Mock data
            response.put("posts", new ArrayList<>()); // Mock data
            response.put("createdAt", registeredUser.getCreatedAt());
            response.put("updatedAt", registeredUser.getUpdatedAt());

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> loginData) {
        String email = loginData.get("email");
        String password = loginData.get("password");

        Optional<User> userOptional = userService.loginUser(email, password);

        if (userOptional.isPresent()) {
            Map<String, String> response = new HashMap<>();
            response.put("token", "dummy_token"); // Implementar a geração de token JWT
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body("Invalid email or password");
        }
    }

    @PostMapping("/follow/{userId}")
    public ResponseEntity<?> followUser(@PathVariable Long userId) {
        // Implement follow logic here
        return ResponseEntity.ok("Followed user " + userId);
    }

    @PostMapping("/unfollow/{userId}")
    public ResponseEntity<?> unfollowUser(@PathVariable Long userId) {
        // Implement unfollow logic here
        return ResponseEntity.ok("Unfollowed user " + userId);
    }
}
