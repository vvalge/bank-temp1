package com.bank.antifraud.controller;

import com.bank.antifraud.dto.AccountTransferDto;
import com.bank.antifraud.dto.CardTransferDto;
import com.bank.antifraud.dto.PhoneTransferDto;
import com.bank.antifraud.service.SuspiciousTransferServiceImpl;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@OpenAPIDefinition(info = @Info(
        title = "AntifraudController",
        version = "1.0.0",
        description = "контроллер для работы с антифрод сервисом"))
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@RequestMapping("/")

public class AntifraudController {

    /**
     * @see SuspiciousTransferServiceImpl
     */
    private final SuspiciousTransferServiceImpl suspiciousTransferService;

    @GetMapping("/at/{id}")
    public ResponseEntity<AccountTransferDto> findAccountTransferById(@PathVariable("id") Long id) {
        try {
            final AccountTransferDto gottenAccountTransfer = suspiciousTransferService.getAccountTransferById(id);
            if (gottenAccountTransfer != null) {
                log.info("Account Transfer record received");
            } else {
                log.error("There is no any ac records with mentioned ID {}", id);
            }
            return new ResponseEntity<>(gottenAccountTransfer, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            log.error("AccountController.getAccount: Error while processing request", e);
            return null;
        }
    }


    @PutMapping("/at/{id}")
    public ResponseEntity<AccountTransferDto> updateAccountTransfer(@RequestBody AccountTransferDto accountTransfer, @PathVariable String id) {
        try {
            final AccountTransferDto updatedAccountTransfer = suspiciousTransferService.updateAccountTransfer(accountTransfer);
            if (updatedAccountTransfer != null) {
                log.info("Account Transfer record with ID {} updated", id);
            } else {
                log.error("There is no any ac records with mentioned ID {}", id);
            }
            return new ResponseEntity<>(updatedAccountTransfer, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            log.error("An unexpected error occurred while account transfer record updating.", e);
            return null;
        }
    }


    @PostMapping("/at")
    public ResponseEntity<AccountTransferDto> createAccountTransfer(@RequestBody AccountTransferDto accountTransfer) {
        log.error("Trying to create new transfer record");
        try {
            suspiciousTransferService.createAccountTransfer(accountTransfer);
            log.error("Account transfer record created");
            return new ResponseEntity<>(accountTransfer, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("An unexpected error occurred while account transfer record creating.", e);
            return null;
        }
    }


    @GetMapping("/ct/{id}")
    public ResponseEntity<CardTransferDto> findCardTransferById(@PathVariable("id") Long id) {
        try {
            final CardTransferDto gottenCardTransfer = suspiciousTransferService.getCardTransferById(id);
            if (gottenCardTransfer != null) {
                log.info("Card Transfer record received");
            } else {
                log.error("There is no any ct records with mentioned ID {}", id);
            }
            return new ResponseEntity<>(gottenCardTransfer, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            log.error("AccountController.getAccount: Error while processing request", e);
            return null;
        }
    }


    @PutMapping("/ct/{id}")
    public ResponseEntity<CardTransferDto> updateCardTransfer(@RequestBody CardTransferDto cardTransfer, @PathVariable String id) {
        try {
            final CardTransferDto updatedCardTransfer = suspiciousTransferService.updateCardTransfer(cardTransfer);
            if (updatedCardTransfer != null) {
                log.info("Card Transfer record with ID {} updated", id);
            } else {
                log.error("There is no any ac records with mentioned ID {}", id);
            }
            return new ResponseEntity<>(updatedCardTransfer, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            log.error("An unexpected error occurred while card transfer record updating.", e);
            return null;
        }
    }


    @PostMapping("/ct")
    public ResponseEntity<CardTransferDto> createCardTransfer(@RequestBody CardTransferDto cardTransfer) {
        log.error("Trying to create new card transfer record");
        try {
            suspiciousTransferService.createCardTransfer(cardTransfer);
            log.error("Card transfer record created");
            return new ResponseEntity<>(cardTransfer, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("An unexpected error occurred while card transfer record creating.", e);
            return null;
        }
    }

    @GetMapping("/pt/{id}")
    public ResponseEntity<PhoneTransferDto> findPhoneTransferById(@PathVariable("id") Long id) {
        try {
            final PhoneTransferDto gottenPhoneTransfer = suspiciousTransferService.getPhoneTransferById(id);
            if (gottenPhoneTransfer != null) {
                log.info("Phone Transfer record received");
            } else {
                log.error("There is no any pt records with mentioned ID {}", id);
            }
            return new ResponseEntity<>(gottenPhoneTransfer, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            log.error("Error while processing request", e);
            return null;
        }
    }


    @PutMapping("/pt/{id}")
    public ResponseEntity<PhoneTransferDto> updatePhoneTransfer(@RequestBody PhoneTransferDto phoneTransfer, @PathVariable String id) {
        try {
            final PhoneTransferDto updatedPhoneTransfer = suspiciousTransferService.updatePhoneTransfer(phoneTransfer);
            if (updatedPhoneTransfer != null) {
                log.info("Phone Transfer record with ID {} updated", id);
            } else {
                log.error("There is no any pt records with mentioned ID {}", id);
            }
            return new ResponseEntity<>(updatedPhoneTransfer, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            log.error("An unexpected error occurred while phone transfer record updating.", e);
            return null;
        }
    }


    @PostMapping("/pt")
    public ResponseEntity<PhoneTransferDto> createPhoneTransfer(@RequestBody PhoneTransferDto phoneTransfer) {
        log.error("Trying to create new phone transfer record");
        try {
            suspiciousTransferService.createPhoneTransfer(phoneTransfer);
            log.error("Phone transfer record created");
            return new ResponseEntity<>(phoneTransfer, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("An unexpected error occurred while phone transfer record creating.", e);
            return null;
        }
    }
}
