package com.bank.transfer.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@Aspect
@Order(1)
public class LogAllTransferMethods {

    @Before("@within(org.springframework.web.bind.annotation.RestController)")
    public void allControllerAspect() {
        log.info("BEFORE - Processing a transfer request");
    }
}
