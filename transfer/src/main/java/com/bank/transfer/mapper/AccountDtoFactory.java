package com.bank.transfer.mapper;

import com.bank.transfer.dto.AccountDto;
import com.bank.transfer.model.AccountEntity;
import org.springframework.stereotype.Component;

/**
 * Класс преобразования модели для переводов по номеру счета в Dto и обратно
 */
@Component
public class AccountDtoFactory {

    /**
     * метод преобразования модели в Dto
     * @param entity модель для переводов по номеру счета
     * @return AccountDto
     */
    public AccountDto makeAccountEntityToDto(AccountEntity entity) {
        return AccountDto.builder()
                .id(entity.getId())
                .number(entity.getNumber())
                .amount(entity.getAmount())
                .purpose(entity.getPurpose())
                .accountDetailsId(entity.getAccountDetailsId())
                .build();
    }

    /**
     * метод преобразования Dto в модель
     * @param dto Dto для переводов по номеру счета
     * @return AccountEntity
     */
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
