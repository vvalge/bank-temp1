package com.bank.history.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HistoryEntity that = (HistoryEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(transferAuditId, that.transferAuditId) &&
                Objects.equals(profileAuditId, that.profileAuditId) &&
                Objects.equals(accountAuditId, that.accountAuditId) &&
                Objects.equals(antiFraudAuditId, that.antiFraudAuditId) &&
                Objects.equals(publicBankInfoAuditId, that.publicBankInfoAuditId) &&
                Objects.equals(authorizationAuditId, that.authorizationAuditId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, transferAuditId, profileAuditId, accountAuditId, antiFraudAuditId, publicBankInfoAuditId, authorizationAuditId);
    }


}