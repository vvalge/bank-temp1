package com.bank.authorization.service;

import com.bank.authorization.repository.entity.AuditEntity;
import com.bank.authorization.repository.entity.AuditRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.java.Log;
import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
@Log
public class AuditService {
    @Autowired
    private AuditRepository auditRepository;
    private ObjectMapper objectMapper = new ObjectMapper();

    public boolean saveAuditEntity(JoinPoint joinPoint, UserDetails userDetails, UserDetails currentUser) {
        try {
            AuditEntity auditEntity = getAuditEntity(userDetails, currentUser, joinPoint);
            auditRepository.save(auditEntity);
            log.info("Сохранение AuditEntity успешно");
            return true;
        } catch (JsonProcessingException e) {
            log.info("Сохранение AuditEntity неуспешно");
            e.getMessage();
            return false;
        }

    }


    private AuditEntity getAuditEntity(UserDetails userDetails, UserDetails currentUser, JoinPoint joinPoint)
            throws JsonProcessingException {
        ZonedDateTime now = ZonedDateTime.now();
        AuditEntity auditEntity = new AuditEntity();

        auditEntity.setEntity_type(userDetails.getClass().getSimpleName());
        auditEntity.setOperation_type(joinPoint.getSignature().getName());
        auditEntity.setCreated_by(currentUser.getUsername());
        auditEntity.setCreated_at(now);
        auditEntity.setModified_at(now);
        auditEntity.setModified_by(currentUser.getUsername());
        auditEntity.setNew_entity_json(objectMapper.writeValueAsString(userDetails));
        auditEntity.setEntity_json(objectMapper.writeValueAsString(userDetails));

        log.info("Создана новая AuditEntity");
        return auditEntity;

    }

}
