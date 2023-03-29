package com.muamerseljubac.mapper;

import com.muamerseljubac.entity.dtos.UserDTO;
import com.muamerseljubac.entity.dtos.request.UserRegisterRequestDTO;
import com.muamerseljubac.entity.models.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User userRegisterRequestDtoToUser(UserRegisterRequestDTO requestDTO);

    UserDTO userToUserDto(User user);

}
