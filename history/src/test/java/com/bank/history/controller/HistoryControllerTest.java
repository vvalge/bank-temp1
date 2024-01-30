package com.bank.history.controller;

import com.bank.history.dto.HistoryDto;
import com.bank.history.model.HistoryEntity;
import com.bank.history.mapper.HistoryMapper;
import com.bank.history.service.HistoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class HistoryControllerTest {
    @InjectMocks
    private HistoryController historyController;
    @Mock
    private HistoryService historyService;

    private final HistoryMapper historyMapper = Mappers.getMapper(HistoryMapper.class);

    @Test
    void getAllHistoryTest() {
        final HistoryEntity historyEntity = new HistoryEntity(1L, 2L, 2L, null, 2L, 2L, 2L);
        final HistoryDto historyDto = new HistoryDto(1L, 2L, 2L, null, 2L, 2L, 2L);

        when(historyService.getAllHistory()).thenReturn(List.of(historyMapper.toDto(historyEntity)));
        ResponseEntity<List<HistoryDto>> responseEntity = ResponseEntity.ok(List.of(historyDto));
        ResponseEntity<List<HistoryDto>> actualResponseEntity = historyController.getAllHistory();


        assertEquals(responseEntity.getBody(), actualResponseEntity.getBody());
        verify(historyService, times(1)).getAllHistory();
    }


    @Test
    void getHistoryByIdTest() {
        final HistoryEntity historyEntity = new HistoryEntity(1L,
                2L,2L,null,
                2L,2L,2L);
        final HistoryDto HistoryDto = new HistoryDto(1L,
                2L,2L,null,
                2L,2L,2L);

        final ResponseEntity<HistoryDto> responseEntity = ResponseEntity.ok().body(HistoryDto);
        when(historyService.getHistoryById(1L)).thenReturn(historyMapper.toDto(historyEntity));
        final  ResponseEntity<HistoryDto> actualResponseEntity = historyController.getHistoryById(1L);

        assertEquals(responseEntity,actualResponseEntity);
        verify(historyService, times(1)).getHistoryById(1L);

    }
    @Test
    void deleteHistoryByIdTest() {
        final Long id = 1L;

        when(historyService.getHistoryById(id)).thenReturn(historyMapper.toDto(new HistoryEntity()));
        historyController.deleteHistoryById(id);

        verify(historyService).getHistoryById(id);
        verify(historyService).deleteHistory(id);
    }




    @Test
    void saveHistoryTest() {
        final HistoryDto historyDto = new HistoryDto(1L, 2L, 2L, null, 2L, 2L, 2L);
        final ResponseEntity<HistoryDto> responseEntity = ResponseEntity.status(HttpStatus.CREATED).body(historyDto);
        final ResponseEntity<HistoryDto> actualResponseEntity = historyController.saveHistory(historyDto);

        assertEquals(responseEntity.getStatusCode(), actualResponseEntity.getStatusCode());
        verify(historyService, times(1)).saveHistory(historyDto);
    }

    @Test
    void updateHistoryTest() {
        final Long id = 1L;
        final HistoryEntity existingHistoryEntity = new HistoryEntity(id, 2L, 2L, null, 2L, 2L, 2L);
        final HistoryDto updatedHistoryDto = new HistoryDto(id, 3L, 3L, null, 3L, 3L, 3L);

        when(historyService.getHistoryById(1L)).thenReturn(historyMapper.toDto(existingHistoryEntity));
        when(historyService.updateHistory(id, updatedHistoryDto)).thenReturn(null);

        ResponseEntity<Void> responseEntity = historyController.updateHistory(id, updatedHistoryDto);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(historyService, times(1)).updateHistory(id, updatedHistoryDto);
    }

}