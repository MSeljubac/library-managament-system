package com.muamerseljubac.entity.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "lms_user")
@Table(name = "lms_user", uniqueConstraints = @UniqueConstraint(name = "email_unique", columnNames = "email"))
public class User {

    @Id
    private UUID id;

    private String firstName;

    private String lastName;

    private String email;

    private String roles;

    private String password;

}