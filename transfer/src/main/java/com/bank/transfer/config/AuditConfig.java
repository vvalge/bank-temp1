package com.bank.transfer.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("com.bank.transfer")
@EnableAspectJAutoProxy
public class AuditConfig {

}
