package com.bank.authorization.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Ответ с токеном доступа")
public class JwtAuthenticationResponse {
    private String token;
    private HttpStatus httpStatus;

}
