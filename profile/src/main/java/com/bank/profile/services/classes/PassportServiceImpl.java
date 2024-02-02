package com.bank.profile.services.classes;

import com.bank.profile.dto.PassportDto;
import com.bank.profile.exceptionHandler.exception.NotFoundExceptionEntity;
import com.bank.profile.mapper.interfaces.PassportMapper;
import com.bank.profile.model.Passport;
import com.bank.profile.repository.PassportRepository;
import com.bank.profile.repository.RegistrationRepository;
import com.bank.profile.services.interfase.PassportService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@Slf4j
public class PassportServiceImpl extends BaseServiceImpl<
        PassportDto,
        Passport>
        implements PassportService {

    private final PassportRepository repository;
    private final RegistrationRepository registrationRepository;
    private final PassportMapper mapper;

    public PassportServiceImpl(PassportRepository repository, RegistrationRepository registrationRepository, PassportMapper mapper) {
        super(repository, mapper, Passport.class);
        this.repository = repository;
        this.registrationRepository = registrationRepository;
        this.mapper = mapper;
    }

    @Override
    public void create(PassportDto dto) {
        if(!registrationRepository.existsById(dto.getRegistrationId())){
            throw new NotFoundExceptionEntity("Registration is not find at PassportServiceImpl");
        }
        Passport model = mapper.toEntity(dto);
        Passport save = repository.save(model);
        log.info(Passport.class.getSimpleName() + " с id - \"{}\" сохранен в базе данных", save.getId());
    }

    @Override
    public void update(Long id, PassportDto dto) {
        if (repository.existsById(id) && registrationRepository.existsById(dto.getRegistrationId())) {
            Passport model = mapper.toEntity(dto);
            model.setId(id);
            repository.save(model);
            log.info(Passport.class.getSimpleName() + " с id - \"{}\" обновлен в базе данных", id);
        } else {
            throw new NotFoundExceptionEntity("update is not found");
        }
    }
}
