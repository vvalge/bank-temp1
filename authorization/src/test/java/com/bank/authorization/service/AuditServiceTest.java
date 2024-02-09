package com.bank.authorization.service;

import com.bank.authorization.AuthorizationApplication;
import com.bank.authorization.exception.RegistrationException;
import com.bank.authorization.repository.entity.AuditEntity;
import com.bank.authorization.repository.entity.AuditRepository;
import com.bank.authorization.repository.entity.UserEntity;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = AuthorizationApplication.class)
@ExtendWith(MockitoExtension.class)
@DisplayName("Тестирование методa AuditService")
public class AuditServiceTest {
    @Mock
    private AuditRepository auditRepository;

    @InjectMocks
    private AuditService auditService;

    /**
     * Тестирование сохранения данных Аудита
     */
    @Test
    public void saveAuditEntityTest() throws RegistrationException {
        final AuditEntity entity = mock(AuditEntity.class);
        final UserDetails userDetails = new UserEntity(13L, "13", "USER");
        final UserDetails currentUser = userDetails;
        JoinPoint joinPoint = mock(JoinPoint.class);
        Signature signature = mock(Signature.class);
        when(joinPoint.getSignature()).thenReturn(signature);
        when(signature.getName()).thenReturn("test");

        auditService.saveAuditEntity(joinPoint, userDetails, currentUser);
        verify(auditRepository).save(any());

    }


}
