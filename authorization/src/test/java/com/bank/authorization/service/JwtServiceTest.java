package com.bank.authorization.service;

import com.bank.authorization.AuthorizationApplication;
import com.bank.authorization.repository.entity.UserEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = AuthorizationApplication.class)
@ExtendWith(MockitoExtension.class)
@DisplayName("Тестирование методов JwtService")
public class JwtServiceTest {
    @Autowired
    private JwtServiceImpl jwtService;

    /**
     * Тестирование получения токена доступа
     */
    @Test
    public void generateTokenTest() {
        UserEntity userDetails = new UserEntity();
        userDetails.setId(13L);
        userDetails.setProfileId(13L);
        userDetails.setPassword("13");
        userDetails.setRole("USER");

        String result = jwtService.generateToken(userDetails);
        Assertions.assertEquals(jwtService.extractProfileId(result), "13");

    }
}
