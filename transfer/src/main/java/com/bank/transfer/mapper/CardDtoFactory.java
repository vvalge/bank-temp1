package com.bank.transfer.mapper;

import com.bank.transfer.dto.CardDto;
import com.bank.transfer.model.CardEntity;
import org.springframework.stereotype.Component;

/**
 * Класс преобразования модели для переводов по номеру карты в Dto и обратно
 */
@Component
public class CardDtoFactory {

    /**
     * метод преобразования модели в Dto
     * @param entity модель для переводов по номеру карты
     * @return CardDto
     */
    public CardDto makeCardEntityToDto(CardEntity entity) {
        return CardDto.builder()
                .id(entity.getId())
                .number(entity.getNumber())
                .amount(entity.getAmount())
                .purpose(entity.getPurpose())
                .accountDetailsId(entity.getAccountDetailsId())
                .build();
    }

    /**
     * метод преобразования Dto в модель
     * @param dto Dto для переводов по номеру телефона
     * @return CardEntity
     */
    public CardEntity makeCardDtoToEntity(CardDto dto) {
        return CardEntity.builder()
                .id(dto.getId())
                .number(dto.getNumber())
                .amount(dto.getAmount())
                .purpose(dto.getPurpose())
                .accountDetailsId(dto.getAccountDetailsId())
                .build();
    }
}
