package br.com.backend.equipe4.controllers;

import br.com.backend.equipe4.dto.UserLoginDto;
import br.com.backend.equipe4.jwt.JwtToken;
import br.com.backend.equipe4.jwt.JwtUserDetailsServices;
import br.com.backend.equipe4.services.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/login")
public class AuthenticationController {

    private final JwtUserDetailsServices detailsService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @PostMapping
    public ResponseEntity<?> auth(@RequestBody @Valid UserLoginDto dto, HttpServletRequest request) {

        try {
            log.info("Login authentication process {}", dto.getUsername());
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword());
            authenticationManager.authenticate(usernamePasswordAuthenticationToken);
            JwtToken token = detailsService.getTokenAuthenticated(dto.getUsername());
            return ResponseEntity.ok(token);
        }
        catch (AuthenticationException e) {
            log.warn("Bad Credentials: {}", dto.getUsername());
        }
        return  ResponseEntity.badRequest().build();
    }

    
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        if (token != null && token.startsWith("Bearer ")) {

            token = token.substring(7);
            tokenService.invalidateToken(token);
            log.info("Logout made successfully: {}", token);
            return ResponseEntity.ok().build();

        }
        return ResponseEntity.badRequest().build();
    }
}
