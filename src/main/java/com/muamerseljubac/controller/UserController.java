package com.muamerseljubac.controller;

import com.muamerseljubac.entity.dtos.UserDTO;
import com.muamerseljubac.entity.dtos.request.UserRegisterRequestDTO;
import com.muamerseljubac.entity.dtos.response.JwtResponseDTO;
import com.muamerseljubac.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("")
    public ResponseEntity<JwtResponseDTO> registerUser(@RequestBody UserRegisterRequestDTO requestDTO) {
        return new ResponseEntity<>(userService.registerUser(requestDTO), HttpStatus.OK);
    }

}
