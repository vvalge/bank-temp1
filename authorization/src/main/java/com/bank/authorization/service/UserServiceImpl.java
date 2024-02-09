package com.bank.authorization.service;

import com.bank.authorization.exception.RegistrationException;
import com.bank.authorization.repository.entity.UserEntity;
import com.bank.authorization.repository.entity.UserRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Log
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;


    /**
     * Сохранение пользователя
     * @param user
     * @throws RegistrationException
     */
    @Transactional
    public void addNewUser(UserDetails user) throws RegistrationException {
        UserEntity userFromDB = userRepository.findByProfileId(Long.valueOf(user.getUsername()));
        if (userFromDB != null) {
            throw new RegistrationException("Client already registered");
        }

        userRepository.save(new UserEntity(Long.valueOf(user.getUsername()),user.getPassword(),"USER"));
        log.info("New user " + user.getUsername() + " add in the database");
    }

    /**
     * Обновление данных пользователя
     * @param user
     */
    @Transactional
    public void updateUser(UserEntity user) {
        log.info("Updating user data");
        userRepository.save(user);
    }

    /**
     * Удаление пользователя по Id
     * @param id
     * @return boolean
     */
    @Transactional
    public boolean deleteUserById(Long id) {
        if (userRepository.findById(id).isPresent()) {
          //  UserEntity userToDelete = (UserEntity) findUserById(id);
            userRepository.deleteById(id);
            log.info("User id " + id + " delete from the database");
           return true;
        }
        log.info("User id " + id + " not found in database");
        return false;
    }

    /**
     * Выдача прав администратора текущему пользователю
     * <p>
     * Нужен для демонстрации
     */
    public void getAdmin() {
        var user = getCurrentUser();
        user.setRole("ADMIN");
        updateUser(user);
        log.info("User " + user.getUsername() +" is assigned as administrator");
    }

    /**
     * Получение списка всех пользователей
     * @return  list allUsers
     */
    public List<UserEntity> getAllUsers() {
        log.info("Getting a list of users from the database");
        return userRepository.findAll();
    }


    /**
     * Получение списка ролей пользователя
     * @param profileId
     * @return
     */
    public List<String> getAllRoles(Long profileId) {
        List<String> allRoles = new ArrayList<>();
        allRoles.add((userRepository.findByProfileId(profileId)).getRole());
        log.info("Getting a list of user roles");
        return allRoles;
    }

    /**
     * Получение пользователя по Id
     * @param id
     * @return object
     */
    public Object findUserById(long id) {
        log.info("Getting user by Id");
        return userRepository.findById(id);
    }

    /**
     * Получение пользователя по уникальному паролю
     * @param password
     * @return user
     */
    public UserEntity getByPassword(String password) {
        log.info("Getting user by password");
        return userRepository.findByPassword(password);
    }

    /**
     * Получение пользователя по profileId
     * <p>
     *     Нужен для Security
     * @param profileId
     * @return user
     */
    public UserEntity findByProfileId(long profileId) {
        log.info("Getting user by profileId");
        return userRepository.findByProfileId(profileId);
    }

    //этот метод переопределяет способ сверки данных пользователя по логину и паролю
// и передает эти данные в обьект аутентикейшн
    /**
     * Получение данных пользователя для обьекта аутнтикейшн с заполненными ролями для сверки с бд и создания обьекта аутентификации
     * @param username
     * @return UserDetails
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userFromDB = userRepository.findByProfileId(Long.valueOf(username));
        if (userFromDB == null) {
            throw new UsernameNotFoundException("Incorrect login or unknown user");
        }
       UserDetails user = User.builder()
                 .username(String.valueOf(userFromDB.getProfileId()))
                 .password(userFromDB.getPassword())
                       .roles(userFromDB.getRole())
                       .build();
        log.info("Creating an object userDetails");
        return user;

    }

    /**
     * Получение данных текущего пользователя из контекста Spring Security
     * @return UserEntity
     */
    public UserEntity getCurrentUser() {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("Getting the current user");
        return findByProfileId(Long.valueOf(username));
    }



}


