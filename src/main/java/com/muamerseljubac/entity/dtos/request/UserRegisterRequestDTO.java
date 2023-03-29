package com.muamerseljubac.entity.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterRequestDTO {

    private String firstName;

    private String lastName;

    private String email;

    private String roles;

    private String password;

}
