package org.example.elegant.common.service;

import org.example.elegant.common.mapper.CommonMapper;
import org.example.elegant.common.repository.CommonRepository;

public abstract class CommonService<ENTITY, ID, CREATE_DTO, RESPONSE_DTO, UPDATE_DTO> {
    protected abstract CommonRepository<ENTITY, ID> getRepository();

    protected abstract Class<ENTITY> getEntityClass();

    protected abstract CommonMapper<ENTITY, CREATE_DTO, RESPONSE_DTO, UPDATE_DTO> getMapper();

    protected abstract RESPONSE_DTO internalCreate(CREATE_DTO createDto);
}
