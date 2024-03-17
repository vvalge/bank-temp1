package com.bank.antifraud.mapper;

import com.bank.antifraud.dto.AccountTransferDto;
import com.bank.antifraud.dto.CardTransferDto;
import com.bank.antifraud.dto.PhoneTransferDto;
import com.bank.antifraud.model.SuspiciousAccountTransfer;
import com.bank.antifraud.model.SuspiciousCardTransfer;
import com.bank.antifraud.model.SuspiciousPhoneTransfer;
import org.springframework.stereotype.Component;

@Component
public class SuspiciousTransferDtoFactory {

    /**
     * метод преобразования модели трансферов из аккаунта в Dto
     * @param accountTransfer model подозрительных трансферов из аккаунта
     * @return AccountTransferDto
     */
    public AccountTransferDto makeAccountTransferToDto(SuspiciousAccountTransfer accountTransfer) {
        return AccountTransferDto.builder()
                .accountTransferId(accountTransfer.getAccountTransferId())
                .isBlocked(accountTransfer.getIsBlocked())
                .isSuspicious(accountTransfer.getIsSuspicious())
                .blockedReason(accountTransfer.getBlockedReason())
                .suspiciousReason(accountTransfer.getSuspiciousReason())
                .build();
    }

    /**
     * метод преобразования Dto трансферов из аккаунта в модель
     * @param dto Dto подозрительных трансферов из аккаунта
     * @return SuspiciousAccountTransfer
     */
    public SuspiciousAccountTransfer makeDtoToAccountTransfer(AccountTransferDto dto) {
        return SuspiciousAccountTransfer.builder()
                .accountTransferId(dto.getAccountTransferId())
                .isBlocked(dto.getIsBlocked())
                .isSuspicious(dto.getIsSuspicious())
                .blockedReason(dto.getBlockedReason())
                .suspiciousReason(dto.getSuspiciousReason())
                .build();
    }

    /**
     * метод преобразования модели трансферов с карты в Dto
     * @param cardTransfer model подозрительных трансферов с карты
     * @return CardTransferDto
     */
    public CardTransferDto makeCardTransferToDto(SuspiciousCardTransfer cardTransfer) {
        return CardTransferDto.builder()
                .cardTransferId(cardTransfer.getCardTransferId())
                .isBlocked(cardTransfer.getIsBlocked())
                .isSuspicious(cardTransfer.getIsSuspicious())
                .blockedReason(cardTransfer.getBlockedReason())
                .suspiciousReason(cardTransfer.getSuspiciousReason())
                .build();
    }

    /**
     * метод преобразования Dto трансферов с карты в модель
     * @param dto Dto подозрительных трансферов с карты
     * @return SuspiciousCardTransfer
     */
    public SuspiciousCardTransfer makeDtoToCardTransfer(CardTransferDto dto) {
        return SuspiciousCardTransfer.builder()
                .cardTransferId(dto.getCardTransferId())
                .isBlocked(dto.getIsBlocked())
                .isSuspicious(dto.getIsSuspicious())
                .blockedReason(dto.getBlockedReason())
                .suspiciousReason(dto.getSuspiciousReason())
                .build();
    }

    /**
     * метод преобразования модели трансферов с телефона в Dto
     * @param phoneTransfer model подозрительных трансферов с телефона
     * @return PhoneTransferDto
     */
    public PhoneTransferDto makePhoneTransferToDto(SuspiciousPhoneTransfer phoneTransfer) {
        return PhoneTransferDto.builder()
                .phoneTransferId(phoneTransfer.getPhoneTransferId())
                .isBlocked(phoneTransfer.getIsBlocked())
                .isSuspicious(phoneTransfer.getIsSuspicious())
                .blockedReason(phoneTransfer.getBlockedReason())
                .suspiciousReason(phoneTransfer.getSuspiciousReason())
                .build();
    }

    /**
     * метод преобразования Dto трансферов с телефона в модель
     * @param dto Dto подозрительных трансферов с телефона
     * @return SuspiciousCardTransfer
     */
    public SuspiciousPhoneTransfer makeDtoToPhoneTransfer(PhoneTransferDto dto) {
        return SuspiciousPhoneTransfer.builder()
                .phoneTransferId(dto.getPhoneTransferId())
                .isBlocked(dto.getIsBlocked())
                .isSuspicious(dto.getIsSuspicious())
                .blockedReason(dto.getBlockedReason())
                .suspiciousReason(dto.getSuspiciousReason())
                .build();
    }

}
