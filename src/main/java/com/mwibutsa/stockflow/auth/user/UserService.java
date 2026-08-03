package com.mwibutsa.stockflow.auth.user;

import com.mwibutsa.stockflow.auth.user.dto.CreateUserRequest;
import com.mwibutsa.stockflow.auth.user.dto.LoginRequest;
import com.mwibutsa.stockflow.auth.user.dto.LoginResponse;
import com.mwibutsa.stockflow.auth.user.dto.UserResponse;
import com.mwibutsa.stockflow.common.exception.BadRequestException;
import com.mwibutsa.stockflow.common.exception.ConflictException;
import com.mwibutsa.stockflow.jwt.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final JwtService jwtService;

    public UserResponse registerUser(CreateUserRequest payload) {
        var emailExists = userRepository.existsByEmail(payload.getEmail());

        if (emailExists) {
            throw new ConflictException("Email is already in use", "email");
        }

        var user = userMapper.toEntity(payload);

        user.setPassword(passwordEncoder.encode(payload.getPassword()));

        userRepository.save(user);
        return userMapper.toDto(user);


    }


    public LoginResponse login(LoginRequest payload) {
        var user = userRepository.findByEmail(payload.getEmail()).orElseThrow(() -> new BadRequestException("Invalid user credentials"));

        if (!passwordEncoder.matches(payload.getPassword(), user.getPassword())) {
            throw new BadRequestException("Invalid user credentials");
        }
        var token = jwtService.generateAccessToken(user);
        return new LoginResponse(token.toString());
    }
}
