package com.muamerseljubac.service;

import com.muamerseljubac.config.JpaUserDetailsService;
import com.muamerseljubac.config.JwtUtils;
import com.muamerseljubac.entity.dtos.request.UserRegisterRequestDTO;
import com.muamerseljubac.entity.dtos.response.JwtResponseDTO;
import com.muamerseljubac.entity.models.User;
import com.muamerseljubac.mapper.UserMapper;
import com.muamerseljubac.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Service
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final JpaUserDetailsService userDetailsService;

    @Autowired
    public UserService(UserMapper userMapper, UserRepository userRepository, JpaUserDetailsService userDetailsService) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.userDetailsService = userDetailsService;
    }

    public JwtResponseDTO registerUser(UserRegisterRequestDTO requestDTO) {
        User user = userMapper.userRegisterRequestDtoToUser(requestDTO);
        user.setId(UUID.randomUUID());
        userRepository.save(user);

        String accessToken = new JwtUtils().generateToken(userDetailsService.loadUserByUsername(user.getEmail()));

        return new JwtResponseDTO(accessToken);
    }

}
