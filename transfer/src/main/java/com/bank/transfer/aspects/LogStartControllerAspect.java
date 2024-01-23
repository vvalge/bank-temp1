package com.bank.transfer.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Класс для логирования на уровне Контроллера
 */
@Slf4j
@Component
@Aspect
@Order(1)
public class LogStartControllerAspect {

    /**
     * Метод логирования перед обработкой запроса
     */
    @Before("@within(org.springframework.web.bind.annotation.RestController)")
    public void allControllerAspect() {
        log.info("BEFORE - Processing a transfer request");
    }
}
