package com.bank.publicinfo.service.classes;

import com.bank.publicinfo.dto.AtmDto;
import com.bank.publicinfo.entity.AtmEntity;
import com.bank.publicinfo.entity.BranchEntity;
import com.bank.publicinfo.exception.NotFoundException;
import com.bank.publicinfo.mapper.AtmMapper;
import com.bank.publicinfo.repository.AtmRepository;
import com.bank.publicinfo.repository.BranchRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AtmServiceImplTest {

    private static final AtmEntity expectedEntity = new AtmEntity();
    private static final AtmEntity actualEntity = new AtmEntity();
    private static final AtmDto expectedDto = new AtmDto();
    private static final AtmEntity actualEntityWithoutBranch = new AtmEntity();
    private static final BranchEntity branch = new BranchEntity();
    private static final Long id = 1L;
    private static final Long branchId = 2L;

    static {
        expectedEntity.setId(id);
        actualEntity.setId(id);
        actualEntityWithoutBranch.setId(id);
        expectedDto.setId(id);
        branch.setId(branchId);
        expectedEntity.setBranch(branch);
        actualEntity.setBranch(branch);
    }

    @Mock
    private AtmRepository atmRepository;
    @Mock
    private AtmMapper atmMapper;
    @Mock
    private BranchRepository branchRepository;
    @InjectMocks
    private AtmServiceImpl atmService;

    @Test
    void test_findAllByBranchId() {
        when(atmRepository.findAllByBranch_Id(branchId)).thenReturn(List.of(expectedEntity, expectedEntity));

        assertEquals(atmMapper.toDtoList(List.of(expectedEntity, expectedEntity)), atmService.findAllByBranchId(branchId));
    }

    @Test
    void test_findAll() {
        when(atmRepository.findAll()).thenReturn(List.of(expectedEntity, expectedEntity));

        assertEquals(atmMapper.toDtoList(List.of(expectedEntity, expectedEntity)), atmService.findAll());
    }

    @Test
    void test_save_withoutBranch() {
        when(atmMapper.toEntity(expectedDto)).thenReturn(expectedEntity);
        when(atmRepository.save(expectedEntity)).thenReturn(actualEntityWithoutBranch);

        assertDoesNotThrow(() -> atmService.save(expectedDto));

        verify(atmMapper).toEntity(expectedDto);
        verify(atmRepository).save(expectedEntity);
    }

    @Test
    void test_save_withBranch() {
        when(atmMapper.toEntity(expectedDto)).thenReturn(expectedEntity);
        when(atmRepository.save(expectedEntity)).thenReturn(actualEntity);

        assertDoesNotThrow(() -> atmService.save(expectedDto));

        verify(atmMapper).toEntity(expectedDto);
        verify(atmRepository).save(expectedEntity);
    }

    @Test
    void test_update_withBranch() {
        when(atmMapper.toEntity(expectedDto)).thenReturn(expectedEntity);
        when(atmRepository.save(expectedEntity)).thenReturn(actualEntity);

        assertDoesNotThrow(() -> atmService.update(id, expectedDto));

        verify(atmMapper).toEntity(expectedDto);
        verify(atmRepository).save(expectedEntity);
    }

    @Test
    void test_update_withoutBranch() {
        when(atmMapper.toEntity(expectedDto)).thenReturn(expectedEntity);
        when(atmRepository.save(expectedEntity)).thenReturn(actualEntityWithoutBranch);

        assertDoesNotThrow(() -> atmService.update(id, expectedDto));

        verify(atmMapper).toEntity(expectedDto);
        verify(atmRepository).save(expectedEntity);
    }

    @Test
    void test_deleteById_successful() {
        assertDoesNotThrow(() -> atmService.deleteById(id));

        verify(atmRepository).deleteById(id);
    }

    @Test
    void test_deleteById_error() {
        doThrow(NotFoundException.class).when(atmRepository).deleteById(id);

        assertThrows(NotFoundException.class, () -> atmService.deleteById(id));

        verify(atmRepository).deleteById(id);
    }

}