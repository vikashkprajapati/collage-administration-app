package com.collage.repository;

import com.collage.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository
        extends JpaRepository<User, Long> {
    @Query("select u from User u where u.username = ?1")
    Optional<User> findByUsername(String username);

    @Query("select (count(u) > 0) from User u where u.email = ?1 and u.enabled = true")
    boolean existsByEmailAndEnabledTrue(String email);

    @Query("select u from User u where u.email = ?1 and u.enabled = true")
    Optional<User> findByEmailAndEnabledTrue(String emailId);
}
