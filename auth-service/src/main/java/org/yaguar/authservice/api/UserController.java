package org.yaguar.authservice.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.descriptor.web.ContextHandler;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yaguar.authservice.api.dto.request.LoginRequest;
import org.yaguar.authservice.api.dto.request.RegisterRequest;
import org.yaguar.authservice.service.UserService;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public String register(@RequestBody @Valid RegisterRequest registerRequest) {
        return userService.Register(registerRequest);
    }

    @PreAuthorize("hasRole('ANONYMOUS')")
    @PostMapping("/login")
    public String login(@RequestBody @Valid LoginRequest loginRequest) {
        var rawToken = new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password());
        return userService.Login(loginRequest);
    }
}
