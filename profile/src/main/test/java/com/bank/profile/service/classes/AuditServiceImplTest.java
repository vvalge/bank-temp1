package com.bank.profile.service.classes;

import com.bank.profile.dto.AuditDto;
import com.bank.profile.exception.NotFoundExceptionEntity;
import com.bank.profile.mapper.AuditMapperImpl;
import com.bank.profile.model.Audit;
import com.bank.profile.repository.AuditRepository;
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
class AuditServiceImplTest {
    @Mock
    private AuditRepository repository;

    @Mock
    private AuditMapperImpl mapper;
    private static final Audit expectedModel = new Audit();
    private static final Audit actualModel = new Audit();
    private static final AuditDto dto = new AuditDto();

    private static final long id = 1L;

    static {
        expectedModel.setId(id);
        actualModel.setId(id);
    }

    @InjectMocks
    private AuditServiceImpl service;

    @Test
    void testGetById() {

        when(repository.findById(id)).thenReturn(Optional.of(expectedModel));

        assertEquals(expectedModel, service.getById(id));

    }

    @Test
    void testDeleteById() {
        assertDoesNotThrow(() -> service.deleteById(id));

        verify(repository).deleteById(id);
    }

    @Test
    void testCreate() {
        when(mapper.toEntity(dto)).thenReturn(expectedModel);
        when(repository.save(expectedModel)).thenReturn(actualModel);

        assertDoesNotThrow(() -> service.create(dto));

        verify(mapper).toEntity(dto);
        verify(repository).save(expectedModel);
    }


    @Test
    void testUpdate() {
        when(repository.existsById(id)).thenReturn(true);
        when(mapper.toEntity(dto)).thenReturn(expectedModel);
        when(repository.save(expectedModel)).thenReturn(actualModel);

        assertDoesNotThrow(() -> service.update(id, dto));

        verify(repository).existsById(id);
        verify(mapper).toEntity(dto);
        verify(repository).save(expectedModel);
    }


    @Test
    void testUpdate_ThrowsNotFoundException() {
        when(repository.existsById(id)).thenReturn(false);

        assertThrows(NotFoundExceptionEntity.class, () -> service.update(id, dto));

        verify(repository, times(1)).existsById(id);
        verify(mapper, never()).toEntity(dto);
        verify(repository, never()).save(any());
    }

    @Test
    void testGet_ThrowsNotFoundException() {
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundExceptionEntity.class, () -> service.getById(id));

        verify(repository).findById(id);
    }


    @Test
    void testDelete_ThrowsNotFoundException() {
        doThrow(EmptyResultDataAccessException.class).when(repository).deleteById(id);

        assertThrows(NotFoundExceptionEntity.class, () -> service.deleteById(id));

        verify(repository).deleteById(id);
    }
}