package com.example.MailSenderService.repositories;

import com.example.MailSenderService.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseUserRepositoryResolver extends JpaRepository<User, Long> {

}
