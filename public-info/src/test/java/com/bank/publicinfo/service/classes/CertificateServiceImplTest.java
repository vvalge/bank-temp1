package com.bank.publicinfo.service.classes;

import com.bank.publicinfo.dto.CertificateDto;
import com.bank.publicinfo.entity.BankDetailsEntity;
import com.bank.publicinfo.entity.CertificateEntity;
import com.bank.publicinfo.exception.NotFoundException;
import com.bank.publicinfo.mapper.CertificateMapper;
import com.bank.publicinfo.repository.CertificateRepository;
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
class CertificateServiceImplTest {

    private static final CertificateEntity expectedEntity = new CertificateEntity();
    private static final CertificateDto expectedDto = new CertificateDto();
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
    private CertificateRepository certificateRepository;
    @Mock
    private CertificateMapper certificateMapper;
    @InjectMocks
    private CertificateServiceImpl certificateService;

    @Test
    void test_findAllByBankDetailsId() {
        when(certificateRepository.findAllByBankDetails_Id(bankDetailsId)).thenReturn(List.of(expectedEntity, expectedEntity));

        assertEquals(certificateMapper.toDtoList(List.of(expectedEntity, expectedEntity)), certificateService.findAllByBankDetailsId(bankDetailsId));
    }

    @Test
    void test_findAll() {
        when(certificateRepository.findAll()).thenReturn(List.of(expectedEntity, expectedEntity));

        assertEquals(certificateMapper.toDtoList(List.of(expectedEntity, expectedEntity)), certificateService.findAll());
    }

    @Test
    void test_deleteByCertificateIdAndBankDetailsId() {
        assertDoesNotThrow(() -> certificateService.deleteByCertificateIdAndBankDetailsId(id, bankDetailsId));

        verify(certificateRepository).deleteByIdAndBankDetails_Id(id, bankDetailsId);
    }

    @Test
    void test_deleteByCertificateIdAndBankDetailsId_error() {
        doThrow(NotFoundException.class).when(certificateRepository).deleteByIdAndBankDetails_Id(id, bankDetailsId);

        assertThrows(NotFoundException.class, () -> certificateService.deleteByCertificateIdAndBankDetailsId(id, bankDetailsId));

        verify(certificateRepository).deleteByIdAndBankDetails_Id(id, bankDetailsId);
    }

}