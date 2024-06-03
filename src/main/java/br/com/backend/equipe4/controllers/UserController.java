package br.com.backend.equipe4.controllers;

import br.com.backend.equipe4.dto.ProfileResponseDto;
import br.com.backend.equipe4.dto.UserDto;
import br.com.backend.equipe4.dto.mapper.UserMapper;
import br.com.backend.equipe4.entity.User;
import br.com.backend.equipe4.exception.GlobalExceptionHandler;
import br.com.backend.equipe4.exception.UserAlreadyExistsException;
import br.com.backend.equipe4.jwt.JwtUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import br.com.backend.equipe4.services.UserService;

import java.util.*;

@Tag(name = "Users", description = "All user related methods")
@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @Operation(summary = "Register a new user", description = "Method for register a new user in application",
            responses = {
                @ApiResponse(responseCode = "201", description = "New user successfully registered.",
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))),
                @ApiResponse(responseCode = "409", description = "User already registered.",
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserAlreadyExistsException.class))),
                @ApiResponse(responseCode = "422", description = "Invalid data",
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = GlobalExceptionHandler.class)))
            }
    )
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user) {
        try {
            User registeredUser = userService.registerUser(UserMapper.toUser(user));
            return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toDto(registeredUser));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

/*    @Operation(summary = "Login into account", description = "Method for login in application",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Login successfully.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid email or password",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = GlobalExceptionHandler.class))),
                    @ApiResponse(responseCode = "422", description = "Invalid data",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = GlobalExceptionHandler.class)))
            }
    )
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
    }*/


    @Operation(summary = "Follow a user", description = "Method to follow a user by their user",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully followed the user",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))),
                    @ApiResponse(responseCode = "404", description = "User not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = GlobalExceptionHandler.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid user",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = GlobalExceptionHandler.class)))
            }
    )
    @PostMapping("/follow/{userId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> followUser(@PathVariable UUID userId, @AuthenticationPrincipal JwtUserDetails userDetails) {
        User followerUser = userService.getUserByUsername(userDetails.getUsername());
        User followedUser = userService.getUserById(userId);
        String response = userService.follow(followerUser, followedUser);
        return ResponseEntity.ok(response);
    }


    @Operation(summary = "Unfollow a user", description = "Method to unfollow a user by their user",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully unfollowed the user",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))),
                    @ApiResponse(responseCode = "404", description = "User not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = GlobalExceptionHandler.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid user",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = GlobalExceptionHandler.class)))
            }
    )
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/unfollow/{userId}")
    public ResponseEntity<String> unfollowUser(@PathVariable UUID userId, @AuthenticationPrincipal JwtUserDetails userDetails) {
        User unfollowerUser = userService.getUserByUsername(userDetails.getUsername());
        User unfollowedUser = userService.getUserById(userId);
        String response = userService.unfollow(unfollowerUser, unfollowedUser);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/profile")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ProfileResponseDto> getProfile(@AuthenticationPrincipal JwtUserDetails user) {

        User UserProfile = userService.getUserById(user.getId());

        return ResponseEntity.ok(UserMapper.toProfileDto(UserProfile));
    }
}
