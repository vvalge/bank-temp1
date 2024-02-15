package com.bank.publicinfo.service.classes;

import com.bank.publicinfo.dto.AuditDto;
import com.bank.publicinfo.entity.AuditEntity;
import com.bank.publicinfo.exception.NotFoundException;
import com.bank.publicinfo.mapper.AuditMapper;
import com.bank.publicinfo.repository.AuditRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuditServiceImplTest {

    private static final AuditEntity expectedEntity = new AuditEntity();

    private static final AuditDto expectedDto = new AuditDto();
    private static final Long id = 1L;

    static {
        expectedEntity.setId(id);
    }

    @Mock
    private AuditRepository auditRepository;
    @Mock
    private AuditMapper auditMapper;
    @InjectMocks
    private AuditServiceImpl auditService;

    @Test
    void test_findAll() {
        when(auditRepository.findAll()).thenReturn(List.of(expectedEntity, expectedEntity));

        assertEquals(auditMapper.toDtoList(List.of(expectedEntity, expectedEntity)), auditService.findAll());
    }

    //Тесты MainServiceClass

    @Test
    void test_findById() {
        when(auditRepository.findById(id)).thenReturn(Optional.of(expectedEntity));

        assertEquals(auditMapper.toDto(expectedEntity), auditService.findById(id));
    }

    @Test
    void test_findById_error() {
        when(auditRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> auditService.findById(id));

        verify(auditRepository).findById(id);
    }

    @Test
    void test_save() {
        when(auditMapper.toEntity(expectedDto)).thenReturn(expectedEntity);
        when(auditRepository.save(expectedEntity)).thenReturn(expectedEntity);

        assertDoesNotThrow(() -> auditService.save(expectedDto));

        verify(auditMapper).toEntity(expectedDto);
        verify(auditRepository).save(expectedEntity);
    }

    @Test
    void test_update() {
        when(auditMapper.toEntity(expectedDto)).thenReturn(expectedEntity);
        when(auditRepository.save(expectedEntity)).thenReturn(expectedEntity);

        assertDoesNotThrow(() -> auditService.update(id, expectedDto));

        verify(auditMapper).toEntity(expectedDto);
        verify(auditRepository).save(expectedEntity);
    }

    @Test
    void test_deleteById() {
        assertDoesNotThrow(() -> auditService.deleteById(id));

        verify(auditRepository).deleteById(id);
    }

    @Test
    void test_deleteById_error() {
        doThrow(NotFoundException.class).when(auditRepository).deleteById(id);

        assertThrows(NotFoundException.class, () -> auditService.deleteById(id));

        verify(auditRepository).deleteById(id);
    }
}