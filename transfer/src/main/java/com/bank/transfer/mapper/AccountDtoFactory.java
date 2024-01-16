package com.bank.transfer.mapper;

import com.bank.transfer.dto.AccountDto;
import com.bank.transfer.model.AccountEntity;
import org.springframework.stereotype.Component;

@Component
public class AccountDtoFactory {

    public AccountDto makeAccountEntityToDto(AccountEntity entity) {
        return AccountDto.builder()
                .id(entity.getId())
                .number(entity.getNumber())
                .amount(entity.getAmount())
                .purpose(entity.getPurpose())
                .accountDetailsId(entity.getAccountDetailsId())
                .build();
    }

    public AccountEntity makeAccountDtoToEntity(AccountDto dto) {
        return AccountEntity.builder()
                .id(dto.getId())
                .number(dto.getNumber())
                .amount(dto.getAmount())
                .purpose(dto.getPurpose())
                .accountDetailsId(dto.getAccountDetailsId())
                .build();
    }
}
