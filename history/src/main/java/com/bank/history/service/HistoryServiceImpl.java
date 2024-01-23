package com.bank.history.service;

import com.bank.history.dto.HistoryDto;
import com.bank.history.model.HistoryEntity;
import com.bank.history.mapper.HistoryMapper;
import com.bank.history.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class HistoryServiceImpl implements HistoryService {

    private final HistoryRepository historyRepository;

    @Autowired
    public HistoryServiceImpl(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    /**
     * Получение всех записей истории.
     *
     * @return список DTO истории
     */
    @Override
    @Transactional
    public List<HistoryDto> getAllHistory() {
        final List<HistoryEntity> entities = historyRepository.findAll();
        return entities.stream().map(HistoryMapper.INSTANCE::toDto).toList();
    }

    /**
     * Получение записи истории по идентификатору.
     *
     * @param id идентификатор записи истории
     * @return DTO записи истории или null, если запись не найдена
     */
    @Override
    @Transactional
    public HistoryDto getHistoryById(Long id) {
        final Optional<HistoryEntity> entity = historyRepository.findById(id);
        return entity.map(HistoryMapper.INSTANCE::toDto).orElse(null);
    }

    /**
     * Удаление записи истории по идентификатору.
     *
     * @param id идентификатор записи истории
     */
    @Override
    @Transactional
    public void deleteHistory(Long id) {
        historyRepository.deleteById(id);
    }

    /**
     * Сохранение новой записи истории.
     *
     * @param saveHistory DTO для сохранения
     * @return сохраненная DTO записи истории
     */
    @Override
    @Transactional
    public HistoryDto saveHistory(HistoryDto saveHistory) {
        final HistoryEntity historyEntity = historyRepository.save(HistoryMapper.INSTANCE.toEntity(saveHistory));
        return HistoryMapper.INSTANCE.toDto(historyEntity);
    }

    /**
     * Обновление записи истории по идентификатору.
     *
     * @param id           идентификатор записи истории
     * @param updateHistory обновленная DTO записи истории
     * @return обновленная DTO записи истории
     */
    @Override
    @Transactional
    public HistoryDto updateHistory(Long id, HistoryDto updateHistory) {
        final HistoryEntity historyEntity = historyRepository.save(HistoryMapper.INSTANCE.toEntity(updateHistory));
        return HistoryMapper.INSTANCE.toDto(historyEntity);
    }
}
