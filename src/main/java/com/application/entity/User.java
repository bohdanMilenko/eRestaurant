package com.application.entity;

import com.application.util.DateConverter;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")

    private int userId;

    @Column(name = "name")

    private String name;

    @Column(name = "last_name")

    private String lastName;

    @Column(name = "email")

    private String email;

    @Column(name = "password")

    private String password;

    @Column(name = "birth_date")
    @Convert(converter = DateConverter.class)

    private LocalDateTime birthDate;

    @Column(name = "phone_number")

    private String phoneNumber;

    @Column(name = "date_created")

    private Timestamp accountCreationDateTime;

    @Column(name = "is_active")

    private boolean isActive;

    @ManyToOne
    @JoinColumn(name = "user_role_id")

    private UserRole userRole;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference

    private List<Address> addressList;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference

    private List<Order> orderList;

    public User() {
    }

    public User(int userId) {
        this.userId = userId;
    }

    public User(String email) {
        this.email = email;
    }

    public User(List<Address> addressList) {
        this.addressList = addressList;
    }

    public User(String email, String phoneNumber, Timestamp accountCreationDateTime,
                UserRole userRole, List<Address> addressList) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.accountCreationDateTime = accountCreationDateTime;
        this.userRole = userRole;
        this.addressList = addressList;
    }

    public User(String name, String lastName, String email, String password, LocalDateTime birthDate,
                String phoneNumber, Timestamp accountCreationDateTime, boolean isActive, UserRole userRole,
                List<Address> addressList, List<Order> orderList) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.accountCreationDateTime = accountCreationDateTime;
        this.isActive = isActive;
        this.userRole = userRole;
        this.addressList = addressList;
        this.orderList = orderList;
    }


}
