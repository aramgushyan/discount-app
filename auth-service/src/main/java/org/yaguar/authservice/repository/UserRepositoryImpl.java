package org.yaguar.authservice.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.yaguar.authservice.entity.Role;
import org.yaguar.authservice.entity.UserEntity;

import java.util.Optional;

@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<UserEntity> rowMapper = (rs, ri) ->
            new UserEntity(
                    rs.getLong("id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("email"),
                    Role.valueOf(rs.getString("role").toUpperCase())
            );

    @Override
    public Optional<UserEntity> getUserByName(String name) {
        var sql = "SELECT * FROM users WHERE username = ?";
        return jdbcTemplate.query(sql,rowMapper,name).stream().findFirst();
    }

    @Override
    public void saveUser(UserEntity user) {
        var sql = "INSERT INTO users (username, email, password, role) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, user.getUsername(), user.getEmail(), user.getPassword(), user.getRole().name());
    }
}
