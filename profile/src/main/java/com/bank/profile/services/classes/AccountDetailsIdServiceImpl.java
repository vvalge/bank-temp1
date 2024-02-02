package com.bank.profile.services.classes;


import com.bank.profile.dto.AccountDetailsIdDto;
import com.bank.profile.exceptionHandler.exception.NotFoundExceptionEntity;
import com.bank.profile.mapper.interfaces.AccountDetailsIdMapper;
import com.bank.profile.model.AccountDetailsId;
import com.bank.profile.repository.AccountDetailsIdRepository;
import com.bank.profile.repository.ProfileRepository;
import com.bank.profile.services.interfase.AccountDetailsIdService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@Slf4j
public class AccountDetailsIdServiceImpl extends BaseServiceImpl<
        AccountDetailsIdDto,
        AccountDetailsId>
        implements AccountDetailsIdService {

    private final AccountDetailsIdRepository repository;
    private final ProfileRepository profileRepository;
    private final AccountDetailsIdMapper mapper;

    public AccountDetailsIdServiceImpl(AccountDetailsIdRepository repository, ProfileRepository profileRepository, AccountDetailsIdMapper mapper) {
        super(repository, mapper, AccountDetailsId.class);
        this.repository = repository;
        this.profileRepository = profileRepository;
        this.mapper = mapper;
    }

    @Override
    public void create(AccountDetailsIdDto dto) {
        if (!profileRepository.existsById(dto.getProfileId())) {
            throw new NotFoundExceptionEntity("Profile is not find at AccountDetailsIdServiceImpl");
        }

        AccountDetailsId model = mapper.toEntity(dto);
        AccountDetailsId save = repository.save(model);
        log.info(AccountDetailsId.class.getSimpleName() + " с id - \"{}\" сохранен в базе данных", save.getId());
    }

    @Override
    public void update(Long id, AccountDetailsIdDto dto) {
        if (repository.existsById(id) && profileRepository.existsById(dto.getProfileId())) {
            AccountDetailsId model = mapper.toEntity(dto);
            model.setId(id);
            repository.save(model);
            log.info(AccountDetailsId.class.getSimpleName() + " с id - \"{}\" обновлен в базе данных", id);
        } else {
            throw new NotFoundExceptionEntity("update is not found");
        }
    }

}