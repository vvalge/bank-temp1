package com.bank.transfer.mapper;

import com.bank.transfer.dto.CardDto;
import com.bank.transfer.model.CardEntity;
import org.springframework.stereotype.Component;

@Component
public class CardDtoFactory {
    public CardDto makeCardEntityToDto(CardEntity entity) {
        return CardDto.builder()
                .id(entity.getId())
                .number(entity.getNumber())
                .amount(entity.getAmount())
                .purpose(entity.getPurpose())
                .accountDetailsId(entity.getAccountDetailsId())
                .build();
    }

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
