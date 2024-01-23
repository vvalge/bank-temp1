package com.bank.history.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;

@Builder
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "history", schema = "history")
public class HistoryEntity {
    public static final int MAX_NAME_LENGTH = 50;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column
    private Long transferAuditId;

    @NotNull
    @Column
    private Long profileAuditId;

    @NotNull
    @Column
    private Long accountAuditId;

    @NotNull
    @Column
    private Long antiFraudAuditId;

    @NotNull
    @Column
    private Long publicBankInfoAuditId;

    @NotNull
    @Column
    private Long authorizationAuditId;

}