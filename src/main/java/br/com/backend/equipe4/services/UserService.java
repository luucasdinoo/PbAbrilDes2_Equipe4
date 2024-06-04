package br.com.backend.equipe4.services;

import br.com.backend.equipe4.entity.Post;
import br.com.backend.equipe4.entity.User;
import br.com.backend.equipe4.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow((() -> new RuntimeException("Username not found")));
    }

    public User.Role getRoleByUsername(String username) {
        return userRepository.findRoleByUsername(username);
    }

    public User getUserById(UUID id) {
        return  userRepository.findById(id);
    }

    public String follow(User followerUser, User followedUser) {

        UUID followerId = followerUser.getId();
        UUID followedId = followedUser.getId();

        if (followerId.equals(followedId))
            throw new RuntimeException("You can't follow yourself");

        if (followerUser.getFollows().contains(followedUser))
            return "You already follow " + followedUser.getUsername();

        followerUser.getFollows().add(followedUser);
        followedUser.getFollowers().add(followerUser);

        userRepository.save(followerUser);
        userRepository.save(followedUser);

        return "Now you follow " + followedUser.getUsername();

    }

    public String unfollow(User followerUser, User followedUser) {

        UUID followerId = followerUser.getId();
        UUID followedId = followedUser.getId();

        if (followerId.equals(followedId))
            throw new RuntimeException("You can't unfollow yourself");

        if (!followerUser.getFollows().contains(followedUser))
            return "You do not follow " + followedUser.getUsername();

        followerUser.getFollows().remove(followedUser);
        followedUser.getFollowers().remove(followerUser);

        userRepository.save(followerUser);
        userRepository.save(followedUser);

        return "You have unfollowed " + followedUser.getUsername();
    }

    public List<Post> homePage(){

        List<User> users = userRepository.findAll();
        List<Post> posts = new ArrayList<>();
        for (User user : users) {
            posts.addAll(user.getPosts());
        }
        return posts;
    }

}
