package org.example.elegant.user.role;

import lombok.RequiredArgsConstructor;
import org.example.elegant.common.mapper.CommonMapper;
import org.example.elegant.user.permission.entity.Permission;
import org.example.elegant.user.role.dto.RoleCreateDto;
import org.example.elegant.user.role.dto.RoleResponseDto;
import org.example.elegant.user.role.dto.RoleUpdateDto;
import org.example.elegant.user.role.entity.Role;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RoleDtoMapper extends CommonMapper<Role, RoleCreateDto, RoleResponseDto, RoleUpdateDto> {
    private final ModelMapper modelMapper;
    @Override
    public Role toEntity(RoleCreateDto roleCreatedDto) {
       return modelMapper.map(roleCreatedDto, Role.class);

    }

    @Override
    public RoleResponseDto toResponseDto(Role role) {
        RoleResponseDto responseDto = modelMapper.map(role, RoleResponseDto.class);

        Set<String> permissions = role
                .getPermissions()
                .stream()
                .map(Permission::getName)
                .collect(Collectors.toSet());

        responseDto.setPermissions(permissions);
        return responseDto;
    }

    @Override
    public void toEntity(RoleUpdateDto roleUpdate, Role role) {
        modelMapper.map(roleUpdate,role);
    }
}
