package br.com.backend.equipe4.controllers;

import br.com.backend.equipe4.jwt.JwtToken;
import br.com.backend.equipe4.jwt.JwtUserDetailsServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/profile")
public class AuthenticationController {

    private final JwtUserDetailsServices detailsService;
    private final AuthenticationManager authenticationManager;

}
