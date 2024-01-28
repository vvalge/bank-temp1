package com.bank.authorization.controller;

import com.bank.authorization.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "Аутентификация")
public class DemonstrationController {
    private final UserService userService;

    @GetMapping
    @Operation(summary = "Страница доступна только авторизованным пользователям")
    public String usersPage() {
        return "Hello, user!";
    }

    @GetMapping("/admin")
    @Operation(summary = "Страница доступна только авторизованным пользователям с ролью ADMIN")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminPage() {
        return "Hello, admin!";
    }

    @GetMapping("/get-admin")
    @Operation(summary = "Получить роль ADMIN (для демонстрации)")
    public String getAdmin() {

        userService.getAdmin();
        return "You are ADMIN!";
    }

    @GetMapping("/admin/delete/{id}")
    @Operation(summary = "Удаление пользователя из ДБ,доступно только авторизованным пользователям с ролью ADMIN ")
    public String deletedUser(@PathVariable @Valid Long id) {
        if (userService.deleteUserById(id)) {
            return "Пользователь " + id + " успешно удален.";
        }
        return "Пользователь " + id + " не найден в ДБ.";
    }


}

