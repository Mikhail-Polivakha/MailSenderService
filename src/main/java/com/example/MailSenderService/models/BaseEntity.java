package com.example.MailSenderService.models;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@MappedSuperclass
@Data
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "created")
    @CreatedDate
    private Date dateOfCreation;

    @Column(name = "updated")
    @CreatedDate
    private Date lastUpdatedDate;
}
