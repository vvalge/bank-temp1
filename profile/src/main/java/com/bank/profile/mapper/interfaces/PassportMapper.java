package com.bank.profile.mapper.interfaces;

import com.bank.profile.dto.PassportDto;
import com.bank.profile.model.Passport;

/**
 * Интерфейс Mapper для преобразования между {@link Passport} и {@link PassportDto}.
 */
public interface PassportMapper extends BaseMapper<PassportDto, Passport> {
}
