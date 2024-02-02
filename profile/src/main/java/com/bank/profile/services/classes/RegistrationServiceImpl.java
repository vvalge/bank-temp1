package com.bank.profile.services.classes;

import com.bank.profile.dto.RegistrationDto;
import com.bank.profile.mapper.interfaces.RegistrationMapper;
import com.bank.profile.model.Registration;
import com.bank.profile.repository.RegistrationRepository;

import com.bank.profile.services.interfase.RegistrationService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class RegistrationServiceImpl extends BaseServiceImpl<
        RegistrationDto,
        Registration>
        implements RegistrationService {

    private final RegistrationRepository repository;
    private final RegistrationMapper mapper;

    public RegistrationServiceImpl(RegistrationRepository repository, RegistrationMapper mapper) {
        super(repository, mapper, Registration.class);
        this.repository = repository;
        this.mapper = mapper;
    }
}
