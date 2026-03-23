package org.yaguar.authservice.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.yaguar.authservice.api.dto.request.RegisterRequest;
import org.yaguar.authservice.service.UserService;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public String Register(@RequestBody @Valid RegisterRequest registerRequest) {
        return userService.Register(registerRequest);
    }
}
