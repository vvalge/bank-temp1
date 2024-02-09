package com.bank.profile.dto;

import com.bank.profile.model.Audit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * Класс, представляющий Data Transfer Object (DTO) для {@link Audit}.
 * Используется для передачи данных между слоями приложения.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuditDto {

    private String entityType;

    private String operationType;

    private String createdBy;

    private String modifiedBy;

    private Timestamp createdAt;

    private Timestamp modifiedAt;

    private String newEntityJson;

    private String entityJson;

}
