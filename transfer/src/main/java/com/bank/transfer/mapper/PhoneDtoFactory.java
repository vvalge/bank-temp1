package com.bank.transfer.mapper;

import com.bank.transfer.dto.PhoneDto;
import com.bank.transfer.model.PhoneEntity;
import org.springframework.stereotype.Component;

@Component
public class PhoneDtoFactory {
    public PhoneDto makePhoneEntityToDto(PhoneEntity entity) {
        return PhoneDto.builder()
                .id(entity.getId())
                .number(entity.getNumber())
                .amount(entity.getAmount())
                .purpose(entity.getPurpose())
                .accountDetailsId(entity.getAccountDetailsId())
                .build();
    }

    // конвертация Dto в Entity
    public PhoneEntity makePhoneDtoToEntity(PhoneDto dto) {
        return PhoneEntity.builder()
                .id(dto.getId())
                .number(dto.getNumber())
                .amount(dto.getAmount())
                .purpose(dto.getPurpose())
                .accountDetailsId(dto.getAccountDetailsId())
                .build();
        /*PhoneEntity entity = new PhoneEntity();
        entity.setId(dto.getId());
        entity.setNumber(dto.getNumber());
        entity.setAmount(dto.getAmount());
        entity.setPurpose(dto.getPurpose());
        entity.setAccountDetailsId(dto.getAccountDetailsId());
        return entity;*/
    }
}
