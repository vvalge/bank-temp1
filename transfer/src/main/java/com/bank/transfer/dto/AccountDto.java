package com.bank.transfer.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import java.math.BigDecimal;

/**
 * Класс описывающий объект модели перевода по номеру счета, который используется для передачи данных между подсистемами приложения
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountDto {

    Long id;
    @NonNull
    Long number;
    @NonNull
    BigDecimal amount;
    String purpose;
    Long accountDetailsId;
}
