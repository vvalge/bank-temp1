package com.bank.transfer.aspects;

import com.bank.transfer.model.AccountEntity;
import com.bank.transfer.model.AuditEntity;
import com.bank.transfer.model.CardEntity;
import com.bank.transfer.model.PhoneEntity;
import com.bank.transfer.service.TransferService;
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

@Slf4j
@Component
@Aspect
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Order(10)
public class LogAuditAspect {

    TransferService transferService;

    @Around(value = "execution(public void transferByAccountNumber(com.bank.transfer.model.AccountEntity)) && args(entity)")
    public Object accountServiceLoggingAspect(ProceedingJoinPoint proceedingJoinPoint, AccountEntity entity) throws Throwable {

        log.info("AROUND Before - The beginning of the invoked {} method execution", proceedingJoinPoint.getSignature().getName());
        try {
            Object result = proceedingJoinPoint.proceed();
            log.info("AROUND After Returning - invoke {} method", proceedingJoinPoint.getSignature().getName());
            if (entity.getId() != null) {
                log.info("AROUND After Returning - Type of transfer: Updating the transfer data, method:{}",
                        proceedingJoinPoint.getSignature().getName());
            } else {
                log.info("AROUND After Returning - Type of transfer: New transfer");
            }
            AuditEntity audit = new AuditEntity();
            audit.setEntityType(entity.getClass().getSimpleName());
            audit.setOperationType(proceedingJoinPoint.getSignature().getName());
            audit.setCreatedBy(entity.getAccountDetailsId().toString());
            audit.setCreatedAt(ZonedDateTime.now());
            audit.setEntityJson(entity.toString());
            transferService.saveAudit(audit);
            log.info("AROUND After Returning - The transfer by account number was completed successfully");
            return result;
        } catch (Throwable e) {
            log.error("AROUND After Thtowing - " + e);
            return new Throwable(e);
        }
    }

    @Around(value = "execution(public void transferByCardNumber(com.bank.transfer.model.CardEntity)) && args(entity)")
    public Object accountServiceLoggingAspect(ProceedingJoinPoint proceedingJoinPoint, CardEntity entity) throws Throwable {

        log.info("AROUND Before - The beginning of the invoked {} method execution", proceedingJoinPoint.getSignature().getName());
        try {
            Object result = proceedingJoinPoint.proceed();
            log.info("AROUND After Returning - invoke {} method", proceedingJoinPoint.getSignature().getName());
            if (entity.getId() != null) {
                log.info("AROUND After Returning - Type of transfer: Updating the transfer data, method:{}",
                        proceedingJoinPoint.getSignature().getName());
            } else {
                log.info("AROUND After Returning - Type of transfer: New transfer");
            }
            AuditEntity audit = new AuditEntity();
            audit.setEntityType(entity.getClass().getSimpleName());
            audit.setOperationType(proceedingJoinPoint.getSignature().getName());
            audit.setCreatedBy(entity.getAccountDetailsId().toString());
            audit.setCreatedAt(ZonedDateTime.now());
            audit.setEntityJson(entity.toString());
            transferService.saveAudit(audit);
            log.info("AROUND After Returning - The transfer by card number was completed successfully");
            return result;
        } catch (Throwable e) {
            log.error("AROUND After Thtowing - " + e);
            return new Throwable(e);
        }
    }

    @Around(value = "execution(public void transferByPhoneNumber(com.bank.transfer.model.PhoneEntity)) && args(entity)")
    public Object accountServiceLoggingAspect(ProceedingJoinPoint proceedingJoinPoint, PhoneEntity entity) throws Throwable {

        log.info("AROUND Before - The beginning of the invoked {} method execution",
                proceedingJoinPoint.getSignature().getName());
        try {
            Object result = proceedingJoinPoint.proceed();
            log.info("AROUND After Returning - invoke {} method", proceedingJoinPoint.getSignature().getName());
            log.info("AROUND After Returning - Type of transfer: New transfer");
            AuditEntity audit = new AuditEntity();
            audit.setEntityType(entity.getClass().getSimpleName());
            audit.setOperationType(proceedingJoinPoint.getSignature().getName());
            audit.setCreatedBy(entity.getAccountDetailsId().toString());
            audit.setCreatedAt(ZonedDateTime.now());//calendar.getTime()
            audit.setEntityJson(entity.toString());
            transferService.saveAudit(audit);
            log.info("AROUND After Returning - The transfer by phone number was completed successfully");
            return result;
        } catch (Throwable e) {
            log.error("AROUND After Thtowing - " + e);
            return new Throwable(e);
        }
    }
}
