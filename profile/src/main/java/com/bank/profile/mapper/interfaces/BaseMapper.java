package com.bank.profile.mapper.interfaces;

public interface BaseMapper <DTO, ENTITY>{

    ENTITY toEntity(DTO dto);

    DTO toDto(ENTITY entity);
}
