package com.kaitait.email.service;

import com.kaitait.email.domain.User;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class FakeUserService {
    public static final User FAKE_USER = new User("AAA", "BBB", "dummy@test.com", "abc123");

    public User getUser() {
        return FAKE_USER;
    }
}
