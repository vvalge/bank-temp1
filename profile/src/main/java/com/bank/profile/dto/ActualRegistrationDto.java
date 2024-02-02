package com.bank.profile.dto;

import com.bank.profile.model.ActualRegistration;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Класс, представляющий Data Transfer Object (DTO) для {@link ActualRegistration}.
 * Используется для передачи данных между слоями приложения.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActualRegistrationDto {

    private String country;

    private String region;

    private String city;

    private String district;

    private String locality;

    private String street;

    private String houseNumber;

    private String houseBlock;

    private String flatNumber;

    private long index;

}
