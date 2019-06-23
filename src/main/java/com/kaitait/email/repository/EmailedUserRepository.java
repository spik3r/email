package com.kaitait.email.repository;

import com.kaitait.email.domain.EmailedUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmailedUserRepository extends JpaRepository<EmailedUser, String> {
    List<EmailedUser> findAll();
}
