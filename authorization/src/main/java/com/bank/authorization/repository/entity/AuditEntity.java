package com.bank.authorization.repository.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "audit")
public class AuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Тип сущности
     */
    @NotNull
    private String entity_type;

    /**
     * Тип операции
     */
    @NotNull
    private String operation_type;

    /**
     * ProfileId пользователя кто создал.
     */
    @Column(updatable = false)
    @NotNull
    private String created_by;


    /**
     * ProfileId пользователя кто обновил.
     */
    private String modified_by;

    /**
     * Когда создано
     */
    @Column(updatable = false)
    @ToString.Include
    @NotNull
    private ZonedDateTime created_at;

    /**
     * Когда изменено
     */
    @NotNull
    private ZonedDateTime modified_at;


    private String new_entity_json;

    @NotNull
    private String entity_json;


}
