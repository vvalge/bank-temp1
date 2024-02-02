package com.bank.profile.dto;

import com.bank.profile.model.AccountDetailsId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Класс, представляющий Data Transfer Object (DTO) для {@link AccountDetailsId}.
 * Используется для передачи данных между слоями приложения.
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AccountDetailsIdDto {

    private Long accountId;

    private Long profileId;

}