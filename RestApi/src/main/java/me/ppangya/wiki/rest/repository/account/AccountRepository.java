package me.ppangya.wiki.rest.repository.account;

import me.ppangya.wiki.rest.repository.entity.Account;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface AccountRepository extends Repository<Account, Long>, AccountRepositoryCustom {

    <S extends Account> Account save(S account);
    
}
