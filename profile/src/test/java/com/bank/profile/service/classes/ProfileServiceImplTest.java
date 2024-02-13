package com.bank.profile.service.classes;


import com.bank.profile.dto.ProfileDto;
import com.bank.profile.exceptionHandler.exception.NotFoundExceptionEntity;
import com.bank.profile.mapper.ProfileMapperImpl;
import com.bank.profile.model.Profile;
import com.bank.profile.repository.ActualRegistrationRepository;
import com.bank.profile.repository.PassportRepository;
import com.bank.profile.repository.ProfileRepository;
import com.bank.profile.services.classes.ProfileServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProfileServiceImplTest {
    @Mock
    private ProfileRepository profileRepository;
    @Mock
    private ActualRegistrationRepository actualRegistrationRepository;
    @Mock
    private PassportRepository passportRepository;

    @Mock
    private ProfileMapperImpl mapper;
    private static final Profile expectedModel = new Profile();
    private static final Profile actualModel = new Profile();
    private static final ProfileDto dto = new ProfileDto();

    private static final long id = 1L;

    static {
        expectedModel.setId(id);
        actualModel.setId(id);
        dto.setActualRegistrationId(id);
        dto.setPassport_id(id);
    }

    @InjectMocks
    private ProfileServiceImpl service;

    @Test
    void testGetById() {

        when(profileRepository.findById(id)).thenReturn(Optional.of(expectedModel));

        assertEquals(expectedModel, service.getById(id));

    }

    @Test
    void testDeleteById() {
        assertDoesNotThrow(() -> service.deleteById(id));

        verify(profileRepository).deleteById(id);
    }

    @Test
    void testCreate() {
        when(mapper.toEntity(dto)).thenReturn(expectedModel);
        when(profileRepository.save(expectedModel)).thenReturn(actualModel);
        when(actualRegistrationRepository.existsById(dto.getActualRegistrationId())).thenReturn(true);
        when(passportRepository.existsById(dto.getPassport_id())).thenReturn(true);

        assertDoesNotThrow(() -> service.create(dto));

        verify(mapper).toEntity(dto);
        verify(profileRepository).save(expectedModel);
    }


    @Test
    void testUpdate() {
        when(profileRepository.existsById(id)).thenReturn(true);
        when(mapper.toEntity(dto)).thenReturn(expectedModel);
        when(profileRepository.save(expectedModel)).thenReturn(actualModel);
        when(profileRepository.existsById(id)).thenReturn(true);
        when(actualRegistrationRepository.existsById(dto.getActualRegistrationId())).thenReturn(true);
        when(passportRepository.existsById(dto.getPassport_id())).thenReturn(true);

        assertDoesNotThrow(() -> service.update(id, dto));

        verify(profileRepository).existsById(id);
        verify(mapper).toEntity(dto);
        verify(profileRepository).save(expectedModel);
    }


    @Test
    void testUpdate_ThrowsNotFoundException() {
        when(profileRepository.existsById(id)).thenReturn(false);

        assertThrows(NotFoundExceptionEntity.class, () -> service.update(id, dto));

        verify(profileRepository, times(1)).existsById(id);
        verify(mapper, never()).toEntity(dto);
        verify(profileRepository, never()).save(any());
    }

    @Test
    void testGet_ThrowsNotFoundException() {
        when(profileRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundExceptionEntity.class, () -> service.getById(id));

        verify(profileRepository).findById(id);
    }


    @Test
    void testDelete_ThrowsNotFoundException() {
        doThrow(EmptyResultDataAccessException.class).when(profileRepository).deleteById(id);

        assertThrows(NotFoundExceptionEntity.class, () -> service.deleteById(id));

        verify(profileRepository).deleteById(id);
    }

    @Test
    void testCreate_ThrowsNotFoundException_if_passportRepository_return_false() {
        when(actualRegistrationRepository.existsById(dto.getActualRegistrationId())).thenReturn(true);
        when(passportRepository.existsById(dto.getPassport_id())).thenReturn(false);

        assertThrows(NotFoundExceptionEntity.class, () -> service.create(dto));

        verify(profileRepository, never()).save(any());
    }

    @Test
    void create_ShouldThrowNotFoundException_WhenActualRegistrationNotExists() {
        when(actualRegistrationRepository.existsById(dto.getActualRegistrationId())).thenReturn(false);

        assertThrows(NotFoundExceptionEntity.class, () -> service.create(dto));
    }

    @Test
    void create_ShouldThrowNotFoundException_WhenPassportNotExists() {
        when(actualRegistrationRepository.existsById(any())).thenReturn(true);
        when(passportRepository.existsById(any())).thenReturn(false);

        assertThrows(NotFoundExceptionEntity.class, () -> service.create(new ProfileDto()));
    }

    @Test
    void update_ShouldThrowNotFoundException_WhenProfileNotExists() {
        when(profileRepository.existsById(any())).thenReturn(false);

        assertThrows(NotFoundExceptionEntity.class, () -> service.update(id, dto));
    }

    @Test
    void update_ShouldThrowNotFoundException_WhenActualRegistrationNotExists() {
        when(profileRepository.existsById(any())).thenReturn(true);
        when(actualRegistrationRepository.existsById(any())).thenReturn(false);

        assertThrows(NotFoundExceptionEntity.class, () -> service.update(id, dto));
    }

    @Test
    void update_ShouldThrowNotFoundException_WhenPassportNotExists() {
        when(profileRepository.existsById(any())).thenReturn(true);
        when(actualRegistrationRepository.existsById(any())).thenReturn(true);
        when(passportRepository.existsById(any())).thenReturn(false);

        assertThrows(NotFoundExceptionEntity.class, () -> service.update(id, dto));
    }
}