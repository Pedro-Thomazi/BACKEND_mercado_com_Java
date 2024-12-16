package br.com.backend.backend.controllers;

import br.com.backend.backend.domains.user.*;
import br.com.backend.backend.services.TokenService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private UserRepository repository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    @Transactional
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
        try {
            var userNamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
            var auth = manager.authenticate(userNamePassword);
            var token = tokenService.generateToken((User) auth.getPrincipal());
            return ResponseEntity.ok(new TokenResponseDTO(token));
        } catch (Exception error) {
            error.printStackTrace();
            System.out.println(error.getMessage());
            return ResponseEntity.badRequest().body(error.getMessage());
        }
    }

    @PostMapping("/register")
    @Transactional
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data) {
        if (this.repository.findByEmail(data.email()) != null) return ResponseEntity.badRequest().build();

        try {
            String encriptedPassword = new BCryptPasswordEncoder().encode(data.password());
            User newUser = new User(data.name(), data.email(), encriptedPassword, data.status());
            this.repository.save(newUser);

            var userNamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
            var auth = manager.authenticate(userNamePassword);
            var token = tokenService.generateToken((User) auth.getPrincipal());
            return ResponseEntity.ok(new TokenResponseDTO(token));
        } catch (Exception error) {
            error.printStackTrace();
            System.out.println(error.getMessage());
            return ResponseEntity.badRequest().body(error.getMessage());
        }
    }
}
