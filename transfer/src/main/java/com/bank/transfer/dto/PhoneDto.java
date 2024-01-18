package com.bank.transfer.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import java.math.BigDecimal;

/**
 * Класс описывающий объект модели перевода по номеру телефона, который используется для передачи данных между подсистемами приложения
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PhoneDto {

    Long id;
    @NonNull
    Long number;
    @NonNull
    BigDecimal amount;
    String purpose;
    Long accountDetailsId;
}
