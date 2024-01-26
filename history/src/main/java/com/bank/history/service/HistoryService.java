package com.bank.history.service;


import com.bank.history.dto.HistoryDto;

import java.util.List;

public interface HistoryService {
    List<HistoryDto> getAllHistory();

    HistoryDto getHistoryById(Long id);

    void deleteHistory(Long id);

    HistoryDto saveHistory(HistoryDto saveHistory);

    HistoryDto updateHistory(Long id, HistoryDto historyDto);

}