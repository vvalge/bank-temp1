package com.bank.transfer.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;
import java.time.ZonedDateTime;

@Builder
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "audit", schema = "transfer")
public class AuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "entity_type", length = 40, nullable = false)
    String entityType;

    @Column(name = "operation_type", nullable = false)
    String operationType;

    @Column(name = "created_by",nullable = false)
    String createdBy;

    @Column(name = "modified_by")
    String modifiedBy;

    @Column(name = "created_at", nullable = false)
    ZonedDateTime createdAt;

    @Column(name = "modified_at")
    ZonedDateTime modifiedAt;

    @Column(columnDefinition = "TEXT")
    String newEntityJson;

    @Column(columnDefinition = "TEXT", nullable = false)
    String entityJson;
}
