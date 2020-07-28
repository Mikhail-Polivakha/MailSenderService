package com.example.MailSenderService.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "users")
@AllArgsConstructor
public class User extends BaseEntity {

    private String firstName;
    private String lastName;

    @Column(name = "dateOfBirth")
    private Date dateOfBirth;
    private Long account;
}
