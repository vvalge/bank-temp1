package com.bank.transfer.controller;

import com.bank.transfer.dto.AccountDto;
import com.bank.transfer.dto.CardDto;
import com.bank.transfer.dto.PhoneDto;
import com.bank.transfer.mapper.AccountDtoFactory;
import com.bank.transfer.mapper.CardDtoFactory;
import com.bank.transfer.mapper.PhoneDtoFactory;
import com.bank.transfer.model.AccountEntity;
import com.bank.transfer.model.CardEntity;
import com.bank.transfer.model.PhoneEntity;
import com.bank.transfer.service.TransferService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Slf4j
@RestController
@OpenAPIDefinition(info = @Info(
        title = "TransferController",
        version = "1.0.0",
        description = "контроллер для работы с операциями перевода"))
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/")
public class TransferController {

    TransferService transferService;
    AccountDtoFactory accountDtoFactory;
    CardDtoFactory cardDtoFactory;
    PhoneDtoFactory phoneDtoFactory;

    //-------- ПЕРЕВОД ----------- //
    // перевод по номеру счета
    @PostMapping("/account/{id}")
    @Operation(summary = "Перевод по номеру счета", description = "Выполненяем перевод по номеру счета")
    public ResponseEntity<HttpStatus> transferByAccountNumber(@RequestBody AccountDto accountDto, @PathVariable @Valid Long id) {

        AccountEntity accountEntity = accountDtoFactory.makeAccountDtoToEntity(accountDto);
        if (accountEntity.getNumber() == null || accountEntity.getAmount() == null) {
            log.error("TransferController.transferByAccountNumber: Notnull field by AccountEntity is null");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            accountEntity.setAccountDetailsId(id);
            transferService.transferByAccountNumber(accountEntity);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    // перевод по номеру карты
    @PostMapping("/card/{id}")
    @Operation(summary = "Перевод по номеру карты", description = "Выполненяем перевод по номеру карты")
    public ResponseEntity<HttpStatus> transferByCardNumber(@RequestBody CardDto cardDto, @PathVariable @Valid Long id) {

        CardEntity cardEntity = cardDtoFactory.makeCardDtoToEntity(cardDto);
        if (cardEntity.getNumber() == null || cardEntity.getAmount() == null) {
            log.error("TransferController.transferByCardNumber: Notnull field by CardEntity is null");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            cardEntity.setAccountDetailsId(id);
            transferService.transferByCardNumber(cardEntity);
            return new ResponseEntity<> (HttpStatus.OK);
        }

    }

    // перевод по номеру телефона
    @PostMapping("/phone/{id}")
    @Operation(summary = "Перевод по номеру телефона", description = "Выполненяем перевод по номеру телефона")
    public ResponseEntity<HttpStatus> transferByPhoneNumber(@RequestBody PhoneDto phoneDto, @PathVariable @Valid Long id) {

        PhoneEntity phoneEntity = phoneDtoFactory.makePhoneDtoToEntity(phoneDto);
        if (phoneEntity.getNumber() == null || phoneEntity.getAmount() == null) {
            log.error("TransferController.transferByPhoneNumber: Notnull field by PhoneEntity is null");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            phoneEntity.setAccountDetailsId(id);
            transferService.transferByPhoneNumber(phoneEntity);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
