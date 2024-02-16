package com.bank.profile.aspect;

import com.bank.profile.aspect.annotation.Auditable;
import com.bank.profile.model.Audit;
import com.bank.profile.repository.AuditRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuditAspectTest {

    @InjectMocks
    private AuditAspect auditAspect;

    @Mock
    private AuditRepository auditRepository;

    @Mock
    private ObjectMapper mapper;

    @BeforeEach
    public void setup() throws JsonProcessingException {
        when(mapper.writeValueAsString(any(Map.class))).thenReturn("{\"param1\":\"value1\",\"param2\":\"value2\"}");
    }

    @Test
    public void testCreateNewAudit() throws JsonProcessingException {
        JoinPoint joinPoint = mock(JoinPoint.class);
        MethodSignature methodSignature = mock(MethodSignature.class);
        when(joinPoint.getSignature()).thenReturn(methodSignature);
        when(methodSignature.getParameterNames()).thenReturn(new String[]{"param1", "param2"});
        when(joinPoint.getArgs()).thenReturn(new Object[]{"value1", "value2"});

        Auditable auditable = mock(Auditable.class);
        when(auditable.entityType()).thenReturn("Entity");
        when(auditable.operationType()).thenReturn("Operation");
        when(auditable.createdBy()).thenReturn("User");

        auditAspect.createNewAudit(joinPoint, auditable);

        verify(auditRepository, times(1)).save(any(Audit.class));
    }

    @Test
    public void testGetEntityJson() throws JsonProcessingException {
        JoinPoint joinPoint = mock(JoinPoint.class);
        MethodSignature methodSignature = mock(MethodSignature.class);
        when(joinPoint.getSignature()).thenReturn(methodSignature);
        when(methodSignature.getParameterNames()).thenReturn(new String[]{"param1", "param2"});
        when(joinPoint.getArgs()).thenReturn(new Object[]{"value1", "value2"});

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("param1", "value1");
        paramMap.put("param2", "value2");

        String entityJson = auditAspect.getEntityJson(joinPoint);

        assertEquals("{\"param1\":\"value1\",\"param2\":\"value2\"}", entityJson);
    }

    @Test
    public void testCreateNewAuditThrowsException() throws Exception {
        JoinPoint joinPoint = mock(JoinPoint.class);
        MethodSignature methodSignature = mock(MethodSignature.class);
        when(joinPoint.getSignature()).thenReturn(methodSignature);
        when(methodSignature.getParameterNames()).thenReturn(new String[]{"param1", "param2"});
        when(joinPoint.getArgs()).thenReturn(new Object[]{"value1", "value2"});

        Auditable auditable = mock(Auditable.class);
        when(auditable.entityType()).thenReturn("Entity");
        when(auditable.operationType()).thenReturn("Operation");
        when(auditable.createdBy()).thenReturn("User");

        doThrow(new RuntimeException()).when(auditRepository).save(any(Audit.class));

        assertThrows(RuntimeException.class, () -> {
            auditAspect.createNewAudit(joinPoint, auditable);
        });

        verify(auditRepository, times(1)).save(any(Audit.class));
    }
}