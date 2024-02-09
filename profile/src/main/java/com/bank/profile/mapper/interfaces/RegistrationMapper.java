package com.bank.profile.mapper.interfaces;

import com.bank.profile.dto.RegistrationDto;
import com.bank.profile.model.Registration;
import org.mapstruct.Mapper;

/**
 * Интерфейс Mapper для преобразования между {@link Registration} и {@link RegistrationDto}.
 */
@Mapper(componentModel = "spring")
public interface RegistrationMapper extends BaseMapper<RegistrationDto, Registration> {

}
