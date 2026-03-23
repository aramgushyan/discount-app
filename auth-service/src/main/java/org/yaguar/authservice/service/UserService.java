package org.yaguar.authservice.service;

import org.yaguar.authservice.api.dto.request.LoginRequest;
import org.yaguar.authservice.api.dto.request.RegisterRequest;

public interface UserService {
    String Login(LoginRequest loginRequest);
    String Register(RegisterRequest registerRequest);
}
