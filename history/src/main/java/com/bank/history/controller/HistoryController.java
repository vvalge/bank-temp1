package com.bank.history.controller;

import com.bank.history.dto.HistoryDto;
import com.bank.history.service.HistoryService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/history")
@AllArgsConstructor
@OpenAPIDefinition(info = @Info(
        title = "History controller",
        version = "1.0.0",
        description = "Контроллер для работы с записями истории"))
@Tag(name = "История операций")
public class HistoryController {

    private final HistoryService historyService;

    @GetMapping
    @Operation(summary = "Получение всех записей")
    public ResponseEntity<List<HistoryDto>> getAllHistory() {
        log.info("Запрос на получение всей истории");
        List<HistoryDto> historyList = historyService.getAllHistory();
        return new ResponseEntity<>(historyList, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    @Operation(summary = "Получение записи по id")
    public ResponseEntity<HistoryDto> getHistoryById(@PathVariable Long id) {
        log.info("Запрос на получение по id = {}", id);
        final HistoryDto historyDto = historyService.getHistoryById(id);
        if (historyDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(historyDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление записи из истории по id")
    public ResponseEntity<Void> deleteHistoryById(@PathVariable Long id) {
        log.info("Запрос на удаление по id = {}", id);
        final HistoryDto historyDto = historyService.getHistoryById(id);
        if (historyDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        historyService.deleteHistory(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Создание новой записи")
    public ResponseEntity<HistoryDto> saveHistory(@RequestBody HistoryDto historyDto) {
        log.info("Запрос на создание новой записи");
        HistoryDto savedHistory = historyService.saveHistory(historyDto);
        return new ResponseEntity<>(savedHistory, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Изменение записи")
    public ResponseEntity<Void> updateHistory(@PathVariable("id") Long id, @RequestBody HistoryDto historyDto) {
        log.info("Запрос редактирования записи по id = {}", id);
        final HistoryDto historyDtoOld = historyService.getHistoryById(id);
        if (historyDtoOld == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        historyService.updateHistory(id, historyDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
