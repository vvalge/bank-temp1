package com.bank.profile.mapper.interfaces;

import com.bank.profile.dto.AuditDto;
import com.bank.profile.model.Audit;
import org.mapstruct.Mapper;

/**
 * Интерфейс Mapper для преобразования между {@link Audit} и {@link AuditDto}.
 */
@Mapper(componentModel = "spring")
public interface AuditMapper extends BaseMapper<AuditDto, Audit> {
    Audit toEntity(AuditDto entity);

    AuditDto toDto(Audit entity);
}
