package com.application.entity.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Data
public class UserDTO {
    @NotNull
    String name;
    @NotNull
    String surname;
    String address;
    @NotNull
    String email;
    @NotNull
    String phoneNumber;

    public UserDTO() {
    }

    public UserDTO(@NotNull String name, @NotNull String surname,
                   String address, @NotNull String email, @NotNull String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
