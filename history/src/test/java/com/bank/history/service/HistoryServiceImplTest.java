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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HistoryServiceImplTest {
    @InjectMocks
    HistoryServiceImpl historyServiceimpl;
    @Mock
    HistoryRepository historyRepository;

    @Test
    void getAllHistory() {
        final HistoryEntity historyEntity = new HistoryEntity(1L,
                2L,2L,null,
                2L,2L,2L);

        when(historyRepository.findAll()).thenReturn(List.of(historyEntity));
        final List<HistoryDto> actualHistory = historyServiceimpl.getAllHistory();

        assertEquals(List.of(HistoryMapper.INSTANCE.toDto(historyEntity)), actualHistory);
        verify(this.historyRepository).findAll();
    }

    @Test
    void getHistoryById() {
        final Long historyId = 1L;
        final HistoryEntity historyEntity = new HistoryEntity(historyId,
                2L, 2L, null,
                2L, 2L, 2L);

        when(historyRepository.findById(historyEntity.getId())).thenReturn(Optional.of(historyEntity));

        final HistoryDto foundHistoryDto = historyServiceimpl.getHistoryById(historyId);
        final HistoryDto expectedHistoryDto = HistoryMapper.INSTANCE.toDto(historyEntity);

        assertEquals(expectedHistoryDto, foundHistoryDto);
        verify(this.historyRepository).findById(historyEntity.getId());
    }

    @Test
    void deleteHistory() {
        final HistoryEntity historyEntity = new HistoryEntity(1L,
                2L,2L,null,
                2L,2L,2L);

        historyRepository.deleteById(historyEntity.getId());
        verify(this.historyRepository).deleteById(historyEntity.getId());
    }

    @Test
    void saveHistory() {
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





}