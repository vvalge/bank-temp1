package com.bank.transfer.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;
import java.time.ZonedDateTime;

/**
 * Класс модели для аудирования
 */
@Builder
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "audit", schema = "transfer")
public class AuditEntity {

    /**
     * технический идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    /**
     * тип сущности
     */
    @Column(name = "entity_type", length = 40, nullable = false)
    String entityType;

    /**
     * тип операции
     */
    @Column(name = "operation_type", nullable = false)
    String operationType;

    /**
     * кто создал
     */
    @Column(name = "created_by",nullable = false)
    String createdBy;

    /**
     * кто изменил
     */
    @Column(name = "modified_by")
    String modifiedBy;

    /**
     * когда создан
     */
    @Column(name = "created_at", nullable = false)
    ZonedDateTime createdAt;

    /**
     * когда обновлен
     */
    @Column(name = "modified_at")
    ZonedDateTime modifiedAt;

    /**
     * json, заполняется при изменении
     */
    @Column(columnDefinition = "TEXT")
    String newEntityJson;

    /**
     * json, заполняется при измнении и при сохранении
     */
    @Column(columnDefinition = "TEXT", nullable = false)
    String entityJson;
}
