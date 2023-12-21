package com.bank.authorization.aspects;

import com.bank.authorization.repository.entity.AuditEntity;
import com.bank.authorization.repository.entity.AuditRepository;
import com.bank.authorization.repository.entity.UserEntity;
import com.bank.authorization.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;


@Component
@Aspect
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuditAspect {
    @Autowired
    private AuditRepository auditRepository;
    @Autowired
    private UserService userService;


    @AfterReturning("execution(public void com.bank.authorization.service.UserServiceImpl.addNewUser(..))")
    public void afterReturningAddNewUser(JoinPoint joinPoint) {
        System.out.println("Работает метод АОП - Выполнено создание новой сущности ");
        Object[] arguments = joinPoint.getArgs();
        UserDetails userDetails = (UserDetails) arguments[0];

        AuditEntity auditEntity = new AuditEntity();

        auditEntity.setEntity_type(userDetails.getClass().getSimpleName());
        auditEntity.setOperation_type(joinPoint.getSignature().getName());
        auditEntity.setCreated_by(userDetails.getUsername());
        auditEntity.setCreated_at(ZonedDateTime.now());
        auditEntity.setNew_entity_json(  //userDetails.toString());
                "{\"username\": " + userDetails.getUsername() + " \",\n" +
                        "\"password\": " + userDetails.getPassword() + "\" }");
        auditEntity.setEntity_json(  //userDetails.toString());
                "{\"username\": \" " + userDetails.getUsername() + " \",\n" +
                        "\"password\": \" " + userDetails.getPassword() + "\" }");

        auditRepository.save(auditEntity);

    }

    @AfterReturning("execution(public void com.bank.authorization.service.UserServiceImpl.updateUser(..))")
    public void afterReturningUpdateUser(JoinPoint joinPoint) {
        System.out.println("Работает метод АОП - Выполнено обновление сущности ");
        Object[] arguments = joinPoint.getArgs();
        UserEntity userEntity = (UserEntity) arguments[0];

        AuditEntity auditEntity = new AuditEntity();

        auditEntity.setEntity_type(userEntity.getClass().getSimpleName());
        auditEntity.setOperation_type(joinPoint.getSignature().getName());
        auditEntity.setCreated_by("-");
        auditEntity.setModified_by(userService.getCurrentUser().getUsername());
        auditEntity.setCreated_at(ZonedDateTime.now());
        auditEntity.setModified_at(ZonedDateTime.now());
        auditEntity.setNew_entity_json(    //userEntity.toString());
                "{\"username\": " + userEntity.getUsername() + " \",\n" +
                        "\"password\": " + userEntity.getPassword() + "\", \n" + "\"role\": \" " + userEntity.getRole() + "\" }");
        auditEntity.setEntity_json(  //(userEntity.toString());
                "{\"username\": \" " + userEntity.getUsername() + " \",\n" +
                        "\"password\": \" " + userEntity.getPassword() + "\", \n" + "\"role\": \" " + userEntity.getRole() + "\" }");

        auditRepository.save(auditEntity);

    }

    @AfterReturning(pointcut = "execution(public boolean com.bank.authorization.service.UserServiceImpl.deleteUserById(..))")
    public void afterReturningDeleteUser(JoinPoint joinPoint) {
        System.out.println("Работает метод АОП - Выполнено удаление сущности ");
        Object[] arguments = joinPoint.getArgs();
        Long id = (Long) arguments[0];

        AuditEntity auditEntity = new AuditEntity();

        auditEntity.setEntity_type(userService.getCurrentUser().getClass().getSimpleName());
        auditEntity.setOperation_type(joinPoint.getSignature().getName());
        auditEntity.setCreated_by(userService.getCurrentUser().getUsername());
        auditEntity.setCreated_at(ZonedDateTime.now());
        auditEntity.setEntity_json(  //userService.findUserById(id).toString());
                "{\"username\": \" " + ((UserEntity) userService.findUserById(id)).getUsername() + " \",\n" +
                        "\"password\": \" " + ((UserEntity) userService.findUserById(id)).getPassword() + "\", \n" + "\"role\": \" " + ((UserEntity) userService.findUserById(id)).getRole() + "\" }");

        auditRepository.save(auditEntity);
    }

    @AfterReturning("execution(public void com.bank.authorization.service.UserServiceImpl.getAdmin())")
    public void afterReturningGetAdmin(JoinPoint joinPoint) {
        System.out.println("Отработал метод АОП - Назначен администратор");

        AuditEntity auditEntity = new AuditEntity();

        auditEntity.setEntity_type(userService.getCurrentUser().getClass().getSimpleName());
        auditEntity.setOperation_type(joinPoint.getSignature().getName());
        auditEntity.setCreated_by(userService.getCurrentUser().getUsername());
        auditEntity.setCreated_at(ZonedDateTime.now());
        auditEntity.setModified_at(ZonedDateTime.now());
        auditEntity.setEntity_json(  //userService.getCurrentUser().toString());
                "{\"username\": \" " + userService.getCurrentUser().getUsername() + " \",\n" +
                        "\"password\": \" " + userService.getCurrentUser().getPassword() + "\", \n" + "\"role\": \" " + userService.getCurrentUser().getRole() + "\" }");

        auditRepository.save(auditEntity);
    }
}
