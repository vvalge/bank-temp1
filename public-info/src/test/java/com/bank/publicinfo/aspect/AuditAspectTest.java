package com.bank.publicinfo.aspect;

import com.bank.publicinfo.aspect.annotation.Auditable;
import com.bank.publicinfo.entity.AuditEntity;
import com.bank.publicinfo.repository.AuditRepository;
import com.bank.publicinfo.service.interfaces.MainService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuditAspectTest {

    @InjectMocks
    private AuditAspect auditAspect;

    @Mock
    private AuditRepository auditRepository;

    @Mock
    private HashMap<String, MainService> serviceHashMap;

    @Mock
    private MainService mainService;

    @Test
    void aroundAllAuditableMethodsTestSaveCase() throws Throwable {
        ProceedingJoinPoint proceedingJoinPoint = mock(ProceedingJoinPoint.class);
        MethodSignature methodSignature = mock(MethodSignature.class);
        Auditable auditable = mock(Auditable.class);

        when(methodSignature.getParameterNames()).thenReturn(new String[]{"param1", "param2"});
        when(proceedingJoinPoint.getSignature()).thenReturn(methodSignature);
        when(proceedingJoinPoint.getArgs()).thenReturn(new Object[]{"value1", "value2"});
        when(proceedingJoinPoint.proceed()).thenReturn(new ResponseEntity("TestBody", HttpStatus.valueOf(200)));
        when(auditable.entityType()).thenReturn("Entity");
        when(auditable.operationType()).thenReturn("save");
        auditAspect.aroundAllAuditableMethods(proceedingJoinPoint, auditable);
        verify(auditRepository, times(1)).save(any(AuditEntity.class));
    }

    @Test
    void aroundAllAuditableMethodsTestDeleteCase() throws Throwable {
        ProceedingJoinPoint proceedingJoinPoint = mock(ProceedingJoinPoint.class);
        MethodSignature methodSignature = mock(MethodSignature.class);
        Auditable auditable = mock(Auditable.class);

        when(methodSignature.getParameterNames()).thenReturn(new String[]{"param1", "param2"});
        when(proceedingJoinPoint.getSignature()).thenReturn(methodSignature);
        when(proceedingJoinPoint.getArgs()).thenReturn(new Object[]{"value1", "value2"});
        when(proceedingJoinPoint.proceed()).thenReturn(new ResponseEntity("Body", HttpStatus.valueOf(200)));
        when(auditable.entityType()).thenReturn("Entity");
        when(auditable.operationType()).thenReturn("delete");
        when(serviceHashMap.get("Entity")).thenReturn(mainService);
        when(mainService.findById(null)).thenReturn("CreatedAudit");
        when(auditRepository.findByEntityJson("CreatedAudit"))
                .thenReturn(AuditEntity.builder()
                        .createdBy("User")
                        .createdAt(java.sql.Timestamp.valueOf("2000-01-01 10:10:10.0"))
                        .build());

        auditAspect.aroundAllAuditableMethods(proceedingJoinPoint, auditable);
        verify(auditRepository, times(1)).save(any(AuditEntity.class));
    }

    @Test
    void getCreatedAuditFromEntityId() {
        Auditable auditable = mock(Auditable.class);

        when(auditable.entityType()).thenReturn("Entity");
        when(serviceHashMap.get("Entity")).thenReturn(mainService);
        when(mainService.findById(null)).thenReturn("CreatedAudit");
        when(auditRepository.findByEntityJson("CreatedAudit"))
                .thenReturn(AuditEntity.builder().entityJson("CorrectAudit").build());

        AuditEntity actualAudit = auditAspect.getCreatedAuditFromEntityId(auditable, null);
        assertEquals("CorrectAudit", actualAudit.getEntityJson());
    }

    @Test
    void getParametersFromJoinPoint() {
        ProceedingJoinPoint joinPoint = mock(ProceedingJoinPoint.class);
        MethodSignature methodSignature = mock(MethodSignature.class);

        when(joinPoint.getSignature()).thenReturn(methodSignature);
        when(joinPoint.getArgs()).thenReturn(new Object[]{"value1", "value2"});
        when(((MethodSignature) joinPoint.getSignature()).getParameterNames()).thenReturn(new String[]{"param1", "param2"});

        Map<String, Object> expectedMap = new HashMap<>();
        expectedMap.put("param1", "value1");
        expectedMap.put("param2", "value2");

        Map<String, Object> actualMap = auditAspect.getParametersFromJoinPoint(joinPoint);
        assertEquals(expectedMap, actualMap);
    }

    @Test
    public void testCreateNewAuditThrowsException() throws Exception {
        ProceedingJoinPoint proceedingJoinPoint = mock(ProceedingJoinPoint.class);
        MethodSignature methodSignature = mock(MethodSignature.class);
        Auditable auditable = mock(Auditable.class);

        when(proceedingJoinPoint.getSignature()).thenReturn(methodSignature);
        when(methodSignature.getParameterNames()).thenReturn(new String[]{"param1", "param2"});
        when(proceedingJoinPoint.getArgs()).thenReturn(new Object[]{"value1", "value2"});
        when(auditable.entityType()).thenReturn("Entity");
        when(auditable.operationType()).thenReturn("Operation");

        doThrow(new RuntimeException()).when(auditRepository).save(any(AuditEntity.class));

        assertThrows(RuntimeException.class, () -> {
            auditAspect.aroundAllAuditableMethods(proceedingJoinPoint, auditable);
        });

        verify(auditRepository, times(1)).save(any(AuditEntity.class));
    }
}