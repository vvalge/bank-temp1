package com.bank.authorization.aspects;

import com.bank.authorization.repository.entity.UserEntity;
import com.bank.authorization.service.AuditService;
import com.bank.authorization.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuditAspect {

    @Autowired
    private UserService userService;
    @Autowired
    private AuditService auditService;


    @AfterReturning("execution(public void com.bank.authorization.service.UserServiceImpl.addNewUser(..))")
    public void afterReturningAddNewUser(JoinPoint joinPoint) {
        log.info("Работает метод АОП - Выполнено создание новой сущности ");
        Object[] arguments = joinPoint.getArgs();
        UserDetails userDetails = (UserDetails) arguments[0];
        UserDetails currentUser = userDetails;
        auditService.saveAuditEntity(joinPoint, userDetails, currentUser);


    }

    @AfterReturning("execution(public void com.bank.authorization.service.UserServiceImpl.updateUser(..))")
    public void afterReturningUpdateUser(JoinPoint joinPoint) {
        log.info("Работает метод АОП - Выполнено обновление сущности.");
        Object[] arguments = joinPoint.getArgs();
        UserDetails userDetails = (UserEntity) arguments[0];
        UserDetails currentUser = userService.getCurrentUser();
        auditService.saveAuditEntity(joinPoint, userDetails, currentUser);


    }

    @AfterReturning(pointcut = "execution(public boolean com.bank.authorization.service.UserServiceImpl.deleteUserById(..))")
    public void afterReturningDeleteUser(JoinPoint joinPoint) {
        log.info("Работает метод АОП - Выполнено удаление сущности ");
        Object[] arguments = joinPoint.getArgs();
        Long id = (Long) arguments[0];
        UserDetails userDetails = ((UserEntity) userService.findUserById(id));
        UserDetails currentUser = userService.getCurrentUser();
        auditService.saveAuditEntity(joinPoint, userDetails, currentUser);

    }

    @AfterReturning("execution(public void com.bank.authorization.service.UserServiceImpl.getAdmin())")
    public void afterReturningGetAdmin(JoinPoint joinPoint) {
        log.info("Отработал метод АОП - Назначен администратор");
        UserDetails userDetails = userService.getCurrentUser();
        UserDetails currentUser = userDetails;
        auditService.saveAuditEntity(joinPoint, userDetails, currentUser);


    }


}
