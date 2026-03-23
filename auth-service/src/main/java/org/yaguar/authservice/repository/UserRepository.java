package org.yaguar.authservice.repository;

import org.yaguar.authservice.entity.UserEntity;

import java.util.Optional;

public interface UserRepository {
    Optional<UserEntity> getUserByName(String name);

    void saveUser(UserEntity user);
}
