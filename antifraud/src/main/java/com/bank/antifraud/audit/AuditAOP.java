package com.bank.antifraud.audit;

import com.bank.antifraud.dto.AccountTransferDto;
import com.bank.antifraud.model.TransferAudit;
import com.bank.antifraud.service.SuspiciousTransferService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

/**
 * Класс описывающий аудирование
 */
@Slf4j
@Component
@Aspect
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Order(10)
public class AuditAOP {

    /**
     * @see SuspiciousTransferService
     */
    SuspiciousTransferService suspiciousTransferService;

    /**
     * Метод аудирования при выполнении перевода по номеру счета
     *
     * @param proceedingJoinPoint точки наблюдения, присоединения к коду, где планируется введение функциональности
     * @param accountTransfer     SuspiciousAccountTransfer
     * @return результирующий объект обрабатываемого метода
     * @throws Throwable возможны ошибки и исключения
     */
    @Around(value = "execution(public Long createAccountTransfer(com.bank.antifraud.dto.AccountTransferDto)) && args(accountTransfer))")
    public Object createATAudit(ProceedingJoinPoint proceedingJoinPoint, AccountTransferDto accountTransfer) throws Throwable {

        log.info("AROUND Before - The beginning of the invoked {} method execution", proceedingJoinPoint.getSignature().getName());
        try {
            Object result = proceedingJoinPoint.proceed();
            log.info("AROUND After Returning - invoke {} method", proceedingJoinPoint.getSignature().getName());
            if (accountTransfer.getAccountTransferId() != null) {
                log.info("AROUND After Returning - Type of transfer: Updating the transfer data, method:{}", proceedingJoinPoint.getSignature().getName());
            } else {
                log.info("AROUND After Returning - Type of transfer: New transfer");
            }
            TransferAudit audit = new TransferAudit();
            audit.setEntityType(accountTransfer.getClass().getSimpleName());
            audit.setOperationType(proceedingJoinPoint.getSignature().getName());
            audit.setCreatedBy("System");
            audit.setCreatedAt(ZonedDateTime.now());
            //audit.setModifiedAt(ZonedDateTime.now());
            audit.setEntityJson(accountTransfer.builder().accountTransferId(accountTransfer.getAccountTransferId()).isBlocked(accountTransfer.getIsBlocked()).isSuspicious(accountTransfer.getIsSuspicious()).blockedReason(accountTransfer.getBlockedReason()).suspiciousReason(accountTransfer.getSuspiciousReason()).build().toString());
            suspiciousTransferService.saveAudit(audit);
            log.info("AROUND After Returning - The transfer by account number was completed successfully");
            return result;
        } catch (Throwable e) {
            log.error("AROUND After Throwing - " + e);
            return new Throwable(e);
        }
    }

    @Around(value = "execution(public com.bank.antifraud.dto.AccountTransferDto updateAccountTransfer(com.bank.antifraud.dto.AccountTransferDto)) && args(accountTransfer))")
    public Object updateATAudit(ProceedingJoinPoint proceedingJoinPoint, AccountTransferDto accountTransfer) throws Throwable {

        log.info("AROUND Before - The beginning of the invoked {} method execution", proceedingJoinPoint.getSignature().getName());
        try {
            Object result = proceedingJoinPoint.proceed();
            log.info("AROUND After Returning - invoke {} method", proceedingJoinPoint.getSignature().getName());
            if (accountTransfer.getAccountTransferId() != null) {
                log.info("AROUND After Returning - Type of transfer: Updating the transfer data, method:{}", proceedingJoinPoint.getSignature().getName());
            } else {
                log.info("AROUND After Returning - Type of transfer: New transfer");
            }
            TransferAudit audit = new TransferAudit();
            audit.setEntityType(accountTransfer.getClass().getSimpleName());
            audit.setOperationType(proceedingJoinPoint.getSignature().getName());
            audit.setCreatedBy("System");
            audit.setModifiedBy("System");
            audit.setCreatedAt(ZonedDateTime.now());
            audit.setModifiedAt(ZonedDateTime.now());
            audit.setNewEntityJson(accountTransfer.builder().accountTransferId(accountTransfer.getAccountTransferId()).isBlocked(accountTransfer.getIsBlocked()).isSuspicious(accountTransfer.getIsSuspicious()).blockedReason(accountTransfer.getBlockedReason()).suspiciousReason(accountTransfer.getSuspiciousReason()).build().toString());
            audit.setNewEntityJson(accountTransfer.builder().accountTransferId(accountTransfer.getAccountTransferId()).isBlocked(accountTransfer.getIsBlocked()).isSuspicious(accountTransfer.getIsSuspicious()).blockedReason(accountTransfer.getBlockedReason()).suspiciousReason(accountTransfer.getSuspiciousReason()).build().toString());
            suspiciousTransferService.saveAudit(audit);
            log.info("AROUND After Returning - The transfer by account number was completed successfully");
            return result;
        } catch (Throwable e) {
            log.error("AROUND After Throwing - " + e);
            return new Throwable(e);
        }
    }

}


