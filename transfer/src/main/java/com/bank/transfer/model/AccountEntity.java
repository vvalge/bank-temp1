package com.bank.transfer.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Класс модели для переводов по номеру счёта
 */
@Builder
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account_transfer", schema = "transfer")
public class AccountEntity{

    /**
     * технический идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    Long id;

    /**
     * номер счета по которому переводить деньги
     */
    @NotEmpty
    @Column(name = "account_number", unique = true, nullable = false)
    Long number;

    /**
     * сумма перевода
     */
    @NotNull
    @Column(nullable = false, precision = 20, scale = 2)
    BigDecimal amount;

    /**
     * цель перевода денег
     */
    String purpose;

    /**
     * технический идентификатор банковского счета
     */
    @Column(name = "account_details_id", nullable = false)
    Long accountDetailsId;
}
