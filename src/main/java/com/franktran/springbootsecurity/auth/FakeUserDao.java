package com.franktran.springbootsecurity.auth;

import com.google.common.collect.Lists;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.franktran.springbootsecurity.security.UserRole.ADMIN;
import static com.franktran.springbootsecurity.security.UserRole.ADMINTRAINEE;
import static com.franktran.springbootsecurity.security.UserRole.STUDENT;

@Repository("fake")
public class FakeUserDao implements UserDao {

    private final PasswordEncoder passwordEncoder;

    public FakeUserDao(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<User> selectUserByUsername(String username) {
        return selectAllUsers().stream()
                .filter(user -> Objects.equals(username, user.getUsername()))
                .findFirst();
    }

    public List<User> selectAllUsers() {
        return Lists.newArrayList(
                new User(ADMIN.getAuthorities(),
                        "frank",
                        passwordEncoder.encode("frank123"),
                        true, true, true, true),
                new User(ADMINTRAINEE.getAuthorities(),
                        "henry",
                        passwordEncoder.encode("henry123"),
                        true, true, true, true),
                new User(STUDENT.getAuthorities(),
                        "bean",
                        passwordEncoder.encode("bean123"),
                        true, true, true, true)
        );
    }
}
