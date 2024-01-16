package com.bank.transfer.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Builder
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "phone_transfer", schema = "transfer")
public class PhoneEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    Long id;

    @NotNull
    @Column(name = "phone_number", nullable = false)
    Long number;

    @NotNull
    @Column(nullable = false)
    BigDecimal amount;

    String purpose;

    @Column(name = "account_details_id", nullable = false)
    Long accountDetailsId;
}
