package com.bank.authorization.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Value
@Getter
@Setter
@AllArgsConstructor
@Schema(description = "Запрос на аутентификацию")
public class SignInRequestDto {

    @Schema(description = "Id пользователя", example = "13")
    @NotBlank(message = "Id не может быть пустым")
    private Long profileId;

    @Schema(description = "Пароль пользователя", example = "My1pass")
    @NotBlank(message = "Пароль не может быть пустым")
    @Size(min = 1, max = 255, message = "Длинна пароля должна быть от 1 до 255 символов")
    private String password;
}
