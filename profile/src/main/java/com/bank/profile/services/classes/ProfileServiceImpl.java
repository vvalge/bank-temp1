package com.bank.profile.services.classes;


import com.bank.profile.dto.ProfileDto;
import com.bank.profile.exceptionHandler.exception.NotFoundExceptionEntity;
import com.bank.profile.mapper.interfaces.ProfileMapper;
import com.bank.profile.model.Profile;
import com.bank.profile.repository.ActualRegistrationRepository;
import com.bank.profile.repository.PassportRepository;
import com.bank.profile.repository.ProfileRepository;
import com.bank.profile.services.interfase.ProfileService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@Slf4j
public class ProfileServiceImpl extends BaseServiceImpl<
        ProfileDto,
        Profile>
        implements ProfileService {

    private final ProfileRepository repository;
    private final ProfileMapper mapper;
    private final PassportRepository passportRepository;
    private final ActualRegistrationRepository actualRegistrationRepository;

    public ProfileServiceImpl(ProfileRepository repository, ProfileMapper mapper, PassportRepository passportRepository, ActualRegistrationRepository actualRegistrationRepository) {
        super(repository, mapper, Profile.class);
        this.repository = repository;
        this.mapper = mapper;
        this.passportRepository = passportRepository;
        this.actualRegistrationRepository = actualRegistrationRepository;
    }


    @Override
    public void create(ProfileDto dto) {
        if(!(actualRegistrationRepository.existsById(dto.getActualRegistrationId())) || !(passportRepository.existsById(dto.getPassport_id()))){
            throw new NotFoundExceptionEntity("create is not find at ProfileServiceImpl in actualRegistrationRepository or passportRepository");
        }
        Profile model = mapper.toEntity(dto);
        Profile save = repository.save(model);
        log.info(Profile.class.getSimpleName() + " с id - \"{}\" сохранен в базе данных", save.getId());
    }

    @Override
    public void update(Long id, ProfileDto dto) {
        if (repository.existsById(id)
                && actualRegistrationRepository.existsById(dto.getActualRegistrationId())
                && passportRepository.existsById(dto.getPassport_id()
        )) {
            Profile model = mapper.toEntity(dto);
            model.setId(id);
            repository.save(model);
            log.info(Profile.class.getSimpleName() + " с id - \"{}\" обновлен в базе данных", id);
        } else {
            throw new NotFoundExceptionEntity("update is not found");
        }
    }
}
