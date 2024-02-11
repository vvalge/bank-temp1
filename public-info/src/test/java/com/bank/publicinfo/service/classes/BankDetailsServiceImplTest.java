package com.bank.publicinfo.service.classes;

import com.bank.publicinfo.dto.BankDetailsDto;
import com.bank.publicinfo.entity.BankDetailsEntity;
import com.bank.publicinfo.exception.NotFoundException;
import com.bank.publicinfo.mapper.BankDetailsMapper;
import com.bank.publicinfo.repository.BankDetailsRepository;
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
class BankDetailsServiceImplTest {

    private static final BankDetailsEntity expectedEntity = new BankDetailsEntity();
    private static final BankDetailsDto expectedDto = new BankDetailsDto();
    private static final Long id = 1L;
    private static final Long inn = 2L;
    private static final Long bik = 3L;
    private static final Long kpp = 4L;
    private static final Integer corAccount = 5;

    static {
        expectedEntity.setId(id);
        expectedDto.setId(id);
        expectedEntity.setInn(inn);
        expectedDto.setInn(inn);
        expectedEntity.setBik(bik);
        expectedDto.setBik(bik);
        expectedEntity.setKpp(kpp);
        expectedDto.setKpp(kpp);
        expectedEntity.setCorAccount(corAccount);
        expectedDto.setCorAccount(corAccount);
    }

    @Mock
    private BankDetailsRepository bankDetailsRepository;
    @Mock
    private BankDetailsMapper bankDetailsMapper;
    @InjectMocks
    private BankDetailsServiceImpl bankDetailsService;

    @Test
    void test_findByBik() {
        when(bankDetailsRepository.findByBik(bik)).thenReturn(Optional.of(expectedEntity));

        assertEquals(bankDetailsMapper.toDto(expectedEntity), bankDetailsService.findByBik(bik));
    }

    @Test
    void test_findByInn() {
        when(bankDetailsRepository.findByInn(inn)).thenReturn(Optional.of(expectedEntity));

        assertEquals(bankDetailsMapper.toDto(expectedEntity), bankDetailsService.findByInn(inn));
    }

    @Test
    void test_findByKpp() {
        when(bankDetailsRepository.findByKpp(kpp)).thenReturn(Optional.of(expectedEntity));

        assertEquals(bankDetailsMapper.toDto(expectedEntity), bankDetailsService.findByKpp(kpp));
    }

    @Test
    void test_findByCorAccount() {
        when(bankDetailsRepository.findByCorAccount(corAccount)).thenReturn(Optional.of(expectedEntity));

        assertEquals(bankDetailsMapper.toDto(expectedEntity), bankDetailsService.findByCorAccount(corAccount));
    }

    @Test
    void test_findAll() {
        when(bankDetailsRepository.findAll()).thenReturn(List.of(expectedEntity, expectedEntity));

        assertEquals(bankDetailsMapper.toDtoList(List.of(expectedEntity, expectedEntity)), bankDetailsService.findAll());
    }

    @Test
    void test_findByBik_error() {
        when(bankDetailsRepository.findByBik(bik)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> bankDetailsService.findByBik(bik));

        verify(bankDetailsRepository).findByBik(bik);
    }

    @Test
    void test_findByInn_error() {
        when(bankDetailsRepository.findByInn(inn)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> bankDetailsService.findByInn(inn));

        verify(bankDetailsRepository).findByInn(inn);
    }

    @Test
    void test_findByKpp_error() {
        when(bankDetailsRepository.findByKpp(kpp)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> bankDetailsService.findByKpp(kpp));

        verify(bankDetailsRepository).findByKpp(kpp);
    }

    @Test
    void test_findByCorAccount_error() {
        when(bankDetailsRepository.findByCorAccount(corAccount)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> bankDetailsService.findByCorAccount(corAccount));

        verify(bankDetailsRepository).findByCorAccount(corAccount);
    }

}