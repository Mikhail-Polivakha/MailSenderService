package com.example.MailSenderService.models;

import lombok.Data;

import javax.persistence.Entity;
import java.util.Date;

@Data
@Entity
public class User extends BaseEntity {

    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private Long account;
}
