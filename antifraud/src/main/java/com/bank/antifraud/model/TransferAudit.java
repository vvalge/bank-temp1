package com.bank.antifraud.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.ZonedDateTime;


/**
 * Класс модели для аудирования
 */
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
@ToString
@Table(schema = "anti_fraud", name = "audit")
public class TransferAudit {
    /**
     * идентификатор
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long auditId;

    /**
     * тип сущности
     */
    @Column(name = "entity_type", nullable = false)
    private String entityType;

    /**
     * тип операции
     */
    @Column(name = "operation_type", nullable = false)
    private String operationType;

    /**
     * кто создал
     */
    @Column(name = "created_by", nullable = false)
    private String createdBy;

    /**
     * кто изменил
     */
    @Column(name = "modified_by")
    private String modifiedBy;

    /**
     * когда создан
     */
    @Column(name = "created_at", nullable = false)
    private ZonedDateTime createdAt;

    /**
     * когда обновлен
     */
    @Column(name = "modified_at")
    private ZonedDateTime modifiedAt;

    /**
     * json, заполняется при изменении
     */
    @Column(name = "new_entity_json", columnDefinition = "TEXT")
    private String newEntityJson;

    /**
     * json, заполняется при изменении и при сохранении
     */
    @Column(name = "entity_json", nullable = false, columnDefinition = "TEXT")
    private String entityJson;
}

