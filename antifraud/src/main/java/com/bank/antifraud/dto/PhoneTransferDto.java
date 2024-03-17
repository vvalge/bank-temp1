package com.bank.antifraud.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PhoneTransferDto {

    //private Long id;

    @NonNull
    private Long phoneTransferId;

    @NonNull
    private Boolean isBlocked;

    @NonNull
    private Boolean isSuspicious;

    private String blockedReason;

    @NonNull
    private String suspiciousReason;
}
