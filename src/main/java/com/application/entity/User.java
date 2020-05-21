package com.application.entity;

import com.application.entity.UserRole;
import sun.util.resources.LocaleData;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private String name;
    private String lastName;
    private String email;
    private String password;
    private LocalDate birthDate;
    private String phoneNumber;
    private LocalDateTime accountCreationDateTime;

    @ManyToOne
    @JoinColumn(name = "user_role_id")
    private UserRole userRole;
}
