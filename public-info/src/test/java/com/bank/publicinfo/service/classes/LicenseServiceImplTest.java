package com.bank.publicinfo.service.classes;

import com.bank.publicinfo.dto.LicenseDto;
import com.bank.publicinfo.entity.BankDetailsEntity;
import com.bank.publicinfo.entity.LicenseEntity;
import com.bank.publicinfo.exception.NotFoundException;
import com.bank.publicinfo.mapper.LicenseMapper;
import com.bank.publicinfo.repository.LicenseRepository;
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
class LicenseServiceImplTest {

    private static final LicenseEntity expectedEntity = new LicenseEntity();
    private static final LicenseDto expectedDto = new LicenseDto();
    private static final BankDetailsEntity bankDetailsEntity = new BankDetailsEntity();
    private static final Long id = 1L;
    private static final Long bankDetailsId = 2L;

    static {
        expectedEntity.setId(id);
        expectedDto.setId(id);
        bankDetailsEntity.setId(bankDetailsId);
        expectedEntity.setBankDetails(bankDetailsEntity);
    }

    @Mock
    private LicenseRepository licenseRepository;
    @Mock
    private LicenseMapper licenseMapper;
    @InjectMocks
    private LicenseServiceImpl licenseService;

    @Test
    void test_findAllByBankDetailsId() {
        when(licenseRepository.findAllByBankDetails_Id(bankDetailsId)).thenReturn(List.of(expectedEntity, expectedEntity));

        assertEquals(licenseMapper.toDtoList(List.of(expectedEntity, expectedEntity)), licenseService.findAllByBankDetailsId(bankDetailsId));
    }

    @Test
    void test_findAll() {
        when(licenseRepository.findAll()).thenReturn(List.of(expectedEntity, expectedEntity));

        assertEquals(licenseMapper.toDtoList(List.of(expectedEntity, expectedEntity)), licenseService.findAll());
    }

    @Test
    void test_deleteByLicenseIdAndBankDetailsId() {
        assertDoesNotThrow(() -> licenseService.deleteByLicenseIdAndBankDetailsId(id, bankDetailsId));

        verify(licenseRepository).deleteByIdAndBankDetails_Id(id, bankDetailsId);
    }

    @Test
    void test_deleteByLicenseIdAndBankDetailsId_error() {
        doThrow(NotFoundException.class).when(licenseRepository).deleteByIdAndBankDetails_Id(id, bankDetailsId);

        assertThrows(NotFoundException.class, () -> licenseService.deleteByLicenseIdAndBankDetailsId(id, bankDetailsId));

        verify(licenseRepository).deleteByIdAndBankDetails_Id(id, bankDetailsId);
    }

}