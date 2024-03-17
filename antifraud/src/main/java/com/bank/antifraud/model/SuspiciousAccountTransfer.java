package com.bank.antifraud.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Builder
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "anti_fraud", name = "suspicious_account_transfers")
public class SuspiciousAccountTransfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name="account_transfer_id")
    private Long accountTransferId;

    @NotNull
    @Column(name="is_blocked")
    private Boolean isBlocked;

    @NotNull
    @Column(name="is_suspicious")
    private Boolean isSuspicious;

    @Column(name="blocked_reason")
    private String blockedReason;

    @NotNull
    @Column(name="suspicious_reason")
    private String suspiciousReason;
}
