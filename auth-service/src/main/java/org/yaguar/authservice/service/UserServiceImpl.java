package org.yaguar.authservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.yaguar.authservice.api.dto.request.LoginRequest;
import org.yaguar.authservice.api.dto.request.RegisterRequest;
import org.yaguar.authservice.entity.Role;
import org.yaguar.authservice.entity.UserEntity;
import org.yaguar.authservice.repository.UserRepository;
import org.yaguar.common.utils.JwtUtils;

import java.security.Key;

@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final Key key;
    private final Long expiration;

    public String Login(LoginRequest loginRequest) {
        var user = userRepository.getUserByName(loginRequest.username())
                .orElseThrow(() -> new RuntimeException("UserNotFound"));
        if (!passwordEncoder.matches(loginRequest.password(), user.getPassword())) {
            throw new RuntimeException("PasswordMismatch");
        }
        return jwtUtils.generateToken(user.getUsername(), user.getRole().name(), key, expiration);
    }

    public String Register(RegisterRequest registerRequest) {
        var user = userRepository.getUserByName(registerRequest.username());
        if (user.isPresent()) {
            throw new RuntimeException("UserAlreadyExists");
        }

        var password = passwordEncoder.encode(registerRequest.password());
        var userEntity = new UserEntity(null,
                registerRequest.username(),
                password, registerRequest.email(),
                Role.USER);
        userRepository.saveUser(userEntity);

        return jwtUtils.generateToken(userEntity.getUsername(), userEntity.getRole().name(), key, expiration);
    }
}
