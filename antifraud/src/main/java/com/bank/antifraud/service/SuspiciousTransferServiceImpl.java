package com.bank.antifraud.service;

import com.bank.antifraud.dto.AccountTransferDto;
import com.bank.antifraud.dto.CardTransferDto;
import com.bank.antifraud.dto.PhoneTransferDto;
import com.bank.antifraud.mapper.SuspiciousTransferDtoFactory;
import com.bank.antifraud.model.SuspiciousAccountTransfer;
import com.bank.antifraud.model.SuspiciousCardTransfer;
import com.bank.antifraud.model.SuspiciousPhoneTransfer;
import com.bank.antifraud.model.TransferAudit;
import com.bank.antifraud.repository.AccountTransferRepository;
import com.bank.antifraud.repository.AuditRepository;
import com.bank.antifraud.repository.CardTransferRepository;
import com.bank.antifraud.repository.PhoneTransferRepository;
import com.bank.antifraud.util.TransferNotFound;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.Optional;

@Service
@Slf4j
public class SuspiciousTransferServiceImpl implements SuspiciousTransferService {

    /**
     * Репозиторий трансфера по аккаунту
     *
     * @see AccountTransferRepository
     */
    private final AccountTransferRepository accountTransferRepository;

    /**
     * Репозиторий трансфера по карте
     *
     * @see CardTransferRepository
     */
    private final CardTransferRepository cardTransferRepository;

    /**
     * Репозиторий трансфера с телефона
     * @see PhoneTransferRepository
     */
    private final PhoneTransferRepository phoneTransferRepository;

    /**
     * Репозиторий аудита
     *
     * @see AuditRepository
     */
    private final AuditRepository auditRepository;

    /**
     * Фабрика преобразования DTO в модель трансфера и обратно
     *
     * @see SuspiciousTransferDtoFactory
     */
    private final SuspiciousTransferDtoFactory suspiciousTransferDtoFactory;

    /**
     * Конструктор, принимающий репозитории для работы с данными аккаунтов и аудита
     */
    @Autowired
    public SuspiciousTransferServiceImpl(AccountTransferRepository accountRepository, CardTransferRepository cardTransferRepository, PhoneTransferRepository phoneTransferRepository, AuditRepository auditRepository, SuspiciousTransferDtoFactory suspiciousTransferDtoFactory) {
        this.accountTransferRepository = accountRepository;
        this.cardTransferRepository = cardTransferRepository;
        this.phoneTransferRepository = phoneTransferRepository;
        this.auditRepository = auditRepository;
        this.suspiciousTransferDtoFactory = suspiciousTransferDtoFactory;
    }

    /**
     * Метод получения трансфера из аккаунта по id трансфера
     *
     * @param accountTransferId id трансфера из аккаунта
     */
    @Override
    @Transactional
    public AccountTransferDto getAccountTransferById(Long accountTransferId) throws TransferNotFound {
        try {
            log.info("Trying to retrieve suspicious account transfer data with ID {}", accountTransferId);

            final Optional<SuspiciousAccountTransfer>  optionalAccountTransfer = accountTransferRepository.findByAccountTransferId(accountTransferId);

            if (optionalAccountTransfer.isPresent()) {
                log.info("account transfer with ID {} found", accountTransferId);
                return suspiciousTransferDtoFactory.makeAccountTransferToDto(optionalAccountTransfer.get());
            } else {
                log.error("Account Transfer with ID {} not found", accountTransferId);
                throw new TransferNotFound("Unable to find account transfer with id: " + accountTransferId);
            }
        } catch (TransferNotFound e) {
            throw e;
        } catch (Exception e) {
            log.error("Unexpected error while getting transfer with ID: {}", accountTransferId, e);
            throw new RuntimeException("Unexpected error", e);
        }
    }

    /**
     * Метод получения трансфера c карты по id трансфера
     *
     * @param cardTransferId id трансфера c карты
     */
    @Override
    @Transactional
    public CardTransferDto getCardTransferById(Long cardTransferId) throws TransferNotFound {
        try {
            log.info("Trying to retrieve suspicious account transfer data with ID {}", cardTransferId);

            final Optional<SuspiciousCardTransfer>  optionalCardTransfer = cardTransferRepository.findByCardTransferId(cardTransferId);

            if (optionalCardTransfer.isPresent()) {
                log.info("account transfer with ID {} found", cardTransferId);
                return suspiciousTransferDtoFactory.makeCardTransferToDto(optionalCardTransfer.get());
            } else {
                log.error("Account Transfer with ID {} not found", cardTransferId);
                throw new TransferNotFound("Unable to find account transfer with id: " + cardTransferId);
            }
        } catch (TransferNotFound e) {
            throw e;
        } catch (Exception e) {
            log.error("Unexpected error while getting transfer with ID: {}", cardTransferId, e);
            throw new RuntimeException("Unexpected error", e);
        }
    }

    /**
     * Метод получения трансфера c телефона по id трансфера
     *
     * @param phoneTransferId id трансфера c карты
     */
    @Override
    @Transactional
    public PhoneTransferDto getPhoneTransferById(Long phoneTransferId) throws TransferNotFound {
        try {
            log.info("Trying to retrieve suspicious Phone transfer data with ID {}", phoneTransferId);

            final Optional<SuspiciousPhoneTransfer>  optionalPhoneTransfer = phoneTransferRepository.findByPhoneTransferId(phoneTransferId);

            if (optionalPhoneTransfer.isPresent()) {
                log.info("phone transfer with ID {} found", phoneTransferId);
                return suspiciousTransferDtoFactory.makePhoneTransferToDto(optionalPhoneTransfer.get());
            } else {
                log.error("Phone Transfer with ID {} not found", phoneTransferId);
                throw new TransferNotFound("Unable to find Phone transfer with id: " + phoneTransferId);
            }
        } catch (TransferNotFound e) {
            throw e;
        } catch (Exception e) {
            log.error("Unexpected error while getting Phone transfer with ID: {}", phoneTransferId, e);
            throw new RuntimeException("Unexpected error", e);
        }
    }

    /**
     * Метод создания записи трансфера из аккаунта
     *
     * @param dto экземпляр DTO трансфера из аккаунта
     */
    @Override
    public Long createAccountTransfer(AccountTransferDto dto) {
        log.info("Creating a new account transfer record");

        final SuspiciousAccountTransfer accountTransfer = new SuspiciousAccountTransfer();
        accountTransfer.setAccountTransferId(dto.getAccountTransferId());
        accountTransfer.setIsBlocked(dto.getIsBlocked());
        accountTransfer.setIsSuspicious(dto.getIsSuspicious());
        accountTransfer.setBlockedReason(dto.getBlockedReason());
        accountTransfer.setSuspiciousReason(dto.getSuspiciousReason());

        final SuspiciousAccountTransfer createdAccountTransfer = accountTransferRepository.save(accountTransfer);
        final Long accountTransferId = createdAccountTransfer.getId();
        accountTransfer.setId(accountTransferId);

        log.info("Account transfer created with ID: {}", accountTransferId);
        return accountTransferId;
    }

    /**
     * Метод создания записи трансфера с карты
     *
     * @param dto экземпляр DTO трансфера с карты
     */
    @Override
    public Long createCardTransfer(CardTransferDto dto) {
        log.info("Creating a new Card transfer record");

        final SuspiciousCardTransfer cardTransfer = new SuspiciousCardTransfer();
        cardTransfer.setCardTransferId(dto.getCardTransferId());
        cardTransfer.setIsBlocked(dto.getIsBlocked());
        cardTransfer.setIsSuspicious(dto.getIsSuspicious());
        cardTransfer.setBlockedReason(dto.getBlockedReason());
        cardTransfer.setSuspiciousReason(dto.getSuspiciousReason());

        final SuspiciousCardTransfer createdCardTransfer = cardTransferRepository.save(cardTransfer);
        final Long cardTransferId = createdCardTransfer.getId();
        cardTransfer.setId(cardTransferId);

        log.info("Card transfer created with ID: {}", cardTransferId);
        return cardTransferId;
    }

    /**
     * Метод создания записи трансфера с телефона
     *
     * @param dto экземпляр DTO трансфера с телефона
     */
    @Override
    public Long createPhoneTransfer(PhoneTransferDto dto) {
        log.info("Creating a new Phone transfer record");

        final SuspiciousPhoneTransfer phoneTransfer = new SuspiciousPhoneTransfer();
        phoneTransfer.setPhoneTransferId(dto.getPhoneTransferId());
        phoneTransfer.setIsBlocked(dto.getIsBlocked());
        phoneTransfer.setIsSuspicious(dto.getIsSuspicious());
        phoneTransfer.setBlockedReason(dto.getBlockedReason());
        phoneTransfer.setSuspiciousReason(dto.getSuspiciousReason());

        final SuspiciousPhoneTransfer createdPhoneTransfer = phoneTransferRepository.save(phoneTransfer);
        final Long phoneTransferId = createdPhoneTransfer.getId();
        phoneTransfer.setId(phoneTransferId);

        log.info("Phone transfer created with ID: {}", phoneTransferId);
        return phoneTransferId;
    }

    /**
     * Метод обновления записи трансфера из аккаунта
     *
     * @param dto экземпляр DTO трансфера из аккаунта
     */
    @Override
    public AccountTransferDto updateAccountTransfer(AccountTransferDto dto) {
        log.info("Updating account transfer with ID: {}", dto.getAccountTransferId());

        final Long accountTransferId = dto.getAccountTransferId();

        try {
            if (accountTransferId == null) {
                log.error("Invalid argument: Account Transfer ID cannot be null.");
                throw new IllegalArgumentException("Account Transfer ID cannot be null.");
            }

            final Optional<SuspiciousAccountTransfer> optionalUpdatingAccountTransfer = accountTransferRepository.findByAccountTransferId(accountTransferId);

            if (optionalUpdatingAccountTransfer.isPresent()) {
                final SuspiciousAccountTransfer updatingAccountTransfer = optionalUpdatingAccountTransfer.get();
                updatingAccountTransfer.setAccountTransferId(dto.getAccountTransferId());
                updatingAccountTransfer.setIsBlocked(dto.getIsBlocked());
                updatingAccountTransfer.setIsSuspicious(dto.getIsSuspicious());
                updatingAccountTransfer.setBlockedReason(dto.getBlockedReason());
                updatingAccountTransfer.setSuspiciousReason(dto.getSuspiciousReason());

                log.info("Account transfer with ID {} updated successfully", accountTransferId);
                return suspiciousTransferDtoFactory.makeAccountTransferToDto(accountTransferRepository.save(updatingAccountTransfer));
            } else {
                log.error("Account Transfer with ID {} not found", accountTransferId);
                throw new TransferNotFound("Account transfer not found");
            }
        } catch (TransferNotFound e) {
            log.error("Error while updating account with ID: {}", accountTransferId, e);
            throw e;
        } catch (Exception e) {
            log.error("Unexpected error while updating account with ID: {}", accountTransferId, e);
            throw new RuntimeException("UNEXPECTED ERROR", e);
        }
    }

    /**
     * Метод обновления записи трансфера с карты
     *
     * @param dto экземпляр DTO трансфера с карты
     */
    @Override
    public CardTransferDto updateCardTransfer(CardTransferDto dto) {
        log.info("Updating Card transfer with ID: {}", dto.getCardTransferId());

        final Long cardTransferId = dto.getCardTransferId();

        try {
            if (cardTransferId == null) {
                log.error("Invalid argument: Card Transfer ID cannot be null.");
                throw new IllegalArgumentException("card Transfer ID cannot be null.");
            }

            final Optional<SuspiciousCardTransfer> optionalCardTransfer = cardTransferRepository.findByCardTransferId(cardTransferId);

            if (optionalCardTransfer.isPresent()) {
                final SuspiciousCardTransfer cardTransfer = optionalCardTransfer.get();
                cardTransfer.setCardTransferId(dto.getCardTransferId());
                cardTransfer.setIsBlocked(dto.getIsBlocked());
                cardTransfer.setIsSuspicious(dto.getIsSuspicious());
                cardTransfer.setBlockedReason(dto.getBlockedReason());
                cardTransfer.setSuspiciousReason(dto.getSuspiciousReason());

                log.info("Card transfer with ID {} updated successfully", cardTransferId);
                return suspiciousTransferDtoFactory.makeCardTransferToDto(cardTransferRepository.save(cardTransfer));
            } else {
                log.error("Card Transfer with ID {} not found", cardTransferId);
                throw new TransferNotFound("Card transfer not found");
            }
        } catch (TransferNotFound e) {
            log.error("Error while updating account with ID: {}", cardTransferId, e);
            throw e;
        } catch (Exception e) {
            log.error("Unexpected error while updating account with ID: {}", cardTransferId, e);
            throw new RuntimeException("UNEXPECTED ERROR", e);
        }
    }

    /**
     * Метод обновления записи трансфера с телефона
     *
     * @param dto экземпляр DTO трансфера с телефона
     */
    @Override
    public PhoneTransferDto updatePhoneTransfer(PhoneTransferDto dto) {
        log.info("Updating Phone transfer with ID: {}", dto.getPhoneTransferId());

        final Long phoneTransferId = dto.getPhoneTransferId();

        try {
            if (phoneTransferId == null) {
                log.error("Invalid argument: Phone Transfer ID cannot be null.");
                throw new IllegalArgumentException("Phone Transfer ID cannot be null.");
            }

            final Optional<SuspiciousPhoneTransfer> optionalPhoneTransfer = phoneTransferRepository.findByPhoneTransferId(phoneTransferId);

            if (optionalPhoneTransfer.isPresent()) {
                final SuspiciousPhoneTransfer phoneTransfer = optionalPhoneTransfer.get();
                phoneTransfer.setPhoneTransferId(dto.getPhoneTransferId());
                phoneTransfer.setIsBlocked(dto.getIsBlocked());
                phoneTransfer.setIsSuspicious(dto.getIsSuspicious());
                phoneTransfer.setBlockedReason(dto.getBlockedReason());
                phoneTransfer.setSuspiciousReason(dto.getSuspiciousReason());

                log.info("Phone transfer with ID {} updated successfully", phoneTransferId);
                return suspiciousTransferDtoFactory.makePhoneTransferToDto(phoneTransferRepository.save(phoneTransfer));
            } else {
                log.error("Phone Transfer with ID {} not found", phoneTransferId);
                throw new TransferNotFound("Phone transfer not found");
            }
        } catch (TransferNotFound e) {
            log.error("Error while updating phone transfer with ID: {}", phoneTransferId, e);
            throw e;
        } catch (Exception e) {
            log.error("Unexpected error while updating phone transfer with ID: {}", phoneTransferId, e);
            throw new RuntimeException("UNEXPECTED ERROR", e);
        }
    }


    /**
     * Метод добавления записи об аудите в базу
     *
     * @param audit модель аудита
     */
    @Transactional
    public void saveAudit(TransferAudit audit) {
        log.info("Attempting to save audit: {}", audit);

        audit.setModifiedAt(ZonedDateTime.now());

        if (audit.getAuditId() != null) {
            final Optional<TransferAudit> existingAuditOptional = auditRepository.findById(audit.getAuditId());

            existingAuditOptional.ifPresent(existingAudit -> {
                existingAudit.setModifiedBy(audit.getModifiedBy());
                existingAudit.setNewEntityJson(audit.getNewEntityJson());
                auditRepository.save(existingAudit);
                log.info("Audit updated successfully");
            });
        } else {
            auditRepository.save(audit);
            log.info("Audit saved successfully");
        }
    }
}