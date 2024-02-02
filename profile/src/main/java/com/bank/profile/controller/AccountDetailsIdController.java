package com.bank.profile.controller;

import com.bank.profile.aspect.annotation.Auditable;
import com.bank.profile.dto.AccountDetailsIdDto;
import com.bank.profile.model.AccountDetailsId;
import com.bank.profile.services.interfase.AccountDetailsIdService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/***
 * контроллер для {@link AccountDetailsId}
 */
@RestController
@RequestMapping("/account/details")
@OpenAPIDefinition(info = @Info(
        title = "AccountDetailsIdController",
        version = "1.0.0",
        description = "Контроллер для работы со счетами профиля "))
public class AccountDetailsIdController {

    private final AccountDetailsIdService accountDetailsIdService;

    public AccountDetailsIdController(AccountDetailsIdService accountDetailsIdService) {
        this.accountDetailsIdService = accountDetailsIdService;
    }



    @GetMapping("/getById/{id}")
    @Auditable(entityType = "AccountDetailsId", operationType = "get", createdBy = "")
    public ResponseEntity<AccountDetailsId> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(accountDetailsIdService.getById(id));
    }




    @PostMapping("/create")
    @Operation(
            summary = "Создать аккаунт", tags = "AccountDetailsId",
            responses = {
                    @ApiResponse(
                            responseCode = "200", description = "OK",
                            content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AccountDetailsIdDto.class))})
            }
    )
    @Auditable(entityType = "AccountDetailsId", operationType = "create", createdBy = "")
    public ResponseEntity create(@RequestBody AccountDetailsIdDto accountDetailsIdDto) {
        accountDetailsIdService.create(accountDetailsIdDto);
        return ResponseEntity.ok().build();
    }




    @PostMapping("/update/{id}")
    @Operation(
            summary = "Обновить аккаунт", tags = "AccountDetailsId",
            responses = {
                    @ApiResponse(
                            responseCode = "200", description = "OK",
                            content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AccountDetailsIdDto.class))})
            }
    )
    @Auditable(entityType = "AccountDetailsId", operationType = "update", createdBy = "")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody AccountDetailsIdDto accountDetailsId) {
        accountDetailsIdService.update(id, accountDetailsId);
        return ResponseEntity.ok().build();
    }




    @DeleteMapping("/delete/{id}")
    @Operation(
            summary = "Удалить аккаунт", tags = "AccountDetailsId",
            responses = {
                    @ApiResponse(
                            responseCode = "200", description = "OK",
                            content = {@Content(mediaType = "application/json")}),
                    @ApiResponse(responseCode = "404", description = "Not found")
            }
    )
    @Auditable(entityType = "AccountDetailsId", operationType = "delete", createdBy = "")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        accountDetailsIdService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}