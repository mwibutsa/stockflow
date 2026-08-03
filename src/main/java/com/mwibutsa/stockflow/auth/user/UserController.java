package com.mwibutsa.stockflow.auth.user;

import com.mwibutsa.stockflow.auth.user.dto.CreateUserRequest;
import com.mwibutsa.stockflow.auth.user.dto.LoginRequest;
import com.mwibutsa.stockflow.auth.user.dto.LoginResponse;
import com.mwibutsa.stockflow.auth.user.dto.UserResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    @PostMapping("/users")
    public ResponseEntity<UserResponse> registerUser(@Valid @RequestBody CreateUserRequest payload,
                                                     UriComponentsBuilder uriBuilder) {
        var userDto = userService.registerUser(payload);
        var uri = uriBuilder.path("/auth/users/{userId}").buildAndExpand(userDto.getId()).toUri();
        return ResponseEntity.created(uri).body(userDto);
    }

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest payload) {
        return userService.login(payload);
    }
}
