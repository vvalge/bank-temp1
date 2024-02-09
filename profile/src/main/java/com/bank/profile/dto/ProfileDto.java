package com.bank.profile.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Класс, представляющий Data Transfer Object (DTO) для {@link com.bank.profile.model.Profile}.
 * Используется для передачи данных между слоями приложения.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileDto {

    private Long phoneNumber;

    private String email;

    private String nameOnCard;

    private Long inn;

    private Long snils;

    private Long passport_id;

    private Long actualRegistrationId;

}
