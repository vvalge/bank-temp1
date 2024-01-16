package com.bank.transfer.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Builder
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account_transfer", schema = "transfer")
public class AccountEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    Long id;

    @NotEmpty
    @Column(name = "account_number", unique = true, nullable = false)
    Long number;

    @NotNull
    @Column(nullable = false, precision = 20, scale = 2)
    BigDecimal amount;

    String purpose;

    @Column(name = "account_details_id", nullable = false)
    Long accountDetailsId;
}
