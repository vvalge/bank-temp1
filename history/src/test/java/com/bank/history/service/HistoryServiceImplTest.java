package com.bank.history.service;

import com.bank.history.dto.HistoryDto;
import com.bank.history.model.HistoryEntity;
import com.bank.history.repository.HistoryRepository;
import com.bank.history.mapper.HistoryMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class HistoryServiceImplTest {
    @InjectMocks
    HistoryServiceImpl historyServiceimpl;
    @Mock
    HistoryRepository historyRepository;

    @Test
    void getAllHistoryTest() {
        final HistoryEntity historyEntity = new HistoryEntity(1L,
                2L,2L,null,
                2L,2L,2L);

        when(historyRepository.findAll()).thenReturn(List.of(historyEntity));
        final List<HistoryDto> actualHistory = historyServiceimpl.getAllHistory();

        assertEquals(List.of(HistoryMapper.INSTANCE.toDto(historyEntity)), actualHistory);
        verify(this.historyRepository).findAll();
    }

    @Test
    void getHistoryByIdTest() {
        final Long historyId = 1L;
        final HistoryEntity historyEntity = new HistoryEntity(historyId,
                2L, 2L, null,
                2L, 2L, 2L);

        when(historyRepository.findById(historyEntity.getId())).thenReturn(Optional.of(historyEntity));

        final HistoryDto foundHistoryDto = historyServiceimpl.getHistoryById(historyId);
        final HistoryDto expectedHistoryDto = HistoryMapper.INSTANCE.toDto(historyEntity);

        assertNotNull(foundHistoryDto);
        assertEquals(expectedHistoryDto, foundHistoryDto);
        verify(this.historyRepository).findById(historyEntity.getId());
    }

    @Test
    void deleteHistoryTest() {
        final HistoryEntity historyEntity = new HistoryEntity(1L,
                2L,2L,null,
                2L,2L,2L);

        historyRepository.deleteById(historyEntity.getId());
        verify(this.historyRepository).deleteById(historyEntity.getId());
    }

    @Test
    void saveHistoryTest() {
        final HistoryEntity historyEntity = new HistoryEntity(1L,
                2L,2L,null,
                2L,2L,2L);
        final HistoryDto historyDto = new HistoryDto(1L,
                2L,2L,null,
                2L,2L,2L);

        when(historyRepository.save(any(HistoryEntity.class))).thenReturn(historyEntity);
        final HistoryDto actualHistory = historyServiceimpl.saveHistory(historyDto);

        assertEquals(HistoryMapper.INSTANCE.toDto(historyEntity), actualHistory);
        verify(this.historyRepository).save(any(HistoryEntity.class));
    }

    @Test
    void updateHistoryTest() {
        final Long id = 1L;
        final HistoryEntity existingHistoryEntity = new HistoryEntity(id, 2L, 2L, 3L, 2L, 2L, 2L);
        final HistoryDto updatedHistoryDto = new HistoryDto(id, 2L, 2L, 3L, 2L, 2L, 2L);

        when(historyRepository.findById(id)).thenReturn(Optional.of(existingHistoryEntity));
        when(historyRepository.save(any(HistoryEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        final HistoryDto actualAudit = historyServiceimpl.getHistoryById(id);

        assertNotNull(actualAudit);
        assertEquals(updatedHistoryDto, actualAudit);
        historyServiceimpl.updateHistory(id, updatedHistoryDto);

        final HistoryEntity updatedHistoryEntity = new HistoryEntity(
                updatedHistoryDto.getId(),
                updatedHistoryDto.getTransferAuditId(),
                updatedHistoryDto.getProfileAuditId(),
                updatedHistoryDto.getAccountAuditId(),
                updatedHistoryDto.getAntiFraudAuditId(),
                updatedHistoryDto.getPublicBankInfoAuditId(),
                updatedHistoryDto.getAuthorizationAuditId()
        );

        verify(this.historyRepository, times(1)).save(updatedHistoryEntity);
    }

}