package com.example.MailSenderService.repositories;

import com.example.MailSenderService.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface BaseUserRepositoryResolver extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM users WHERE id = ?1", nativeQuery = true)
    Optional<User> findById(Long id);

    @Query(value = "SELECT * FROM users WHERE dateOfBirth = ?1", nativeQuery = true)
    List<User> findByDateOfBirth(Date passedDateOfBirth);
}
