package com.bank.authorization.service;

import com.bank.authorization.AuthorizationApplication;
import com.bank.authorization.exception.RegistrationException;
import com.bank.authorization.repository.entity.UserEntity;
import com.bank.authorization.repository.entity.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


@SpringBootTest(classes = AuthorizationApplication.class)
@ExtendWith(MockitoExtension.class)
@DisplayName("Тестирование методов UserService")
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;


    /**
     * Тестирование получения списkа пользователей
     */
    @Test
    public void getAllUsersTest() {
        List<UserEntity> testUsersList = getUserListTest();
        Mockito.when(userRepository.findAll()).thenReturn(testUsersList);

        List<UserEntity> result = userService.getAllUsers();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals(testUsersList.get(0), result.get(0));
        Assertions.assertEquals(testUsersList.get(1), result.get(1));

    }

    private List<UserEntity> getUserListTest() {
        UserEntity first = new UserEntity(1, "1", "USER");
        UserEntity second = new UserEntity(2, "2", "USER");
        return List.of(first, second);
    }

    /**
     * Тестирование получения данных пользователя по profileId
     */
    @Test
    public void findByProfileIdTest() {
        UserEntity testUser = new UserEntity(13l, "13", "USER");
        Mockito.when(userRepository.findByProfileId(13)).thenReturn(testUser);
        UserEntity result = userService.findByProfileId(13);

        verify(userRepository).findByProfileId(13);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(testUser, result);

    }

    /**
     * Тестирование получения данных пользователя по password
     */
    @Test
    public void getByPasswordTest() {
        UserEntity testUser = new UserEntity(13l, "13", "USER");
        Mockito.when(userRepository.findByPassword("13")).thenReturn(testUser);

        UserEntity result = userService.getByPassword("13");

        verify(userRepository).findByPassword("13");
        Assertions.assertNotNull(result);
        Assertions.assertEquals(testUser, result);

    }

    /**
     * Тестирование получения списка ролей пользователя
     */
    @Test
    public void getAllRolesTest() {
        List<String> roleTest = new ArrayList<>();
        roleTest.add("USER");
        UserEntity entity = new UserEntity(13, "13", "USER");

        Mockito.when(userRepository.findByProfileId(13)).thenReturn(entity);

        List<String> result = userService.getAllRoles(13L);

        verify(userRepository).findByProfileId(13);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(roleTest.size(), result.size());
        Assertions.assertEquals(roleTest.get(0), result.get(0));

    }

    /**
     * Тестирование получения данных пользователя по Id
     */
    @Test
    public void findUserByIdTest() {
        Optional entity = mock(Optional.class);
        Mockito.when(userRepository.findById(13L)).thenReturn(entity);

        Object result = userService.findUserById(13);
        verify(userRepository).findById(13L);
        Assertions.assertNotNull(result);
    }

    /**
     * Тестирование удаления данных пользователя по Id
     */
    @Test
    public void deleteUserByIdTest() {
        Optional<UserEntity> entity = Optional.ofNullable(new UserEntity(13, "13", "USER"));
        Mockito.when(userRepository.findById(13L)).thenReturn(entity);

        Boolean bool = userService.deleteUserById(13L);
        verify(userRepository).deleteById(13L);
        Assertions.assertTrue(bool);
    }

    /**
     * Тестирование обновления данных пользователя
     */
    @Test
    public void updateUserTest() {
        UserEntity entity = mock(UserEntity.class);

        userService.updateUser(entity);
        verify(userRepository).save(entity);
    }

    /**
     * Тестирование получения данных пользователя по Id
     */
    @Test
    public void addNewUserTest() throws RegistrationException {
        UserDetails entity = mock(UserDetails.class);
        Mockito.when(entity.getUsername()).thenReturn("13");
        Mockito.when(entity.getPassword()).thenReturn("13");

        Mockito.when(userRepository.findByProfileId(Long.valueOf(entity.getUsername()))).thenReturn(null);

        userService.addNewUser(entity);
        verify(userRepository).save(any());
    }


}




