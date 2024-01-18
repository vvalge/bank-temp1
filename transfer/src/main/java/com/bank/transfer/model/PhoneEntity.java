package com.bank.transfer.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Класс модели для переводов по номеру телефона
 */
@Builder
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "phone_transfer", schema = "transfer")
public class PhoneEntity {

    /**
     * технический идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    Long id;

    /**
     * номер телефона по которому переводить деньги
     */
    @NotNull
    @Column(name = "phone_number", nullable = false)
    Long number;

    /**
     * сумма перевода
     */
    @NotNull
    @Column(nullable = false)
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
