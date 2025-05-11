package com.example.ignite.server.mapper;

import com.example.common.dto.UserDTO;
import com.example.ignite.server.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toDto(User entity);
    User toEntity(UserDTO dto);
}
