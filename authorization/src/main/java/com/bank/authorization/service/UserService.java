package com.bank.authorization.service;

import com.bank.authorization.exception.RegistrationException;
import com.bank.authorization.repository.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    public void addNewUser(UserDetails user) throws RegistrationException;  //+

    public void updateUser(UserEntity user);  //+

    public boolean deleteUserById(Long id);  //+

    public void getAdmin();

    public List<UserEntity> getAllUsers();  //+

    public List<String> getAllRoles(Long profileId);  //+

    public Object findUserById(long id); //+

    public UserEntity getByPassword(String password);  //+

    public UserEntity findByProfileId(long profileId); //+

    public UserEntity getCurrentUser();
}
