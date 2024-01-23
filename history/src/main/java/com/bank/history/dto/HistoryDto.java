package com.bank.history.dto;


import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HistoryDto {

    private Long id;

    @NotNull
    private Long transferAuditId;

    @NotNull
    private Long profileAuditId;

    @NotNull
    private Long accountAuditId;

    @NotNull
    private Long antiFraudAuditId;

    @NotNull
    private Long publicBankInfoAuditId;

    @NotNull
    private Long authorizationAuditId;

}