package com.bank.authorization.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
//@RequiredArgsConstructor
@JsonSerialize
@Schema(description = "Запрос на регистрацию")
public class SignUpRequestDto {
    @Schema(description = "Id пользователя", example = "123")
    @NotBlank(message = "Id не может быть пустым")
    private long profileId;

    @Schema(description = "Пароль пользователя", example = "My1pass")
    @Size(min = 1, max = 255, message = "Длинна пароля должна быть от 1 до 255 символов")
    @NotBlank(message = "Пароль не может быть пустым")
    private String password;
}
