package com.bank.profile.controller;

import com.bank.profile.dto.AuditDto;
import com.bank.profile.model.Audit;
import com.bank.profile.services.interfase.AuditService;

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
 * контроллер для {@link Audit}
 */

@RestController
@RequestMapping("/audit")
@OpenAPIDefinition(info = @Info(
        title = "AuditController",
        version = "1.0.0",
        description = "Контроллер для работы с аудитом"))
public class AuditController {

    private final AuditService auditService;

    public AuditController(AuditService auditService) {
        this.auditService = auditService;
    }


    @GetMapping("getById/{id}")
        @Operation(
            summary = "Получить по id аудит", tags = "Audit",
            responses = {
                    @ApiResponse(
                            responseCode = "200", description = "OK",
                            content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Audit.class))}),
                    @ApiResponse(responseCode = "404", description = "Not found")
            }
    )
    public ResponseEntity<Audit> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(auditService.getById(id));
    }




    @PostMapping("/create")
        @Operation(
            summary = "Создать аудит", tags = "Audit",
            responses = {
                    @ApiResponse(
                            responseCode = "200", description = "OK",
                            content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AuditDto.class))})
            }
    )
    public ResponseEntity create(@RequestBody AuditDto auditDto) {
        auditService.create(auditDto);
        return ResponseEntity.ok().build();
    }




    @PostMapping("/update/{id}")
        @Operation(
            summary = "Обновить аудит", tags = "Audit",
            responses = {
                    @ApiResponse(
                            responseCode = "200", description = "OK",
                            content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AuditDto.class))})

            }
    )
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody AuditDto auditDto) {
        auditService.update(id, auditDto);
        return ResponseEntity.ok().build();
    }




    @DeleteMapping("/delete/{id}")
    @Operation(
            summary = "Удалить аудит", tags = "Audit",
            responses = {
                    @ApiResponse(
                            responseCode = "200", description = "OK",
                            content = {@Content(mediaType = "application/json")}),
                    @ApiResponse(responseCode = "404", description = "Not found")
            }
    )
    public ResponseEntity delete(@PathVariable("id") Long id) {
        auditService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}