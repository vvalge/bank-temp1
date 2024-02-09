package com.bank.profile.mapper.interfaces;

import com.bank.profile.dto.ActualRegistrationDto;
import com.bank.profile.model.ActualRegistration;
import org.mapstruct.Mapper;

/**
 * Интерфейс Mapper для преобразования между {@link ActualRegistration} и {@link ActualRegistrationDto}.
 */
@Mapper(componentModel = "spring")
public interface ActualRegistrationMapper extends BaseMapper<ActualRegistrationDto, ActualRegistration> {
    ActualRegistration toEntity(ActualRegistrationDto entity);

    ActualRegistrationDto toDto(ActualRegistration entity);
}
