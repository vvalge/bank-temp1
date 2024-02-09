package com.bank.profile.mapper.interfaces;


import com.bank.profile.dto.ProfileDto;
import com.bank.profile.model.Profile;

/**
 * Интерфейс Mapper для преобразования между {@link Profile} и {@link ProfileDto}.
 */
public interface ProfileMapper extends BaseMapper<ProfileDto, Profile> {
}
