package com.bank.transfer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuditDto {

    Long id;
    String entityType;
    String operationType;
    String createdBy;
    String modifiedBy;
    @JsonProperty("created_at")
    Instant createdAt;
    @JsonProperty("modified_at")
    Instant modifiedAt = Instant.now();
    String newEntityJson;
    String entityJson;
}
