package com.bank.transfer.repository;

import com.bank.transfer.model.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    @Query("select u from AccountEntity u where u.number=(:number)")
    AccountEntity findByNumber(@Param("number") Long number);
}
