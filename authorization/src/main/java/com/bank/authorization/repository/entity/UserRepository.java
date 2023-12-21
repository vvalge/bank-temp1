package com.bank.authorization.repository.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByPassword(String password);

    UserEntity findByProfileId(long profileId);

    boolean existsByProfileId(long profileId);

}

