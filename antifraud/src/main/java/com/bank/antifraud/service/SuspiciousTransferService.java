package com.bank.antifraud.service;

import com.bank.antifraud.dto.AccountTransferDto;
import com.bank.antifraud.dto.CardTransferDto;
import com.bank.antifraud.dto.PhoneTransferDto;
import com.bank.antifraud.model.TransferAudit;



public interface SuspiciousTransferService {

    AccountTransferDto getAccountTransferById(Long id);

    Long createAccountTransfer(AccountTransferDto createAccountTransferDto);

    AccountTransferDto updateAccountTransfer(AccountTransferDto updateAccountTransferDto);

    CardTransferDto getCardTransferById(Long id);

    Long createCardTransfer(CardTransferDto createCardTransferDto);

    CardTransferDto updateCardTransfer(CardTransferDto updateCardTransferDto);

   PhoneTransferDto getPhoneTransferById(Long id);

    Long createPhoneTransfer(PhoneTransferDto createPhoneTransferDto);

    PhoneTransferDto updatePhoneTransfer(PhoneTransferDto updatePhoneTransferDto);

    void saveAudit(TransferAudit transferAudit);

}
