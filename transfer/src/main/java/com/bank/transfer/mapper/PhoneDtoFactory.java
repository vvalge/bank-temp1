package com.bank.transfer.mapper;

import com.bank.transfer.dto.PhoneDto;
import com.bank.transfer.model.PhoneEntity;
import org.springframework.stereotype.Component;

/**
 * Класс преобразования модели для переводов по номеру телефона в Dto и обратно
 */
@Component
public class PhoneDtoFactory {

    /**
     * метод преобразования модели в Dto
     * @param entity модель для переводов по номеру телефона
     * @return PhoneDto
     */
    public PhoneDto makePhoneEntityToDto(PhoneEntity entity) {
        return PhoneDto.builder()
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
     * @return PhoneEntity
     */
    public PhoneEntity makePhoneDtoToEntity(PhoneDto dto) {
        return PhoneEntity.builder()
                .id(dto.getId())
                .number(dto.getNumber())
                .amount(dto.getAmount())
                .purpose(dto.getPurpose())
                .accountDetailsId(dto.getAccountDetailsId())
                .build();
    }
}
