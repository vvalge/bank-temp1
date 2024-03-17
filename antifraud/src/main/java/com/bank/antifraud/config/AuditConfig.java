package com.bank.antifraud.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Класс конфигурации аудирования и логирования на уровне контроллера
 */
@Configuration
@ComponentScan("com.bank.antifraud")
@EnableAspectJAutoProxy
public class AuditConfig {

}

