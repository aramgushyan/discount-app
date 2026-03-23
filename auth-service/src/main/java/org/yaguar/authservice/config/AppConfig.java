package org.yaguar.authservice.config;

import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.yaguar.authservice.repository.UserRepository;
import org.yaguar.authservice.repository.UserRepositoryImpl;
import org.yaguar.authservice.service.UserService;
import org.yaguar.authservice.service.UserServiceImpl;
import org.yaguar.common.utils.JwtUtils;

import java.nio.charset.StandardCharsets;
import java.security.Key;

@Configuration
public class AppConfig {
    @Bean
    UserRepository userRepository(JdbcTemplate jdbcTemplate) {
        return new UserRepositoryImpl(jdbcTemplate);
    }

    @Bean
    UserService userService(UserRepository userRepository,
                            PasswordEncoder passwordEncoder,
                            JwtUtils  jwtUtils,
                            @Value("${jwt.secret}") String secretKey,
                            @Value("${jwt.expiration}") Long expiration) {
        var key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        return new UserServiceImpl(userRepository, passwordEncoder, jwtUtils, key, expiration);
    }
}
