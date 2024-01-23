package com.bank.history.mapper;

import com.bank.history.dto.HistoryDto;
import com.bank.history.model.HistoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface HistoryMapper {
    HistoryMapper INSTANCE = Mappers.getMapper(HistoryMapper.class);
    HistoryEntity toEntity(HistoryDto historyDto);
    HistoryDto toDto(HistoryEntity historyEntity);
}