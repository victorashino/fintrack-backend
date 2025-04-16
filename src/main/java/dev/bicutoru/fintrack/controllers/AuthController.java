package dev.bicutoru.fintrack.controllers;

import dev.bicutoru.fintrack.domain.User;
import dev.bicutoru.fintrack.dto.LoginRequestDTO;
import dev.bicutoru.fintrack.dto.RegisterRequestDTO;
import dev.bicutoru.fintrack.dto.RegisterResponseDTO;
import dev.bicutoru.fintrack.repositories.UserRepository;
import dev.bicutoru.fintrack.service.TokenService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;

    private final TokenService tokenService;

    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    @Transactional
    public ResponseEntity<?> register(@RequestBody @Valid RegisterRequestDTO body) {
        Optional<User> existingUser = this.userRepository.findByEmail(body.email());

        if (existingUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("message", "Email already in use"));
        }
        User newUser = new User();
        newUser.setName(body.name());
        newUser.setEmail(body.email());
        newUser.setPassword(passwordEncoder.encode(body.password()));

        this.userRepository.save(newUser);

        String token = this.tokenService.generateToken(newUser);

        RegisterResponseDTO response = new RegisterResponseDTO(
                 "User successfully created!",
                newUser.getName(),
                LocalDateTime.now().toString(),
                token
        );

        URI location = URI.create("/auth/" + newUser.getId());

        return ResponseEntity.created(location).body(response);
    }

/*    @PostMapping("/login")
    @Transactional
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequestDTO body) {
        User user = userRepository.findByEmail(body.login()).orElseThrow(() -> new RuntimeException("User Not Found."));

        if (passwordEncoder.matches(body.password(), user.getPassword())) {
            String token = this.tokenService.generateToken(user);
            return ResponseEntity.ok(new RegisterResponseDTO(user.getName(), token));
        }

        return ResponseEntity.badRequest().build();
    }*/
}
