package org.example.elegant.user;

import lombok.RequiredArgsConstructor;
import org.example.elegant.common.mapper.CommonMapper;
import org.example.elegant.user.dto.UserCreateDto;
import org.example.elegant.user.dto.UserResponseDto;
import org.example.elegant.user.dto.UserUpdateDto;
import org.example.elegant.user.entity.User;
import org.example.elegant.user.permission.entity.Permission;
import org.example.elegant.user.role.RoleDtoMapper;
import org.example.elegant.user.role.dto.RoleResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
public class UserDtoMapper extends CommonMapper<User, UserCreateDto, UserResponseDto, UserUpdateDto> {
    private final ModelMapper mapper;
    private final RoleDtoMapper roleDtoMapper;
    @Override
    public User toEntity(UserCreateDto userCreatedDto) {

        return mapper.map(userCreatedDto,User.class);
    }

    @Override
    public UserResponseDto toResponseDto(User user) {
        UserResponseDto responseDto = mapper.map(user, UserResponseDto.class);

        Set<RoleResponseDto> roles = user
                .getRoles()
                .stream()
                .map(roleDtoMapper::toResponseDto)
                .collect(Collectors.toSet());

        if (user.getPermissions()!=null) {
            Set<String> permissions = user
                    .getPermissions()
                    .stream()
                    .map(Permission::getName)
                    .collect(Collectors.toSet());

            responseDto.setPermissions(permissions);
        }
        responseDto.setRoles(roles);
        return responseDto;
    }

    public UserResponseDto userResponseDto(UserCreateDto createDto){
       return mapper.map(createDto, UserResponseDto.class);
    }

    @Override
    public void toEntity(UserUpdateDto userUpdateDto, User user) {
        mapper.map(userUpdateDto,user);

    }
}
