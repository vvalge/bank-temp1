package com.bank.publicinfo.service.classes;

import com.bank.publicinfo.dto.BranchDto;
import com.bank.publicinfo.entity.BranchEntity;
import com.bank.publicinfo.exception.NotFoundException;
import com.bank.publicinfo.mapper.BranchMapper;
import com.bank.publicinfo.repository.BranchRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BranchServiceImplTest {

    private static final BranchEntity expectedEntity = new BranchEntity();
    private static final BranchDto expectedDto = new BranchDto();
    private static final Long id = 1L;
    private static final Long phoneNumber = 2L;

    static {
        expectedEntity.setId(id);
        expectedDto.setId(id);
        expectedEntity.setPhoneNumber(phoneNumber);
        expectedDto.setPhoneNumber(phoneNumber);
    }

    @Mock
    private BranchRepository branchRepository;
    @Mock
    private BranchMapper branchMapper;
    @InjectMocks
    private BranchServiceImpl branchService;

    @Test
    void test_findByPhoneNumber() {
        when(branchRepository.findByPhoneNumber(phoneNumber)).thenReturn(Optional.of(expectedEntity));

        assertEquals(branchMapper.toDto(expectedEntity), branchService.findByPhoneNumber(phoneNumber));
    }

    @Test
    void test_findByPhoneNumber_error() {
        when(branchRepository.findByPhoneNumber(phoneNumber)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> branchService.findByPhoneNumber(phoneNumber));

        verify(branchRepository).findByPhoneNumber(phoneNumber);
    }

    @Test
    void test_findAll() {
        when(branchRepository.findAll()).thenReturn(List.of(expectedEntity, expectedEntity));

        assertEquals(branchMapper.toDtoList(List.of(expectedEntity, expectedEntity)), branchService.findAll());
    }

}